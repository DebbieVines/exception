/**
 * 
 */
package br.atech.workshop.bestpractices.app;

import br.atech.workshop.bestpractices.gui.Gui;
import br.atech.workshop.bestpractices.thirdpart1.DataException;
import br.atech.workshop.bestpractices.thirdpart1.DataProvider;
import br.atech.workshop.bestpractices.thirdpart1.DataProviderFactory;

/**
 * @author spac2
 * 
 */
public class Boot {

	/**
	 * 
	 * @param args
	 * @throws AppException
	 */
	public static void main(String[] args) throws AppException {
		try {

			DataProviderFactory factory = new DataProviderFactory();

			DataProvider<Integer> sys1 = factory.getProvider(args[0],
					Integer.class);
			DataProvider<Integer> sys2 = factory.getProvider(args[1],
					Integer.class);
			DataProvider<Integer> sys3 = factory.getProvider(args[2],
					Integer.class);

			Gui gui = new Gui(new App(sys1, sys2, sys3));

			gui.show();

		} catch (DataException e) {
			e.printStackTrace();

			System.exit(1);
		}
	}
}
