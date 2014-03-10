package br.atech.workshop.bestpractices.gui;

import br.atech.workshop.bestpractices.app.AppException;
import br.atech.workshop.bestpractices.app.InfoRequestException;
import br.atech.workshop.bestpractices.thirdpart1.DataException;

/**
 * 
 * @author marcio
 * 
 */
public class ExceptionHandler {

	public Gui gui;

	/**
	 * 
	 * @param gui
	 */
	public ExceptionHandler(Gui gui) {
		this.gui = gui;
	}

	/**
	 * 
	 * @param t
	 */
	public void handle(Throwable t) {
		t.printStackTrace();

		String msg = translate(t);

		gui.error(msg);
	}

	/**
	 * 
	 * @param t
	 * @return
	 */
	private String translate(Throwable t) {
		Throwable err = t;
		while (err.getCause() != null
				&& err.getClass().equals(RuntimeException.class)) {
			err = err.getCause();
		}

//		if (err instanceof InfoRequestException) {
//			return "Resource error: "
//					+ ((InfoRequestException) err).getProvider();
//		}

		if (err instanceof AppException) {
			return "Could not answer to your request.";
		}

		if (err instanceof DataException) {
			return "Could not access the data to answer to your request.";
		}

		if (err instanceof RuntimeException) {
			return "System internal error. Notify sysadmin";
		}

		return "System Error.";
	}
}