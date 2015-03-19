package test;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Move {

    private String id;
    private String gameId;
    private String[][] gameState;
    
    public String getId() {
        return id;
    }
    
    public String getGameId() {
        return gameId;
    }
    
    public String[][] getGameState() {
        return gameState;
    }
    
    
}
