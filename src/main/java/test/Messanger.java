package test;

import org.springframework.web.client.RestTemplate;

public class Messanger {
	private String url;
	private RestTemplate template;
	String [][] currentState;
	String status;
	
	Messanger()
	{
		url = "http://localhost:8080/";
		template = new RestTemplate();
		currentState = null;
	}

	public String getUrl() {
		return url;
	}

	public RestTemplate getTemplate() {
		return template;
	}

	public String[][] getCurrentState() {
		return currentState;
	}
	
	public void performMove(int x, int y)
	{
		Move move = template.getForObject(url + "move?x=" + x +"&y=" + y, Move.class);
	}
}
