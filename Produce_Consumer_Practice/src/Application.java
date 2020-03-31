import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import Broker.Broker;
import Consumer.Consumer;
import Producer.Producer;
import Producer.ProducerFileRead;

public class Application {
	static Broker brokcer;
	private Thread producerT;
	private Thread consumer;

	private void init() {	
		this.producerT = new Thread(new ProducerFileRead(brokcer), "Producer");
		this.consumer = new Thread();
	}
	
	private void closeResource(){
		producerT.destroy();
		consumer.destroy();
	}
	
	public static void main(String[] args) {
		Application m = new Application();
		m.init();
	
		m.producerT.start();
		m.producerT.run();
		
		m.consumer.start();
		m.consumer.run();
	    
		m.closeResource();	
	}
}
