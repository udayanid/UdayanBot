package it.sella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Recipient {

	@SerializedName("id")
	@Expose
	private String id;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Recipient [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
