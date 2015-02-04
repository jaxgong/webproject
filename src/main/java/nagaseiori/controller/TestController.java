package nagaseiori.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import nagaseiori.commons.utils.RtnUtil;
import nagaseiori.models.amqp.WritingDataProcesser;
import nagaseiori.service.amqp.AmqpWritingDataService;

import org.springframework.amqp.support.converter.SerializerMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Resource(name="amqpWritingDataService")
	private AmqpWritingDataService amqpWritingDataService;
	
	@RequestMapping(value="testRabbitSend")
	public void testRabbitSend(HttpServletResponse response) throws Exception{
		WritingDataProcesser message = new WritingDataProcesser();
		message.setUserId(1);
		message.setMsgId(2);
		amqpWritingDataService.writeData2Mq(message);
		responseJson(response, RtnUtil.getOkDataRtn("ok"));
	}
}
