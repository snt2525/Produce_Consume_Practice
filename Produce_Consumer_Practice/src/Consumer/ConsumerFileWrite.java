package Consumer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;

import Brocker.Brocker;

public class ConsumerFileWrite extends Consumer{
	private final static Logger LOG = Logger.getLogger("ConsumerFileWrite");	

	public ConsumerFileWrite(Brocker brocker) {
		super(brocker);
	}
	
	public void writeFile(String word){
		try(BufferedWriter bufWriter = new BufferedWriter(new FileWriter("result", true))) {			
			bufWriter.append(word).append("\r\n");			
		} catch (IOException e) {
			LOG.severe(e.getMessage());
		}
	}
}
