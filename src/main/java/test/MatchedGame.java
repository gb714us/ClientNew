package test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MatchedGame {
	private String gameID;
	private String opponentID;
	private String[][] gameState;
	
    public String getGameID() {
        return gameID;
    }
    
    public String getOpponentID() {
        return opponentID;
    }
    
    public String[][] getGameState() {
        return gameState;
    }
}
