package Producer;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.regex.Pattern;

import CommonModule.FileIO;

/*
 *  a. 파일에서 각 라인을 읽어온다.
	b. 각 라인의 주어진 단어가 유효한지 정규표현식을 활용해 검사한다. 	
	· 유효한 단어에 대한 설명은 과제설명 항목2 참고
	c. 유효하지 않은 단어들은 처리를 생략한다.
	d. 유효한 단어들은 N개의 파티션으로 나눠서 Consumer에 전달한다.
	· 파티션 수는 프로그램의 실행 argument로 입력 받는다.
	· 동일한 단어는 항상 동일한 파티션에 포함되야 한다.
 */
public class Producer implements Runnable {
	private BlockingQueue<String>[] queue;
	private Hashtable<String, Integer> hash;
	private FileIO fileIo;
	private Object lock;
	private int cnt;
	
	public Producer(BlockingQueue<String>[] queue){
		fileIo = new FileIO();
		fileIo.readFile();
		
		this.lock = new Object();
		this.hash = new Hashtable<>();
		this.queue = queue;
	}

	@Override
	public void run() {
		String str = fileIo.getStr();
		do{			
			if(!disposeSameStr(str)) {
				putStrToQuque(str);		
			}
			
			str = fileIo.getStr();
			
		}while(str.equals(""));
	}
	
	public void setFileIO(FileIO fileIo){
		this.fileIo = fileIo;
	}
	
	//HashTable에서 검사
	private boolean disposeSameStr(String key) {
		if(hash.containsKey(key)) {
			int value = hash.get(key);
			queue[value].add(key);			
			return true;
		}		
		
		return false;
	}
	
	private void putStrToQuque(String key) {
		hash.put(key, cnt++ % 4);
		int value = hash.get(key);
		queue[value].add(key);
	}
}
