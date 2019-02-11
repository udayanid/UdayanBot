package it.sella;

public class BotSession {

	private String fbSenderId;
	private String fbReceipientId;
	private String imChatId;
	private String cokkieInfo;

	public String getCokkieInfo() {
		return cokkieInfo;
	}

	public void setCokkieInfo(String cokkieInfo) {
		this.cokkieInfo = cokkieInfo;
	}

	public String getFbSenderId() {
		return fbSenderId;
	}

	public void setFbSenderId(String fbSenderId) {
		this.fbSenderId = fbSenderId;
	}

	public String getFbReceipientId() {
		return fbReceipientId;
	}

	public void setFbReceipientId(String fbReceipientId) {
		this.fbReceipientId = fbReceipientId;
	}

	public String getImChatId() {
		return imChatId;
	}

	public void setImChatId(String imChatId) {
		this.imChatId = imChatId;
	}

	@Override
	public String toString() {
		return "BotSession [fbSenderId=" + fbSenderId + ", fbReceipientId=" + fbReceipientId + ", imChatId=" + imChatId
				+ ", cokkieInfo=" + cokkieInfo + "]";
	}
	

	
}
