package com.hb.bingo.model;

public class EmojiRequestResponse {
	private Long sender;
	private Long receiver;
	private String emoji;

	public Long getSender() {
		return sender;
	}

	public void setSender(Long sender) {
		this.sender = sender;
	}

	public Long getReceiver() {
		return receiver;
	}

	public void setReceiver(Long receiver) {
		this.receiver = receiver;
	}

	public String getEmoji() {
		return emoji;
	}

	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}

	public EmojiRequestResponse(Long sender, Long receiver, String emoji) {
		this.sender = sender;
		this.receiver = receiver;
		this.emoji = emoji;
	}

	@Override
	public String toString() {
		return "Emoji{" + "sender='" + sender + '\'' + ", receiver=" + receiver + ", emoji='" + emoji + '\'' + '}';
	}
}
