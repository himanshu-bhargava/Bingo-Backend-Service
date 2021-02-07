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
@Table(name = "player")
@EntityListeners(AuditingEntityListener.class)
public class PlayerEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5914296393874900838L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String name;
	@ManyToOne
	private RoomEntity room;

	public PlayerEntity(String name, RoomEntity room) {
		this.name = name;
		this.room = room;
	}

	public PlayerEntity() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public RoomEntity getRoom() {
		return room;
	}

	public void setRoom(RoomEntity room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "PlayerEntity [id=" + id + ", name=" + name + ", room=" + room + "]";
	}

}
