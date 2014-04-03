package br.atech.workshop.bestpractices.gui;

import br.atech.workshop.bestpractices.app.AppException;
import br.atech.workshop.bestpractices.app.InfoRequestException;

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
		gui.error(translate(t));
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

		if (err instanceof InfoRequestException) {
			if (err.getCause() != null
					&& err.getCause().getMessage().contains("1404")) {
				return "Erro de leitura: "
						+ ((InfoRequestException) err).getProvider();
			} else {
				return "Erro de conexão: "
						+ ((InfoRequestException) err).getProvider();
			}
		} else if (err instanceof AppException) {
			return "Não foi possível processar sua requisição.";
		} else if (err instanceof RuntimeException) {
			return "Falha interna. Entre em contato com o administrador do sistema";
		} else {
			return "Falha de operação.";
		}
	}
}