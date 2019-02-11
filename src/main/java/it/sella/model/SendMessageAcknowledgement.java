
package it.sella.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SendMessageAcknowledgement {

@SerializedName("recipient_id")
@Expose
private String recipientId;
@SerializedName("message_id")
@Expose
private String messageId;

public String getRecipientId() {
return recipientId;
}

public void setRecipientId(String recipientId) {
this.recipientId = recipientId;
}

public String getMessageId() {
return messageId;
}

public void setMessageId(String messageId) {
this.messageId = messageId;
}

}