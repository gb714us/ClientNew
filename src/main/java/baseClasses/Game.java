public abstract class Game {
	private int gameID;
	protected State state;
	protected Logic logic;
	protected Player player1;
	protected Player player2;
	
	public Game(Player p1,Player p2)
	{
		player1 = p1;
		player2 = p2;
	}
	
	
	public abstract void initializeGame();
	
	public int getGameID()
	{
		return gameID;
	}
	
	public State getState()
	{
		return state;
	}
	
	public Logic getLogic()
	{
		return logic;
	}
	
	
}