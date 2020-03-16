package FileUtil;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

public class FileWrite {
	private final static Logger LOG = Logger.getLogger("FileWrite");

	
	public void writeFile(String word){
		try(BufferedWriter bufWriter = new BufferedWriter(new FileWriter("result", true))) {			
			bufWriter.append(word).append("\r\n");			
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
	}
}
