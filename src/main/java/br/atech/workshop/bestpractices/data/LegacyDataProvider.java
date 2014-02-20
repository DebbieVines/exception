/**
 * 
 */
package br.atech.workshop.bestpractices.data;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import br.atech.workshop.bestpractices.legacy.Legacy;
import br.atech.workshop.bestpractices.thirdpart1.DataException;
import br.atech.workshop.bestpractices.thirdpart1.DataProvider;

/**
 * @author marcio
 * 
 */
public class LegacyDataProvider implements DataProvider<Integer> {

	private URL url;

	/**
	 * 
	 * @param endpoint
	 */
	public LegacyDataProvider(String endpoint) {
		try {
			this.url = new URL(endpoint);
		} catch (MalformedURLException e) {
			System.err.println("WARNING: Endpoint config error." + endpoint);
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param endpoint
	 */
	public LegacyDataProvider(URL endpoint) {
		this.url = endpoint;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * br.atech.workshop.bestpractices.data.DataProvider#getData(java.lang.String
	 * )
	 */
	@Override
	public Iterator<Integer> getData(String query) throws DataException {
		try { 
			return new LegacyDataIterator(connect(url), query);
		} catch (IOException e) {
			throw new DataException(e);
		}
	}

	private InputStream connect(URL url) throws IOException {
		return Legacy.getInputStream(url);
	}
}
