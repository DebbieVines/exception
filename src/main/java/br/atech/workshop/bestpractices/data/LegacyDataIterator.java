/**
 * 
 */
package br.atech.workshop.bestpractices.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import br.atech.workshop.bestpractices.thirdpart1.DataException;
import br.atech.workshop.bestpractices.thirdpart2.DataPointer;

/**
 * 
 * @author spac2
 * 
 */
public class LegacyDataIterator implements Iterator<Integer> {

	private DataPointer pointer;

	/**
	 * 
	 * @param url
	 * @throws DataException
	 */
	public LegacyDataIterator(InputStream input, String query) {
		pointer = new DataPointer(input, query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		try {
			return !pointer.eof();
		} catch (IOException e) {
			// return null;

			System.err.println("FATAL ERROR.");
			e.printStackTrace();
			return false;

			// throw new RuntimeException("FATAL ERROR.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#next()
	 */
	@Override
	public Integer next() {
		try {
			return pointer.getValue();
		} catch (IOException e) {
			// return null;

			System.err.println("FATAL ERROR.");
			e.printStackTrace();
			return null;

			// throw new RuntimeException("FATAL ERROR.", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
}
