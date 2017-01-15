package cosc332_server_test;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import ygraphs.ai.smart_fox.GameMessage;
import ygraphs.ai.smart_fox.games.AmazonsGameMessage;
import ygraphs.ai.smart_fox.games.GameClient;
import ygraphs.ai.smart_fox.games.GamePlayer;
/**
 * An example showing how to implement a GamePlayer 
 * @author yongg
 */
public class test extends GamePlayer{

    private GameClient gameClient;
    private static Boolean isLoggedIn = false;
	
    private String userName = null;
 
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		test player1;
		if(args.length >= 2)//if input, use input
			player1 = new test(args[0], args[1]);
		else//default to best user
			player1 = new test("ryan", "tymber"); 
		 System.out.println(player1.userName);
		 //literally waiting for connection
		 ArrayList<String> rooms = new ArrayList<String>();
		 while(!isLoggedIn){
			 //loop until connection is achieved
		 }
		 System.out.println(player1.gameClient.getRoomList());
		 //player1.gameClient.joinRoom("Echo Lake");
		 //player1.gameClient.logout();
	}
	
	/**
	 * Any name and passwd 
	 * @param userName
	 * @param passwd
	 */
	public test(String userName, String passwd) {
		this.userName = userName;
		gameClient = new GameClient(userName, passwd, this);
	}
 
 
 
	@Override
	public String userName() { 
		return userName;
	}

	@Override
	public boolean handleGameMessage(String messageType, Map<String, Object> msgDetails) {
 		//This method will be called by the GameClient when it receives a game-related message
		//from the server.
		return true;
	}

	@Override
	public void onLogin() {
		System.out.println("I am called because the server said I am logged in successfully");
		isLoggedIn = true;//added this to let main know connection achieved
	}

}
