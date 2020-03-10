package Brocker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueBrocker {
	private BlockingQueue<String> queue;
	
	public BlockingQueueBrocker(int size) {
		this.queue = new LinkedBlockingQueue<String>(size);
	}
	
	public String getStr(int index){
		return queue.poll();
	}
	
	public void putStr(String str, int index){
		queue.add(str);
	}
}
