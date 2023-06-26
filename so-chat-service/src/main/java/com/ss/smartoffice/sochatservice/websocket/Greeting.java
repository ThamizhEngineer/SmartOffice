package com.ss.smartoffice.sochatservice.websocket;

public class Greeting {
	private String fromName;
	private String toName;
	private String messageContent;
	private String time;

	public Greeting() {
		super();
		// TODO Auto-generated constructor stub
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Greeting [fromName=" + fromName + ", toName=" + toName + ", messageContent=" + messageContent
				+ ", time=" + time + "]";
	}

	public Greeting(String fromName, String toName, String messageContent, String time) {
		super();
		this.fromName = fromName;
		this.toName = toName;
		this.messageContent = messageContent;
		this.time = time;
	}

}
