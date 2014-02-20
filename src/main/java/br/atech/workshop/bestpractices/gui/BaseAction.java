package br.atech.workshop.bestpractices.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 * @author spac2
 * 
 */
abstract class BaseAction implements ActionListener {

	private ExceptionHandler exHandler;
	private Gui gui;

	/**
	 * 
	 * @param gui
	 */
	BaseAction(Gui gui) {
		this.gui = gui;
		this.exHandler = new ExceptionHandler(gui);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		gui.reset();
		try {
			doAction(event);
		} catch (Exception e) {
			exHandler.handle(e);
		}
	}

	/**
	 * 
	 * @param event
	 * @throws Exception
	 */
	public abstract void doAction(ActionEvent event) throws Exception;
}