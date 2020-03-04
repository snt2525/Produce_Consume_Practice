import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class FileIO {
	private BlockingQueue<String> str;
	private File file;
	
	public FileIO(){
		str = new LinkedBlockingQueue<>();
	}
	
	public void readFile(){
		File file = new File("word.txt");
		
		try(BufferedReader bufReader = new BufferedReader(new FileReader(file));){
            //입력 스트림 생성
            String line = "";
            while((line = bufReader.readLine()) != null){
            	 str.add(line);
            }
        
        }catch (FileNotFoundException e) {
        	 System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
	}
	
	public String getStr(){
		return str.poll();
	}
}
