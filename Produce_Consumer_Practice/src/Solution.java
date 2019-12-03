import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Solution {
	static Scanner sc;
	static int partNum;
	
	static void init() {
		sc = new Scanner(System.in);
		partNum = sc.nextInt();
	}
	
	static void initQueue(BlockingQueue<String>[] queue) {
		for(int i = 0;i < partNum;i++) {
			queue[i] = new LinkedBlockingQueue<String>();
		}
	}
	
	public static void main(String[] args) {
		init();
		
		BlockingQueue<String>[] queue = new LinkedBlockingQueue[partNum];
		initQueue(queue);
		
		Thread produce = new Thread(new Produce(queue));
		Thread consumer = new Thread(new Consumer(queue));
		
		produce.start();
		consumer.start();
	}
}
