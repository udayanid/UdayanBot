
package it.sella.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Entry {

	@SerializedName("id")
	@Expose
	private String id;
	@SerializedName("time")
	@Expose
	private Long time;
	@SerializedName("messaging")
	@Expose
	private List<Messaging> messaging = null;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Entry [id=");
		builder.append(id);
		builder.append(", time=");
		builder.append(time);
		builder.append(", messaging=");
		builder.append(messaging);
		builder.append("]");
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}

	public List<Messaging> getMessaging() {
		return messaging;
	}

	public void setMessaging(List<Messaging> messaging) {
		this.messaging = messaging;
	}

}
