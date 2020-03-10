import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import CommonModule.FileIO;
import Consumer.Consumer;
import Producer.Producer;

public class KafkaApplication {
	static FileIO fileIo;
	static BlockingQueue<String>[] queue;
	private Thread produce;
	private Thread consumer;
	
	private void init() {	
		initFileIo();
		
		produce = new Thread(new Producer(queue));
		consumer = new Thread(new Consumer(queue));
		
	}
	
	private void initFileIo(){
		this.fileIo = new FileIO();
		initQueue(fileIo.getWordSize());
		
	}
	
	private void initQueue(int partNum) {
		queue = new LinkedBlockingQueue[partNum];
		
		for(int i = 0;i < partNum;i++) {
			queue[i] = new LinkedBlockingQueue<String>();
		}
	}
	
	private void closeResource(){
		produce.destroy();
		consumer.destroy();
	}
	
	public static void main(String[] args) {
		KafkaApplication m = new KafkaApplication();
		m.init();	
		
		m.produce.start();
		m.produce.run();

		m.consumer.start();
		m.consumer.run();
			
		m.closeResource();	
	}
}
