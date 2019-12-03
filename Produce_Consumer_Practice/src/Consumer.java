import java.util.concurrent.BlockingQueue;
/*
 *  a. 파티션에서 순차적으로 단어를 1개씩 가져온다.
	b. 단어의 첫 알파벳에 해당하는 파일이 존재하지 않으면 새로 만들고, 존재한다면 기존 파일 끝에 주어진 단어를 추가한다. (대소문자 구분없음)
	예) 단어가 apple 또는 Apple이라면 a.txt 파일에 해당 단어를 append
 */
public class Consumer implements Runnable{
	private BlockingQueue<String>[] queue;
	
	Consumer(BlockingQueue<String>[] queue){
		this.queue = queue;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
