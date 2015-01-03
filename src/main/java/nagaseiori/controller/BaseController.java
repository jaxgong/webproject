/** ******BaseController.java*****/
/**
 *Copyright
 *
 **/
package nagaseiori.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nagaseiori.commons.utils.PageHolder;
import nagaseiori.commons.utils.RtnUtil;
import nagaseiori.interceptor.CurrentUserAware;
import nagaseiori.models.user.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @describe: <pre>
 * </pre>
 */
public abstract class BaseController implements CurrentUserAware{
	
	protected static final ThreadLocal<UserInfo> userInfoThreadLocal = new ThreadLocal<UserInfo>();
	
	protected static final ThreadLocal<Integer> userIdThreadLocal = new ThreadLocal<Integer>();
	
	protected static final ThreadLocal<String> businessIdThreadLocal = new ThreadLocal<String>();
	
	public void setCurrentUser(UserInfo userInfo){
		userInfoThreadLocal.set(userInfo);
	}
	
	public void setCurrentUserId(int userId){
		userIdThreadLocal.set(userId);
	}
	
	public void setBusinessId(String businessId){
		businessIdThreadLocal.set(businessId);
	}
	
	@Autowired
	protected HttpServletRequest request;
	
	protected Map<String, Object> rtnPageData(PageHolder<?> pageHolder){
		Map<String, Object> rtnMap = new HashMap<String, Object>();
		rtnMap.put("rowCount", pageHolder.getRowCount());
		rtnMap.put("list", pageHolder.getList());
		rtnMap.put("isFirstPage", pageHolder.isFirstPage());
		rtnMap.put("isLastPage", pageHolder.isLastPage());
		rtnMap.put("pageSize", pageHolder.getPageSize());
		rtnMap.put("pageIndex", pageHolder.getPageIndex());
		return rtnMap;
	}
    
    /**
     * 获取IP地址
     * 
     * @param request
     * @return
     */
    public String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
    
    public static final String COOKIE_UID = "userId";
    
    @InitBinder  
    protected  void initBinder(WebDataBinder binder) {  
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));  
    }
    
    @ResponseBody
    protected void responseJson(HttpServletResponse response, String resp) throws Exception {
    	RtnUtil.writeResponse(response, resp);
    }

    /** 
     * 用于处理异常的 
     * @return 
     */  
    @ExceptionHandler
    public void exception(Exception e, HttpServletResponse response) {  
        try {
        	e.printStackTrace();
        	RtnUtil.writeResponse(response, RtnUtil.getFailMsgRtn("小毛驴抽风了，一会儿就好！"));
		} catch (Exception e2) {
			
		}
    }
    
    
}
