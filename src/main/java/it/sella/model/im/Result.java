package it.sella.model.im;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

	@SerializedName("sender")
	@Expose
	private String sender;
	@SerializedName("message")
	@Expose
	private String message;
	@SerializedName("answer")
	@Expose
	private String answer;
	@SerializedName("intentName")
	@Expose
	private String intentName;
	@SerializedName("intentCode")
	@Expose
	private String intentCode;
	@SerializedName("action")
	@Expose
	private String action;
	@SerializedName("operatorSkill")
	@Expose
	private String operatorSkill;
	@SerializedName("link")
	@Expose
	private String link;
	@SerializedName("intentArea")
	@Expose
	private String intentArea;
	@SerializedName("url")
	@Expose
	private Object url;
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public String getIntentCode() {
		return intentCode;
	}
	public void setIntentCode(String intentCode) {
		this.intentCode = intentCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getOperatorSkill() {
		return operatorSkill;
	}
	public void setOperatorSkill(String operatorSkill) {
		this.operatorSkill = operatorSkill;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIntentArea() {
		return intentArea;
	}
	public void setIntentArea(String intentArea) {
		this.intentArea = intentArea;
	}
	public Object getUrl() {
		return url;
	}
	public void setUrl(Object url) {
		this.url = url;
	}
	
	@Override
	public String toString() {
		return "Result [sender=" + sender + ", message=" + message + ", answer=" + answer + ", intentName=" + intentName
				+ ", intentCode=" + intentCode + ", action=" + action + ", operatorSkill=" + operatorSkill + ", link="
				+ link + ", intentArea=" + intentArea + ", url=" + url + "]";
	}	
	
}