package nagaseiori.commons.utils;

import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import nagaseiori.commons.constants.RtnConstants;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * 接口请求返回数据
 */
public class RtnUtil {

	private static SerializerFeature[] features = {SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect};
	
    /**
     * 获得对象的JSON表示
     */
    public static String getDataJsonObject(Object data) {
        return JSONObject.toJSONString(data);
    }
    
    /**
     * 获得只返回0(成功的验证码)
     */
    public static String getRtnOk() {
        Map<String, Object> object = new LinkedHashMap<String, Object>(2);
        object.put(RtnConstants.RTN, RtnConstants.OK);
        return JSONObject.toJSONString(object);
    }
    
    /**
     * 获得只返回-1(失败的验证码)
     */
    public static String getRtnFail() {
        Map<String, Object> object = new LinkedHashMap<String, Object>(2);
        object.put(RtnConstants.RTN, RtnConstants.FAIL);
        return JSONObject.toJSONString(object);
    }
    
    /**
     * 获得请求状态码
     */
    public static String getOnlyRtn(int rtn) {
        Map<String, Object> object = new LinkedHashMap<String, Object>(2);
        object.put(RtnConstants.RTN, rtn);
        return JSONObject.toJSONString(object, features);
    }

    /**
     * 获得指定的rtn和指定的data的JSON
     */
    public static String getRtnData(int rtn, Object data) {
        Map<String, Object> object = new LinkedHashMap<String, Object>(2);
        object.put(RtnConstants.RTN, rtn);
        object.put(RtnConstants.DATA, data);
        return JSONObject.toJSONString(object, features);
    }

    /**
     * 获取返回 指定的rtn、rtnMsg的json
     * @param rtn 返回码
     * @param rtnMsg 给前端展示提示框的文本信息
     * @return
     */
    public static String getRtnMsg(int rtn, String rtnMsg){
    	Map<String, Object> object = new LinkedHashMap<String, Object>(3);
        object.put(RtnConstants.RTN, rtn);
        object.put(RtnConstants.RTN_MSG, rtnMsg);
        return JSONObject.toJSONString(object, features);
    }
    
    /**
     * 获得指定rtn、rtnMsg和data的JSON
     * 
     * @param rtn 返回码
     * @param rtnMsg 给前端展示提示框的文本信息
     * @param data 数据
     * @return JSON表示
     */
    public static String getRtnMsgAndData(int rtn, String rtnMsg, Object data) {
        Map<String, Object> object = new LinkedHashMap<String, Object>(3);
        object.put(RtnConstants.RTN, rtn);
        object.put(RtnConstants.RTN_MSG, rtnMsg);
        object.put(RtnConstants.DATA, data);
        return JSONObject.toJSONString(object, features);
    }

    public static String getRtnError(int rtn, String rtnMsg) {
        Map<String, Object> object = new LinkedHashMap<String, Object>(3);
        object.put(RtnConstants.RTN, rtn);
        object.put(RtnConstants.RTN_MSG, rtnMsg);
        return JSONObject.toJSONString(object, features);
    }
    
    /**
     * 请求成功返回数据
     * @param data
     * @return
     */
    public static String getOkDataRtn(Object data){
    	return getRtnData(RtnConstants.OK, data);
    }
    
    /**
     * 请求成功返回提醒信息
     * @param rtnMsg
     * @return
     */
    public static String getOkMsgRtn(String rtnMsg){
    	return getRtnMsg(RtnConstants.OK, rtnMsg);
    }
    
    /**
     * 请求失败返回数据
     * @param data
     * @return
     */
    public static String getFailDataRtn(Object data){
    	return getRtnData(RtnConstants.FAIL, data);
    }
    
    /**
     * 请求失败返回提醒信息
     * @param rtnMsg
     * @return
     */
    public static String getFailMsgRtn(String rtnMsg){
    	return getRtnMsg(RtnConstants.FAIL, rtnMsg);
    }
    
    public static void main(String[] args) {
    	System.out.println(getRtnOk());
	}
    
    /**
     * 返回数据流
     * @param response
     * @param resp
     * @throws Exception
     */
    public static void writeResponse(HttpServletResponse response, String resp) throws Exception {
        PrintWriter writer = null;
        try {
        	response.setContentType("application/json;charset=UTF-8");
        	// response.setContentType("text/html;charset=UTF-8");
        	response.setCharacterEncoding("UTF-8");
            writer = response.getWriter();
            writer.write(resp);
            writer.flush();
        } catch (Exception e) {
            throw e;
        } finally {
            CloseableUtil.close(writer);
        }
    }
    
}