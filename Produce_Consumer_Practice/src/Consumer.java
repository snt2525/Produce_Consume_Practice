import java.util.concurrent.BlockingQueue;

/*
 *  a. 파티션에서 순차적으로 단어를 1개씩 가져온다.
	b. 단어의 첫 알파벳에 해당하는 파일이 존재하지 않으면 새로 만들고, 존재한다면 기존 파일 끝에 주어진 단어를 추가한다. (대소문자 구분없음)
	예) 단어가 apple 또는 Apple이라면 a.txt 파일에 해당 단어를 append
 */

public class Consumer implements Runnable{
	private BlockingQueue<String>[] queue;
	private StringBuffer[] save;
	private int cnt;
	
	Consumer(BlockingQueue<String>[] queue){
		this.queue = queue;
		this.save = new StringBuffer[26];
	}
	
	@Override
	public void run() {
		do{			
			String str = getStr(getNextPartNum());
			
			if(!str.equals("")){
				int index = discriminateStrFirstIndex(str);
				SaveStr(index, str);
			}
			
		}while(validateCheckQueue());	
		
	}
	
	private int getNextPartNum(){
		synchronized (this){
			return cnt++ % 4;
		}
	}
	
	private String getStr(int partNum){
		if(checkPartNull(partNum)){
			return "";
		}
		
		return pollStr(partNum);
	}
	
	private boolean checkPartNull(int partNum){
		return queue[partNum].isEmpty();
	}
	
    private String pollStr(int partNum){
    	return queue[partNum].poll();
    }
    
    private int discriminateStrFirstIndex(String str){
    	char find = str.charAt(0);
    	
    	if(find >= 65 && find <= 90){
    		return find - 65;
    	}
    	
    	return find - 97;
    }
    
    private void SaveStr(int index, String str){
    	save[index].append(str + "\n");
    }
    
    private boolean validateCheckQueue(){
    	int qLength = queue.length;
    	for(int i = 0;i < qLength;i++){
    		if(!queue[i].isEmpty()){
    			return true;
    		}
    	}
    	return false;
    }

}