package Producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import Brocker.Brocker;

public class ProducerFileRead extends Producer{	
	private final static Logger LOG = Logger.getLogger("ProducerFileRead");	
	
	private final static String INVAILD_PATTERN ="^[a-zA-Z0-9]*$";


	public ProducerFileRead(Brocker brocker) {
		super(brocker);
	}

	protected void readFile(){
		File file = new File("word.txt");
		
		try(BufferedReader bufReader = new BufferedReader(new FileReader(file));){
            //입력 스트림 생성
            String line = "";
            while((line = bufReader.readLine()) != null){
            	if(Pattern.matches(INVAILD_PATTERN, line)){
            		wordQueue.addFileWord(line);
            	}
            }
        }catch (FileNotFoundException e) {
        	LOG.severe(e.getMessage());
        }catch(IOException e){
        	LOG.severe(e.getMessage());
        }
	}

}
