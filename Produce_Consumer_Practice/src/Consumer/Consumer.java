package Consumer;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

import Broker.Broker;

/*
 *  a. 파티션에서 순차적으로 단어를 1개씩 가져온다.
	b. 단어의 첫 알파벳에 해당하는 파일이 존재하지 않으면 새로 만들고, 존재한다면 기존 파일 끝에 주어진 단어를 추가한다. (대소문자 구분없음)
	예) 단어가 apple 또는 Apple이라면 a.txt 파일에 해당 단어를 append
 */

public class Consumer implements Runnable{
	private final static Logger LOG = Logger.getLogger("FileRead");	
	private Broker broker;
	//private BlockingQueue<String>[] queue;
	private StringBuffer[] saveStr;
	private int count;
	
	public Consumer(Broker broker){
		this.broker = broker;
		this.saveStr = new StringBuffer[26];
	}
	
	@Override
	public void run() {
		String str = getStr(getNextPartNum());
		while(validateCheckQueue()){				
			if(!str.equals("")){
				int index = discriminateStrFirstIndex(str);
				SaveStr(index, str);
			}
			str = getStr(getNextPartNum());
		}
	}
	
	private int getNextPartNum(){
		synchronized (this){
			return count++ % 4;
		}
	}
	
	private String getStr(int partNum){
		String word = broker.getWord(partNum);
		if(word == null){
			return "";
		}
		
		return word;
	}
	
    
    private int discriminateStrFirstIndex(String str){
    	char find = str.charAt(0);
    	
    	if(find >= 65 && find <= 90){
    		return find - 65;
    	}
    	
    	return find - 97;
    }
    
    private void SaveStr(int index, String str){
    	saveStr[index].append(str + "\n");
    }
    
    private boolean validateCheckQueue(){
    	int brockerSize = broker.getQueueSize();
    	for(int i = 0;i < brockerSize;i++){
    		if(broker.isNotEmptyQueue(i)){
    			return false;
    		}
    	}
		return true;
    }
}
