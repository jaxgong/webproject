package nagaseiori.service.amqp;

import javax.annotation.Resource;

import nagaseiori.models.amqp.WritingDataProcesser;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service("amqpWritingDataService")
public class AmqpWritingDataService{

	@Resource(name="amqpTemplate")
	private AmqpTemplate amqpTemplate;
	
	/**
	 * 生产者写入数据到队列
	 * @param message
	 */
	public void writeData2Mq(WritingDataProcesser message){
		amqpTemplate.convertAndSend("writing_queue", "", message);
	}
}
