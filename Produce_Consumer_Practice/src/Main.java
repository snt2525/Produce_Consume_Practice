import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {
	static FileIO fileIo;
	static BlockingQueue<String>[] queue;
	private Thread produce;
	private Thread consumer;
	
	private void init() {	
		this.fileIo = new FileIO();
		initQueue(fileIo.getWordSize());
		produce = new Thread(new Produce(queue));
		consumer = new Thread(new Consumer(queue));
		
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
		Main m = new Main();
		m.init();	
		
		m.produce.start();
		m.produce.run();

		m.consumer.start();
		m.consumer.run();
			
		m.closeResource();	
	}
}
