package test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {
	private String[][] gameState;
    private String currentTurn;
    private String currentStatus;
    
//    public Move(String[][] gameState, String currentStatus, String currentTurn)
//	{
//		this.gameState = gameState;
//		this.currentStatus = currentStatus;
//		this.currentTurn = currentTurn;
//	}
//    
    public String getCurrentTurn() {
        return currentTurn;
    }
    
    public String getCurrentStatus() {
        return currentStatus;
    }
    
    public String[][] getGameState() {
        return gameState;
    }
    
    
}
