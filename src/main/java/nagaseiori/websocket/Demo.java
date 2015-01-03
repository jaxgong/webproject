package nagaseiori.websocket;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@ServerEndpoint(value = "/test")
public class Demo {

	private static Logger logger = LoggerFactory.getLogger(Demo.class);
	 
    static Map<String,Session> sessionMap = new ConcurrentHashMap<String,Session>();
    
    @OnOpen
    public void onOpen(Session session) {
    	logger.info(session.getId() + " connect ");
        sessionMap.put(session.getId(), session); 
    }
    
    /**
     * 监听web端发送过来的消息
     * @param unscrambledWord
     * @param session
     * unscrambledWord格式如下
     {
			"apiName":"MessageAPIConstants.API_NAME_LOGIN,MessageAPIConstants.API_NAME_SEND_MESSAGE",
			"messageType":"",
			"sessionId":"对应后台用户会话id",
			"imReceiverId":"对应app用户id",
			"text":"消息内容",
			"handoverSessionId" : "被移交客服httpSessionId", //移交请求时有这个参数
			"userId" : "超时app用户id数组"
	 }
     */
    @OnMessage
    public void onMessage(String unscrambledWord, Session session) {
    	/**
    	 * @TODO 
    	 */
    	//JSONObject jsonParams = null;
    	//jsonParams = JSONObject.parseObject(unscrambledWord);
    }
     
    /**
     * 广播给所有人(慎用)
     * @param message
     */
    public static void broadcastAll(String type,String message){
        Set<Map.Entry<String,Session>> set = sessionMap.entrySet();
        for(Map.Entry<String,Session> i: set){
            try {
                i.getValue().getBasicRemote().sendText("{type:'"+type+"',text:'"+message+"'}");
            } catch (Exception e) {
                logger.error("broadcastAll error",e);
            }
        }
    }
 
	/**
	 * 推送消息到浏览器
	 * 
	 * @param type
	 * @param sessionId
	 * @param message
	 *            推送到浏览器的消息格式为 { "errorCode":0, "errorMessage":"", "data":{
	 *            "type":
	 *            "MessageTypeConstants.LOGIN ,MessageTypeConstants.TEXT,MessageTypeConstants.IMAGE,MessageTypeConstants.AUIDO"
	 *            , "businessId":"对应app用户id", "sessionId":"对应后台用户会话id",
	 *            "text":"消息内容", "userInfo":{ "avatar":
	 *            "http://121.13.249.210:8003/niwo/201410/31/20141031160218_9832.jpg"
	 *            , "borrower": 2, "businessId":
	 *            "431d5fe3-d7c4-47b6-9f3d-071c26718310", "gender": 2,
	 *            "guarantee": 2, "identity": 1, "introduction": "", "nickName":
	 *            "张无忌", "userId": 1 } } }
	 */
    public static void pushMessage(String sessionId, String message){
    	logger.info("pushmessage to web:"+message);
    	Session session  = sessionMap.get(sessionId);
    	try {
    		if(session.isOpen()){
    			session.getBasicRemote().sendText(message);
    		}
		} catch (IOException e) {
			e.printStackTrace();
			 logger.error("pushMessage error",e);
		}
    }
    
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        sessionMap.remove(session.getId());
        try {
        	// logout
		} catch (Exception e) {
			logger.error("logout error",e);
		}
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }
     
    @OnError
    public void error(Session session, java.lang.Throwable throwable){
        sessionMap.remove(session.getId());
        logger.error("session "+session.getId()+" error:"+throwable);
    }
    
    public static boolean isOpen(String sessionId){
    	if(sessionId == null) return false;
    	Session session = sessionMap.get(sessionId);
    	return session != null ?  session.isOpen() : false;
    }
}
