package com.hb.bingo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.bingo.entity.EmojiEntity;
import com.hb.bingo.entity.RoomEntity;
import com.hb.bingo.model.EmojiRequestResponse;
import com.hb.bingo.model.RoomStatus;
import com.hb.bingo.repos.EmojiRepository;
import com.hb.bingo.repos.RoomRepository;

@Service
public class EmojiService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private EmojiRepository emojiRepository;

	public Map<String, Object> sendEmoji(EmojiRequestResponse emojiRequest, Long roomId) {
		Optional<RoomEntity> roomEntityOptional = roomRepository.findById(roomId);
		if (!roomEntityOptional.isPresent()
				|| !roomEntityOptional.get().getStatus().equals(RoomStatus.started.toString())) {
			return null;
		}
		emojiRepository.save(new EmojiEntity(emojiRequest.getSender(), emojiRequest.getReceiver(),
				roomEntityOptional.get(), false, emojiRequest.getEmoji()));
		return new HashMap<>();
	}
}
