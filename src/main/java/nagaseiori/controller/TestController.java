package nagaseiori.controller;

import javax.servlet.http.HttpServletResponse;

import nagaseiori.commons.utils.RtnUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController extends BaseController {

	@RequestMapping(value="/test/")
	public void test(@PathVariable int objUserId, HttpServletResponse response) throws Exception{
		responseJson(response, RtnUtil.getOkDataRtn("ok"));
	}
}
