package com.hb.bingo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BingoExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<?> handleAll(Throwable exception) {
		Map<String, String> map = new HashMap<>();
		map.put("error", exception.getMessage());
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}
}
