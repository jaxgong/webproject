package nagaseiori.controller;

import javax.servlet.http.HttpServletResponse;

import nagaseiori.commons.utils.RtnUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

	@RequestMapping(value="testApnsPush")
	public void testApnsPush(HttpServletResponse response) throws Exception{
		responseJson(response, RtnUtil.getOkDataRtn("ok"));
	}
}
