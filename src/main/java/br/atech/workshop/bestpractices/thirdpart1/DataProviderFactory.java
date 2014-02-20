package br.atech.workshop.bestpractices.thirdpart1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

/**
 * 
 * @author marcio
 * 
 */
public class DataProviderFactory {
	/**
	 * 
	 */
	public DataProviderFactory() {

	}

	/**
	 * 
	 * @param endpoint
	 * @return
	 * @throws DataException
	 */
	public <T> DataProvider<T> getProvider(String endpoint, Class<T> messageType)
			throws DataException {

		try {
			Enumeration<URL> resources = this.getClass().getClassLoader()
					.getResources("META-INF/data-provider.info");

			while (resources.hasMoreElements()) {
				URL resource = resources.nextElement();

				System.out.println(resource);

				Properties info = new Properties();
				info.load(resource.openStream());

				Set<Entry<Object, Object>> entries = info.entrySet();

				for (Entry<Object, Object> entry : entries) {
					String protocol = entry.getKey().toString();
					String implementor = entry.getValue().toString();

					if (endpoint.startsWith(protocol)) {
						@SuppressWarnings("unchecked")
						Class<DataProvider<T>> implClass = (Class<DataProvider<T>>) Class
								.forName(implementor);
						DataProvider<T> provider = implClass.getConstructor(
								String.class).newInstance(endpoint);
						return provider;
					}
				}
			}

			throw new UnsupportedOperationException(
					"No provider to the given endpoint: " + endpoint);

		} catch (IOException | ClassNotFoundException | InstantiationException
				| IllegalAccessException | IllegalArgumentException
				| InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new DataException(e);
		}
	}
}
