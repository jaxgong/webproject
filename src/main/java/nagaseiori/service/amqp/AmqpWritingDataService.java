package nagaseiori.service.amqp;

import javax.annotation.Resource;

import nagaseiori.models.amqp.WritingDataProcesser;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

@Service("amqpWritingDataService")
public class AmqpWritingDataService implements MessageListener{

	@Resource(name="amqpTemplate")
	private AmqpTemplate amqpTemplate;
	
	@Override
	public void onMessage(Message message) {
		message.getBody();
		
	    WritingDataProcesser processer = (WritingDataProcesser) amqpTemplate.receiveAndConvert("writingdbtest_queue");
		System.out.println("receive and convert" + message);
	}

	public void writeData2Mq(WritingDataProcesser message){
		amqpTemplate.convertAndSend("writing_queue", "", message);
		
	}
}
