package test;

import org.springframework.web.client.RestTemplate;

public class Messanger {
	private String url;
	private RestTemplate template;
	private String [][] currentState;
	String status;
	private String gameID;
	
	public Messanger()
	{
		url = "http://localhost:8080/";
		template = new RestTemplate();
		currentState = null;
		gameID = "";
	}

	public String getUrl() {
		return url;
	}

	public RestTemplate getTemplate() {
		return template;
	}
	
	public String getID()
	{
		return gameID;
	}

	public String[][] getCurrentState() {
		return currentState;
	}
	
	public void performMove(int x, int y)
	{
		Move move = template.getForObject(url + "move?x=" + x +"&y=" + y + "&id="+ this.gameID, Move.class);
		currentState = move.getGameState();
	}
	
	public MatchedGame findGame(String game, String user) {
		MatchedGame matched = template.getForObject(url + "findgame?game=" + game + "&user=" + user, MatchedGame.class);
		System.out.println(matched.getGameID());
		System.out.println(matched.getOpponentID());
		System.out.println(matched.getGameState());
		this.currentState = matched.getGameState();
		this.gameID = matched.getGameID();
		return matched;
	}
	
}
