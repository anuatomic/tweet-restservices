package com.ap.model;

public class Tweet {
	private Long tweetid;
	private Long inReplyToUserId;
	private String tweetText;

	public Tweet(Long tweetid, Long inReplyToUserId, String tweetText) {
		this.tweetid = tweetid;
		this.inReplyToUserId = inReplyToUserId;
		this.tweetText = tweetText;
	}

	@Override
	public String toString() {
		return "Tweet [tweetid=" + tweetid + ", inReplyToUserId=" + inReplyToUserId + ", tweetText=" + tweetText + "]";
	}

	public Tweet(Long tweetid, String tweetText) {
		this.tweetid = tweetid;
		this.inReplyToUserId = inReplyToUserId;
		this.tweetText = tweetText;
	}

	public Tweet() {

	}

	public Long getTweetid() {
		return tweetid;
	}

	public Long getInReplyToUserId() {
		return inReplyToUserId;
	}

	public String getTweetText() {
		return tweetText;
	}

	public void setTweetid(Long tweetid) {
		this.tweetid = tweetid;
	}

	public void setInReplyToUserId(Long inReplyToUserId) {
		this.inReplyToUserId = inReplyToUserId;
	}

	public void setTweetText(String tweetText) {
		this.tweetText = tweetText;
	}

}
