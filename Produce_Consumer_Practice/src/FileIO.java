import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.regex.Pattern;

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
            	if(validCheckStr(line)){
        			str.add(line);
            	}
            }
        
        }catch (FileNotFoundException e) {
        	 System.out.println(e);
        }catch(IOException e){
            System.out.println(e);
        }
	}
	
	public String getStr() {
		return str.poll();
	}
	
	public int getWordSize(){
		return str.size();
	}
	
	private boolean validCheckStr(String word) {
		String pattern = "^[a-zA-Z0-9]*$";
		String firstIndexStr = word.substring(0, 1);
        boolean vaildPattern = Pattern.matches(pattern, firstIndexStr);
        
        if (vaildPattern) {
            return true;
        }
        
	    return false;
	}
}
