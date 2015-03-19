import java.util.ArrayList;

public abstract class Logic {
	protected State state;
	
	
	public Logic(Player player1, Player player2)
	{
		
	}
	
	public ArrayList<Location> getLegalMoves(Player p){
		return new ArrayList<Location>();
	}
	
	public void placePiece(GameObject obj, Location l, State s){
		
	}
	
	public boolean checkNotNull(int row, int col)
	{
		return state.getPiece(row,col)!=null;
	}
	
	public void updateState(State s){

	}
	
	public State getState()
	{
		return state;
	}
	
	public abstract void placePiece(int row, int col);
	
	
	
}