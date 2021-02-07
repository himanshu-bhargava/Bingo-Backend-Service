package com.hb.bingo.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.bingo.entity.PlayerEntity;
import com.hb.bingo.entity.RoomEntity;
import com.hb.bingo.model.RoomStatus;
import com.hb.bingo.model.Turn;
import com.hb.bingo.repos.PlayerRepository;
import com.hb.bingo.repos.RoomRepository;

@Service
public class RoomService {

	private static final String ROOM = "room";
	private static final String PLAYERS = "players";
	@Autowired
	RoomRepository roomRepository;
	@Autowired
	PlayerRepository playerRepository;

	public Map<String, Object> createRoom(String name) {
		RoomEntity roomEntity = new RoomEntity();
		roomEntity = roomRepository.save(roomEntity);
		PlayerEntity player = new PlayerEntity(name, roomEntity);
		player = playerRepository.save(player);
		roomEntity.setTurn(player.getId());
		roomEntity = roomRepository.save(roomEntity);
		player.setRoom(null);
		Map<String, Object> response = new HashMap<>();
		response.put(ROOM, roomEntity);
		response.put(PLAYERS, new HashSet<>(Arrays.asList(player)));
		return response;
	}

	public Map<String, Object> startGame(Long id) {
		Optional<RoomEntity> roomEntityOptional = roomRepository.findById(id);
		if (!roomEntityOptional.isPresent()
				|| !roomEntityOptional.get().getStatus().equals(RoomStatus.created.toString())) {
			return null;
		}
		RoomEntity entity = roomEntityOptional.get();
		entity.setStatus(RoomStatus.started.toString());
		Map<String, Object> response = new HashMap<>();
		response.put(ROOM, roomRepository.save(entity));
		return response;
	}

	public Map<String, Object> poll(Long id) {
		Optional<RoomEntity> roomEntityOptional = roomRepository.findById(id);
		if (!roomEntityOptional.isPresent()) {
			return null;
		}
		Map<String, Object> response = new HashMap<>();
		response.put(ROOM, roomEntityOptional.get());
		if (!roomEntityOptional.get().getStatus().equals(RoomStatus.started.toString())) {
			List<PlayerEntity> playersInDb = playerRepository.findByRoomId(roomEntityOptional.get().getId());
			removeRoomFromPlayersAndSort(playersInDb);
			response.put(PLAYERS, playersInDb);
		}
		return response;
	}

	private void removeRoomFromPlayersAndSort(List<PlayerEntity> playersInDb) {
		playersInDb.stream().sorted((p1, p2) -> p1.getId().compareTo(p2.getId())).forEach(temp -> temp.setRoom(null));
	}

	public Map<String, Object> joinRoom(Long roomId, String name) {
		Optional<RoomEntity> roomEntityOptional = roomRepository.findById(roomId);
		if (!roomEntityOptional.isPresent()
				|| !roomEntityOptional.get().getStatus().equals(RoomStatus.created.toString())) {
			return null;
		}

		List<PlayerEntity> playersInDb = playerRepository.findByRoomId(roomEntityOptional.get().getId());
		if (playersInDb.size() == 5) {
			return null;
		}
		Map<String, Object> response = new HashMap<>();
		response.put(ROOM, roomEntityOptional.get());
		PlayerEntity player = new PlayerEntity(name, roomEntityOptional.get());
		player = playerRepository.save(player);
		playersInDb.add(player);
		removeRoomFromPlayersAndSort(playersInDb);
		response.put(PLAYERS, playersInDb);
		return response;
	}

	public Map<String, Object> playTurn(Turn turn, Long roomId) {
		Optional<RoomEntity> roomEntityOptional = roomRepository.findById(roomId);
		if (!roomEntityOptional.isPresent()
				|| !roomEntityOptional.get().getStatus().equals(RoomStatus.started.toString())
				|| roomEntityOptional.get().getWinner() != null
				|| (!turn.getId().equals(roomEntityOptional.get().getTurn()) && !turn.isWon())) {
			return null;
		}

		RoomEntity room = roomEntityOptional.get();
		room.setLatestStep(turn.getNumber());
		room.setStepCount(room.getStepCount() + 1);
		if (turn.isWon()) {
			room.setWinner(turn.getId());
			room.setStatus(RoomStatus.gameOver.toString());
		}
		setNextTurn(turn, room);
		Map<String, Object> response = new HashMap<>();
		response.put(ROOM, roomRepository.save(room));
		return response;
	}

	private void setNextTurn(Turn turn, RoomEntity room) {
		List<PlayerEntity> playersInDb = playerRepository.findByRoomId(room.getId());
		int playerCount = playersInDb.size();
		for (int i = 0; i < playerCount; i++) {
			if (turn.getId().equals(playersInDb.get(i).getId())) {
				int nextTurn = (i + 1) % playerCount;
				room.setTurn(playersInDb.get(nextTurn).getId());
				break;
			}
		}
	}
}
