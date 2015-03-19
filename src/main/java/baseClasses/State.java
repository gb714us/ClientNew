public class State {
	private Board board;
	private  Player winner;
	private boolean gameEnd;
	private Player turn;
	private Player player1;
	private Player player2;
	
	
	public State(Player player1, Player player2)
	{
		this.player1 = player1;
		this.player2 = player2;
		winner = null;
		gameEnd = false;
		turn = player1;
	}
	
	public void initializeBoard(int rows, int cols){
		board = new Board(rows,cols);
	}
	
	public int getRows()
	{
		return board.getRows();
	}
	public int getCols()
	{
		return board.getCols();
	}
	
	public boolean checkState()
	{
		if(winner != null)
			gameEnd = true;
		return gameEnd;
	}
	
	public Player getTurn()
	{
		return turn;
	}
	
	public Player getPlayer1()
	{
		return player1;
	}
	
	public Player getPlayer2()
	{
		return player2;
	}
	
	public void setTurn(Player player)
	{
		this.turn = player;
	}
	
	public Player getWinner() 
	{
		return this.winner;
	}
	
	
	public void endTheGame()
	{
		gameEnd = true;
	}
	
	public void setWinner()
	{
		winner = turn;
	}
	
	public void switchTurn()
	{
		if(turn == player1)
			turn = player2;
		else
			turn = player1;
	}
	
	public GameObject getPiece(int row, int col)
	{
		return board.getGameObject(row, col);
	}

	
	public void placePiece(GameObject piece,int row, int col)
	{
		board.placeObject(piece, row, col);
	}

	public Board getBoard(){
		return board;
	}
}
