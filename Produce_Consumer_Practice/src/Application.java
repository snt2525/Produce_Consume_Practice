import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Brocker.Brocker;
import CommonUtil.FileRead;
import Consumer.Consumer;
import Producer.Producer;

public class Application {
	static FileRead fileIo;
	static Brocker blockingQueue;
	private Thread produce;
	private Thread consumer;

	private void init() {	
		initFileIo();		
		this.produce = new Thread();
		this.consumer = new Thread();
	}
	
	private void initFileIo(){
		this.fileIo = new FileRead();
		this.blockingQueue = new Brocker(fileIo.getWordSize());			
	}
	
	private void closeResource(){
		produce.destroy();
		consumer.destroy();
	}
	
	public static void main(String[] args) {
		Application m = new Application();
		m.init();
		
		m.produce.start();
		m.produce.run();

		m.consumer.start();
		m.consumer.run();
			
		m.closeResource();	
	}
}
