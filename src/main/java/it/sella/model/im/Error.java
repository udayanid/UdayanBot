
package it.sella.model.im;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Error {

	@SerializedName("messageCode")
	@Expose
	private String messageCode;
	@SerializedName("messageParams")
	@Expose
	private List<Object> messageParams = null;
	@SerializedName("messageFEFields")
	@Expose
	private String messageFEFields;

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public List<Object> getMessageParams() {
		return messageParams;
	}

	public void setMessageParams(List<Object> messageParams) {
		this.messageParams = messageParams;
	}

	public String getMessageFEFields() {
		return messageFEFields;
	}

	public void setMessageFEFields(String messageFEFields) {
		this.messageFEFields = messageFEFields;
	}

	@Override
	public String toString() {
		return "Error [messageCode=" + messageCode + ", messageParams=" + messageParams + ", messageFEFields="
				+ messageFEFields + "]";
	}
	

}
