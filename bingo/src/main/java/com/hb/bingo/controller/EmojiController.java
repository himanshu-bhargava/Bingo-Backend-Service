package com.hb.bingo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hb.bingo.model.EmojiRequestResponse;
import com.hb.bingo.service.EmojiService;
import com.hb.bingo.util.BingoUtil;

@RestController
@RequestMapping("/v1/emoji")
public class EmojiController {

	@Autowired
	private EmojiService emojiService;

	@PostMapping
	public ResponseEntity<?> sendEmoji(@RequestBody EmojiRequestResponse emojiRequest,
			@RequestParam(required = true, value = "roomId") Long roomId) {
		return BingoUtil.formAPIResponse(emojiService.sendEmoji(emojiRequest, roomId));
	}
}
