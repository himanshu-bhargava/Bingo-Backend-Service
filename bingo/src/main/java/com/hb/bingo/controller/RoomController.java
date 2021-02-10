package com.hb.bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.bingo.model.RoomRequest;
import com.hb.bingo.model.Turn;
import com.hb.bingo.service.GamePlayService;
import com.hb.bingo.util.BingoUtil;

@RestController
@RequestMapping("/v1/room")
@CrossOrigin
public class RoomController {

	@Autowired
	GamePlayService roomService;

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody RoomRequest roomRequest) {
		return BingoUtil.formAPIResponse(roomService.createRoom(roomRequest.getName()));
	}

	@PostMapping("{roomId}/join")
	public ResponseEntity<?> joinRoom(@PathVariable("roomId") Long roomId, @RequestBody RoomRequest roomRequest) {
		return BingoUtil.formAPIResponse(roomService.joinRoom(roomId, roomRequest.getName()));
	}

	@PostMapping("/{id}/start")
	public ResponseEntity<?> startGame(@PathVariable("id") Long id) {
		return BingoUtil.formAPIResponse(roomService.startGame(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> poll(@PathVariable("id") Long id,
			@RequestParam(required = false, value = "myId") Long myId) {
		System.out.println("Polling: " + id);
		return BingoUtil.formAPIResponse(roomService.poll(id, myId));
	}

	@PostMapping("/{roomId}/turn")
	public ResponseEntity<?> playTurn(@RequestBody Turn turn, @PathVariable("roomId") Long roomId) {
		return BingoUtil.formAPIResponse(roomService.playTurn(turn, roomId));
	}

}
