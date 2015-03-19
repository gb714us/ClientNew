public class Board {
	private GameObject[][] board;
	private int boardCol;
	private int boardRow;
			
	public Board(int cols, int rows){
		boardCol = cols;
		boardRow = rows;
		board = new GameObject[boardCol][boardRow];
	}
	
	public int getRows()
	{
		return boardRow;
	}
	
	public int getCols()
	{
		return boardCol;
	}
	
	public GameObject getGameObject(int row, int col)
	{
		return board[row][col];
	}
	
	public void placeObject(GameObject piece, int row, int col)
	{
		board[row][col] = piece;
	}
	
}