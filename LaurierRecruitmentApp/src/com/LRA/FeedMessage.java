package com.LRA;

public class FeedMessage {
	private String date, message;
	
	public FeedMessage(String d, String m) {
		this.date = d;
		this.message = m;
	}
	public FeedMessage() {
		this.date = "";
		this.message = "";
	}
	public String toString() {
		return this.message + "\nOn " + this.date;
	}
	public String getMessage() {
		return this.message;
	}
	public String getDate() {
		return this.date;
	}
}
