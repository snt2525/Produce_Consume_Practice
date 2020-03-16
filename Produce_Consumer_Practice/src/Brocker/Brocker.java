package Brocker;

import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Brocker {
	private BlockingQueue<String>[] queue;
	private int queueSize;
	
	public Brocker(int size) {
		this.queueSize = size;
		this.queue = new LinkedBlockingQueue[size];
		for(int i = 0;i < size;i++)
			queue[i] = new LinkedBlockingQueue();
	}
	
	public String getWord(int index){
		return queue[index].poll();
	}
	
	public void putWord(String str, int index){
		queue[index].add(str);
	}	
	
	public int getQueueSize(){
		return queueSize;
	}
	
	public boolean isNotEmptyQueue(int index){
		return !queue[index].isEmpty();
	}
	
}
