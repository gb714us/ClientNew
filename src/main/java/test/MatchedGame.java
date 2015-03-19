package test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchedGame {
	private String gameID;
	private String[][] gameState;
	
    public String getGameID() {
        return gameID;
    }
    
    public String[][] getGameState() {
        return gameState;
    }
}
