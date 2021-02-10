package com.hb.bingo.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BingoUtil {
	private BingoUtil() {

	}

	public static ResponseEntity<?> formAPIResponse(Object serviceResponse) {
		if (serviceResponse == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);

	}
}
