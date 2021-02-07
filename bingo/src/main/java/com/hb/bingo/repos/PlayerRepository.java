package com.hb.bingo.repos;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.hb.bingo.entity.PlayerEntity;

public interface PlayerRepository extends CrudRepository<PlayerEntity, Long> {

	@Query(value = "select * from player where room_id=:id", nativeQuery = true)
	public List<PlayerEntity> findByRoomId(Long id);
}
