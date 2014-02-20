package br.atech.workshop.bestpractices.thirdpart1;

import java.util.Iterator;

/**
 * 
 * @author marcio
 * 
 * @param <T>
 */
public interface DataProvider<T> {

	/**
	 * 
	 * @param query
	 * @return
	 * @throws DataException
	 */
	Iterator<T> getData(String query) throws DataException;
}
