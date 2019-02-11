package it.sella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sender {

	@SerializedName("id")
	@Expose
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sender [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

}