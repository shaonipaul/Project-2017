package model;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

public class Util {
	
	public static Map<String, String> onlineId = new HashMap<String, String>();
	public static Map<String, Session> sessions = new HashMap<String, Session>();

}
