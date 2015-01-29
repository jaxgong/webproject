package nagaseiori.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nagaseiori.commons.annotations.NoRequireLogin;
import nagaseiori.commons.constants.DefaultConstants;
import nagaseiori.commons.utils.AnnontationUtils;
import nagaseiori.commons.utils.CookieUtils;
import nagaseiori.commons.utils.Log;

import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 增加错误代码显示 10X
 * 
 * <code>
 *  101  accessToken为空
 *   102   没找到用户token信息（没推过来）
 *   103   缓存内的用户token信息错误
 * </code>
 * 
 * @author jiacan
 * 
 */
public class UserLoginInterceptor implements HandlerInterceptor {

	private Logger logger = Log.getLogger(UserLoginInterceptor.class);

	private final String START_TIME = "_STARTTIME_";

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute(START_TIME, startTime);
		HandlerMethod method = (HandlerMethod) handler;
		//Method proxyMethod = method.getMethod();
		Object controller = method.getBean();
		HttpSession session = request.getSession();
		Object currentUserObj = session.getAttribute(DefaultConstants.CURRENT_USER);
		if(currentUserObj == null){
			String imToken = request.getParameter("imToken");
			if(imToken == null){
				imToken = CookieUtils.getCookie(request, "imToken");
			}
			if (imToken == null) {
				logger.error("invalid request  request url:  " + request.getRequestURL() + "?" + request.getQueryString());
			} else {
//				String userIdStr = DesCrypto.decrypt(imToken, DefaultConstants.DES_CRYPTO_SECRET_APP);
//				int userId = NumberUtils.toInt(userIdStr);
//				Map<String, Object> syncUserMap = userInfoService.findSyncUserByCondition("userId", userId);
//				if(syncUserMap != null){
//					currentUserObj = syncUserMap;
//				}else{
//					logger.error("no such user");
//				}
			}
		}
		final NoRequireLogin noRequire = AnnontationUtils.getFromMethedOrType(NoRequireLogin.class, method);
		if(noRequire == null && currentUserObj == null){
			return false;
		}
		if(currentUserObj != null){
			session.setAttribute(DefaultConstants.CURRENT_USER, currentUserObj);
			Map<String, Object> syncUserMap = (Map<String, Object>) currentUserObj;
			int currentUserId = NumberUtils.toInt(syncUserMap.get("userId").toString());
			((CurrentUserAware) controller).setCurrentUserId(currentUserId);
			String businessId = syncUserMap.get("businessId").toString();
			((CurrentUserAware) controller).setBusinessId(businessId);
		}else{
			((CurrentUserAware) controller).setCurrentUserId(0);
			((CurrentUserAware) controller).setBusinessId("");
		}
		return true;
	}

	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3) throws Exception {
		long startTime = (Long) request.getAttribute(START_TIME);
		long executeTime = System.currentTimeMillis() - startTime;
		if (executeTime > 1000) {
			logger.warn("###访问超过1S( 耗时：" + executeTime + " ms)，URL=" + request.getRequestURL() + " 参数:" + request.getQueryString());
		}
	}
}
