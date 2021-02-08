package com.hb.bingo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "emoji")
@EntityListeners(AuditingEntityListener.class)
public class EmojiEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5206684430556869576L;

	public EmojiEntity() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Long sender;
	Long receiver;
	String sticker;
	@ManyToOne
	private RoomEntity room;
	private boolean alreadySent;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public RoomEntity getRoom() {
		return room;
	}

	public void setRoom(RoomEntity room) {
		this.room = room;
	}

	public boolean isAlreadySent() {
		return alreadySent;
	}

	public void setAlreadySent(boolean alreadySent) {
		this.alreadySent = alreadySent;
	}

	public EmojiEntity(Long sender, Long receiver, RoomEntity room, boolean alreadySent, String sticker) {
		this.sender = sender;
		this.receiver = receiver;
		this.room = room;
		this.alreadySent = alreadySent;
		this.sticker = sticker;
	}

	public String getSticker() {
		return sticker;
	}

	public void setSticker(String sticker) {
		this.sticker = sticker;
	}

	@Override
	public String toString() {
		return "EmojiEntity [id=" + id + ", sender=" + sender + ", receiver=" + receiver + ", sticker=" + sticker
				+ ", room=" + room + ", alreadySent=" + alreadySent + "]";
	}

}
