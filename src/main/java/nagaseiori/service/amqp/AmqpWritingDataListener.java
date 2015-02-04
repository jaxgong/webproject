package nagaseiori.service.amqp;

import javax.annotation.Resource;

import nagaseiori.models.amqp.WritingDataProcesser;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.stereotype.Service;

@Service("amqpWritingDataListener")
public class AmqpWritingDataListener implements MessageListener {

	@Resource(name="converterSerializer")
	private SerializerMessageConverter converterSerializer;
	
	/**
	 * 消费队列
	 */
	@Override
	public void onMessage(Message message) {
		WritingDataProcesser processer = (WritingDataProcesser) converterSerializer.fromMessage(message);
		System.out.println("receive and convert" + processer);
	}

}
