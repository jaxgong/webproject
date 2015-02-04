package nagaseiori.models.amqp;

import java.io.Serializable;

public class WritingDataProcesser implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8133187119688613754L;

	private int msgId;
	private int userId;
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "WritingDataProcesser [msgId=" + msgId + ", userId=" + userId + "]";
	}
	
}
