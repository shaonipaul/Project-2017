package service;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.ChatMessage;

public class ChatMessageEncoder {

	public String encode(ChatMessage chatMessage) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(chatMessage);
		
	}
}
