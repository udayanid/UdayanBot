
package it.sella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Postback {

@SerializedName("payload")
@Expose
private String payload;
@SerializedName("title")
@Expose
private String title;

public String getPayload() {
return payload;
}

public void setPayload(String payload) {
this.payload = payload;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Postback [payload=");
	builder.append(payload);
	builder.append(", title=");
	builder.append(title);
	builder.append("]");
	return builder.toString();
}

}