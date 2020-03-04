import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution {
	static BlockingQueue<String>[] queue;
	static Thread produce;
	static Thread consumer;
	static Scanner sc;
	
	static void init() {
		sc = new Scanner(System.in);
		
		int partNum = sc.nextInt();		
		initQueue(partNum);
		produce = new Thread(new Produce(queue));
		consumer = new Thread(new Consumer(queue));
		
	}
	
	static void initQueue(int partNum) {
		queue = new LinkedBlockingQueue[partNum];
		
		for(int i = 0;i < partNum;i++) {
			queue[i] = new LinkedBlockingQueue<String>();
		}
	}
	
	static void closeResource(){
		produce.destroy();
		consumer.destroy();
		
		sc.close();
	}
	
	public static void main(String[] args) {
		init();	
	
		produce.start();
		produce.run();
		
		consumer.start();
		consumer.run();
			
		closeResource();	
	}
}
