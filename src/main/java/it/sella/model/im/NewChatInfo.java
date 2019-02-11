
package it.sella.model.im;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NewChatInfo {

	@SerializedName("status")
	@Expose
	private String status;
	@SerializedName("errors")
	@Expose
	private List<Object> errors = null;
	@SerializedName("requests")
	@Expose
	private Object requests;
	@SerializedName("codes")
	@Expose
	private Object codes;
	@SerializedName("contextChange")
	@Expose
	private Object contextChange;
	@SerializedName("concepts")
	@Expose
	private Object concepts;
	@SerializedName("favorites")
	@Expose
	private Object favorites;
	@SerializedName("chatid")
	@Expose
	private String chatid;
	@SerializedName("chaturl")
	@Expose
	private String chaturl;
	@SerializedName("result")
	@Expose
	private String result;
	@SerializedName("cause")
	@Expose
	private Object cause;
	@SerializedName("licenses")
	@Expose
	private Object licenses;
	@SerializedName("transcript")
	@Expose
	private Object transcript;
	@SerializedName("overTime")
	@Expose
	private String overTime;
	@SerializedName("errorMessageCode")
	@Expose
	private Object errorMessageCode;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Object> getErrors() {
		return errors;
	}

	public void setErrors(List<Object> errors) {
		this.errors = errors;
	}

	public Object getRequests() {
		return requests;
	}

	public void setRequests(Object requests) {
		this.requests = requests;
	}

	public Object getCodes() {
		return codes;
	}

	public void setCodes(Object codes) {
		this.codes = codes;
	}

	public Object getContextChange() {
		return contextChange;
	}

	public void setContextChange(Object contextChange) {
		this.contextChange = contextChange;
	}

	public Object getConcepts() {
		return concepts;
	}

	public void setConcepts(Object concepts) {
		this.concepts = concepts;
	}

	public Object getFavorites() {
		return favorites;
	}

	public void setFavorites(Object favorites) {
		this.favorites = favorites;
	}

	public String getChatid() {
		return chatid;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}

	public String getChaturl() {
		return chaturl;
	}

	public void setChaturl(String chaturl) {
		this.chaturl = chaturl;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Object getCause() {
		return cause;
	}

	public void setCause(Object cause) {
		this.cause = cause;
	}

	public Object getLicenses() {
		return licenses;
	}

	public void setLicenses(Object licenses) {
		this.licenses = licenses;
	}

	public Object getTranscript() {
		return transcript;
	}

	public void setTranscript(Object transcript) {
		this.transcript = transcript;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public Object getErrorMessageCode() {
		return errorMessageCode;
	}

	public void setErrorMessageCode(Object errorMessageCode) {
		this.errorMessageCode = errorMessageCode;
	}
	

}