package Brocker;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueBrocker {
	private BlockingQueue<String>[] queue;
	
	BlockingQueueBrocker(int size) {
		this.queue = new LinkedBlockingQueue[size];
	}
	
	public String getStr(int index){
		return queue[index].poll();
	}
	
	public void putStr(String str, int index){
		queue[index].add(str);
	}
}
