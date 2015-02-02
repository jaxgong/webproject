package nagaseiori.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import nagaseiori.commons.utils.RtnUtil;
import nagaseiori.models.amqp.WritingDataProcesser;
import nagaseiori.service.amqp.AmqpWritingDataService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@Resource(name="amqpWritingDataService")
	private AmqpWritingDataService amqpWritingDataService;
	
	@RequestMapping(value="testRabbitSend")
	public void test(@PathVariable int objUserId, HttpServletResponse response) throws Exception{
		System.out.println(1111);
		WritingDataProcesser message = new WritingDataProcesser();
		amqpWritingDataService.writeData2Mq(message);
		responseJson(response, RtnUtil.getOkDataRtn("ok"));
	}
}
