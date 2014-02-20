package br.atech.workshop.bestpractices.gui;

import br.atech.workshop.bestpractices.app.AppException;
import br.atech.workshop.bestpractices.thirdpart1.DataException;

/**
 * 
 * @author spac2
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

		String msg;
		if (err instanceof AppException) {
			msg = "Application error.";
		} else if (err instanceof DataException) {
			msg = "Data error.";
		} else {
			msg = "System Error. Please notify sysadmin";
		}

		return msg;
	}
}