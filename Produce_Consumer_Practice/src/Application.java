import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Brocker.Brocker;
import Consumer.Consumer;
import FileUtil.FileRead;
import Producer.Producer;
import Producer.ProducerFileRead;

public class Application {
	static FileRead fileIo;
	static Brocker brokcer;
	private Thread producerT;
	private Thread consumer;

	private void init() {	
		initFileIo();		
		this.producerT = new Thread(new ProducerFileRead(brokcer), "Producer");
		this.consumer = new Thread();
	}
	
	private void initFileIo(){
		this.fileIo = new FileRead();
		this.brokcer = new Brocker(fileIo.getWordSize());			
	}
	
	private void closeResource(){
		producerT.destroy();
		consumer.destroy();
	}
	
	public static void main(String[] args) {
		Application m = new Application();
		m.init();
	
		m.producerT.start();
		m.producerT.run();
		
		m.consumer.start();
		m.consumer.run();
	    
		m.closeResource();	
	}
}
