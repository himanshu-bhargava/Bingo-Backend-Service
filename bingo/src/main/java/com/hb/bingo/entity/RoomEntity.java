package com.hb.bingo.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.hb.bingo.model.RoomStatus;

@Entity
@Table(name = "room")
@EntityListeners(AuditingEntityListener.class)
public class RoomEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3980660494771858047L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	Integer latestStep;
	Integer stepCount;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getLatestStep() {
		return latestStep;
	}

	public void setLatestStep(Integer latestStep) {
		this.latestStep = latestStep;
	}

	public Integer getStepCount() {
		return stepCount;
	}

	public void setStepCount(Integer stepCount) {
		this.stepCount = stepCount;
	}

	public Long getTurn() {
		return turn;
	}

	public void setTurn(Long turn) {
		this.turn = turn;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getWinner() {
		return winner;
	}

	public void setWinner(Long winner) {
		this.winner = winner;
	}

	Long turn;
	String status;
	Long winner;

	public RoomEntity() {
		super();
		this.latestStep = 0;
		this.stepCount = 0;
		this.turn = 0L;
		this.status = RoomStatus.created.toString();
		this.winner = null;
	}

}
