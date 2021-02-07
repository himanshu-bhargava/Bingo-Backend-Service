package com.hb.bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.bingo.model.RoomRequest;
import com.hb.bingo.model.Turn;
import com.hb.bingo.service.RoomService;

@RestController
@RequestMapping("/v1/room")
@CrossOrigin
public class RoomController {

	@Autowired
	RoomService roomService;

	@PostMapping
	public ResponseEntity<?> createRoom(@RequestBody RoomRequest roomRequest) {
		return formAPIResponse(roomService.createRoom(roomRequest.getName()));
	}

	@PostMapping("{roomId}/join")
	public ResponseEntity<?> joinRoom(@PathVariable("roomId") Long roomId, @RequestBody RoomRequest roomRequest) {
		return formAPIResponse(roomService.joinRoom(roomId, roomRequest.getName()));
	}

	@PostMapping("/{id}/start")
	public ResponseEntity<?> startGame(@PathVariable("id") Long id) {
		return formAPIResponse(roomService.startGame(id));
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> poll(@PathVariable("id") Long id) {
		System.out.println("Polling: "+id);
		return formAPIResponse(roomService.poll(id));
	}

	@PostMapping("/{roomId}/turn")
	public ResponseEntity<?> playTurn(@RequestBody Turn turn, @PathVariable("roomId") Long roomId) {
		return formAPIResponse(roomService.playTurn(turn, roomId));
	}

	private ResponseEntity<?> formAPIResponse(Object serviceResponse) {
		if (serviceResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);

	}
}
