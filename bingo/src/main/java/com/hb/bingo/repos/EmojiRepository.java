package com.hb.bingo.repos;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hb.bingo.entity.EmojiEntity;

public interface EmojiRepository extends CrudRepository<EmojiEntity, Long> {

	@Query(value = "select * from emoji where room_id=:roomId and receiver=:receiverId and already_sent=false LIMIT 1", nativeQuery = true)
	EmojiEntity getUnsentEmoji(Long receiverId, Long roomId);
}
