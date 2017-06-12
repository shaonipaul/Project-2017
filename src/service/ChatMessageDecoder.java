package service;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.ChatMessage;

public class ChatMessageDecoder {
	
	public ChatMessage decode(String textMessage) throws Exception {
		
		 ObjectMapper mapper = new ObjectMapper();
		 ChatMessage chatMessage = mapper.readValue(textMessage, ChatMessage.class);
		return chatMessage;
	}


}
