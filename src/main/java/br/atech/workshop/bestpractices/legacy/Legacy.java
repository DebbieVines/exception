/**
 * 
 */
package br.atech.workshop.bestpractices.legacy;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * @author marcio
 * 
 */
public class Legacy {

	public static class FakeInputStream extends InputStream {

		public FakeInputStream() {
			super();
		}

		@Override
		public synchronized int read(byte[] b, int off, int len)
				throws IOException {
			throw new IOException();
		}

		@Override
		public int read() throws IOException {
			return read(null, 0, 0);
		}
	}

	// SYS1 =
	// new ByteArrayInputStream("fulano 0\nbeltrano 2\ncicrano 3".getBytes());

	// SYS2
	// new ByteArrayInputStream("beltrano a\ncicrano ".getBytes());

	// UNREACHABLE
	// null;

	// FAILING
	// new FakeInputStream();

	static int counter;
	
	public static InputStream getInputStream(URL conn) throws IOException {	
		// it really tests the connection  
		conn.openConnection().getInputStream().read();
		
		 if (conn.toString().contains("system2")) {
			counter++;

			if (counter <= 3) {
				return new ByteArrayInputStream("maria 20\njose a".getBytes());
			} else if (counter <= 6) {
				return null;
			} else if (counter <= 9) {
				return new FakeInputStream();
			} else {
				counter = 0;
				throw new IOException();
			}
		} else {
			return new ByteArrayInputStream(
					"joao 0\nmaria 2\njose 3\njoao 13\n".getBytes());
		} 
	}
}
