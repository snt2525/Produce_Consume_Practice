package Producer;

import java.util.LinkedList;
import java.util.Queue;

public class WordQueue {
	private Queue<String> wordQueue; 
	
	WordQueue(){
		this.wordQueue = new LinkedList<String>();
	}
	
	public void addFileWord(String word) {
		wordQueue.add(word);
	}
	
	public String pollFileWord() {
		return wordQueue.poll();
	}
}
