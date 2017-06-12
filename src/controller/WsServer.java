package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.Message;
import model.ChatMessage;
import model.MessageList;
import model.Util;

@ServerEndpoint("/websocketendpoint/{id}")
public class WsServer {
	
	@OnOpen
	public void onOpen(Session session, @PathParam("id") String id){
		System.out.println("Open Connection ...    "+id);
		Util.onlineId.put(id, session.getId());
		Util.sessions.put(session.getId(), session);
	}
	
	@OnClose
	public void onClose(Session session, @PathParam("id") String id){
		System.out.println("Close Connection ...");
		Util.onlineId.remove(id);
		Util.sessions.remove(session.getId());
	}
	
	@OnMessage
	public void onMessage(String message) {
		System.out.println("Message from the client: " + message);
		//System.out.println("Session ...    "+session.getId());
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			ChatMessage chatMessage = mapper.readValue(message, ChatMessage.class);
			Session s = Util.sessions.get(Util.onlineId.get(chatMessage.getToId()));
				if (s.isOpen()) {
					System.out.println(s.getId());
					s.getBasicRemote().sendText(message);
					int a = Integer.parseInt(chatMessage.getToId());
					int b = Integer.parseInt(chatMessage.getFromId());
					String c ="";
					if(a>b){
						c+=b+""+a;
					}else{
						c+=a+""+b;
					}
					Message m = new Message();
					
					MessageList listm = m.getMessage(c);
					
					if(listm!=null){
						System.out.println("hola hola hola hola ");
						listm.getL().add(chatMessage);
						ObjectMapper mapp = new ObjectMapper();
						m.updateMessage(c, mapp.writeValueAsString(listm));
					}
					else{
						List<ChatMessage> l = new ArrayList<ChatMessage>();
						l.add(chatMessage);
						MessageList ml = new MessageList();
						ml.setL(l);
						ObjectMapper mapp = new ObjectMapper();
						m.insertMessage(c, mapp.writeValueAsString(ml));
					}
				}
				
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		//return "Hello";
	}

	@OnError
	public void onError(Throwable e){
		e.printStackTrace();
	}

}
