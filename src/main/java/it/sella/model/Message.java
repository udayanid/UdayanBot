package it.sella.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Message {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Message [mid=");
		builder.append(mid);
		builder.append(", seq=");
		builder.append(seq);
		builder.append(", text=");
		builder.append(text);
		builder.append("]");
		return builder.toString();
	}

	@SerializedName("mid")
	@Expose
	private String mid;
	@SerializedName("seq")
	@Expose
	private Integer seq;
	@SerializedName("text")
	@Expose
	private String text;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}