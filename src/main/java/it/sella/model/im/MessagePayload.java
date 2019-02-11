package it.sella.model.im;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MessagePayload {

	@SerializedName("action")
	@Expose
	private String action;
	@SerializedName("chatid")
	@Expose
	private String chatid;
	@SerializedName("idevent")
	@Expose
	private String idevent;
	@SerializedName("sourceIntentCode")
	@Expose
	private String sourceIntentCode;
	@SerializedName("eventdata")
	@Expose
	private List<Eventdatum> eventdata = null;

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getChatid() {
		return chatid;
	}

	public void setChatid(String chatid) {
		this.chatid = chatid;
	}

	public String getIdevent() {
		return idevent;
	}

	public void setIdevent(String idevent) {
		this.idevent = idevent;
	}

	public String getSourceIntentCode() {
		return sourceIntentCode;
	}

	public void setSourceIntentCode(String sourceIntentCode) {
		this.sourceIntentCode = sourceIntentCode;
	}

	public List<Eventdatum> getEventdata() {
		return eventdata;
	}

	public void setEventdata(List<Eventdatum> eventdata) {
		this.eventdata = eventdata;
	}

	public void addEventDatum(Eventdatum eventdatum) {
		if (this.eventdata == null) {
			this.eventdata = new ArrayList<Eventdatum>();

		}
		this.eventdata.add(eventdatum);
	}

	@Override
	public String toString() {
		return "MessagePayload [action=" + action + ", chatid=" + chatid + ", idevent=" + idevent
				+ ", sourceIntentCode=" + sourceIntentCode + ", eventdata=" + eventdata + "]";
	}
	

}