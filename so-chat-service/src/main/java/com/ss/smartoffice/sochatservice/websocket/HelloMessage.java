package com.ss.smartoffice.sochatservice.websocket;

public class HelloMessage {

	private String fromName;
	private String toName;
	private String messageContent;

	public HelloMessage(String fromName, String toName, String messageContent) {
		super();
		this.fromName = fromName;
		this.toName = toName;
		this.messageContent = messageContent;
	}

	@Override
	public String toString() {
		return "HelloMessage [fromName=" + fromName + ", toName=" + toName + ", messageContent=" + messageContent + "]";
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

	public String getToName() {
		return toName;
	}

	public void setToName(String toName) {
		this.toName = toName;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public HelloMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

}
