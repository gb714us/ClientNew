import baseClasses.GameObject;
import baseClasses.Logic;
import baseClasses.Player;
import baseClasses.State;


public class TicTacToeLogic extends Logic
{
	private GameObject pieceTurn = new XPiece();
	
	TicTacToeLogic(Player player1, Player player2){
		super(player1,player2);
		state = new State(player1,player2);
		state.initializeBoard(3,3);

	}
	

	
	public void printBoard(){
		for (int row = 0; row < 3; row++){
			for (int col = 0; col < 3; col++){
				if(checkNotNull(row,col))
					System.out.print(state.getPiece(row, col).getType() + "  ");
				else
					System.out.print("*  ");
			}
			
			System.out.println();
		}
	}
	
	public void placePiece(int row, int col){
		if(state.checkState()){
			System.out.println("The game is already over");
			return;
		}
		if (checkValidMove(row,col))
			state.placePiece(pieceTurn, row, col);
		else{
			System.out.println("InValid Move");
			return;
		}
		checkWin();
		switchPieceTurn();
		state.switchTurn();
	}
	
	
	public boolean checkValidMove(int row, int col)
	{
		if(state.getPiece(row,col) == null)
			return true;
		return false;
	}
	
	public void switchPieceTurn(){
		if (pieceTurn instanceof XPiece)
			pieceTurn = new OPiece();
		else if (pieceTurn instanceof OPiece)
			pieceTurn = new XPiece();
		
	}
	
	public void checkRow(int row)
	{
		if(checkNotNull(row,0) && checkNotNull(row,1) && checkNotNull(row,2))
			if (state.getPiece(row,0).getType() == state.getPiece(row,1).getType() && 
					state.getPiece(row,1).getType() == state.getPiece(row,2).getType())
			{
				state.setWinner();
			}
	}
	

	public void checkCol(int col)
	{
		if(checkNotNull(0,col) && checkNotNull(1,col) && checkNotNull(2,col))
			if (state.getPiece(0,col).getType() == state.getPiece(1,col).getType() && 
					state.getPiece(1,col).getType() == state.getPiece(2,col).getType())
			{
				state.setWinner();
			}
	}
	
	public void checkDiagonal()
	{
		if(checkNotNull(0,0) && checkNotNull(1,1) && checkNotNull(2,2)){
			if (state.getPiece(0,0).getType()==state.getPiece(1,1).getType() && 
					state.getPiece(1,1).getType()== state.getPiece(2,2).getType())
			{
				state.setWinner();
			}
		}
		if(checkNotNull(0,2) && checkNotNull(1,1) && checkNotNull(2,0))
			if (state.getPiece(0,2).getType() == state.getPiece(1,1).getType() && 
					state.getPiece(1,1).getType() == state.getPiece(2,0).getType())
			{
				state.setWinner();
		}
	}
	
	public void checkWin(){
		//check horizontal
		checkRow(0);
		checkRow(1);
		checkRow(2);
		//check vertical
		checkCol(0);
		checkCol(1);
		checkCol(2);
		//check diagonal
		checkDiagonal();
		if (state.checkState()){
			printBoard();
			System.out.println("WINNER IS: " + pieceTurn.getType());
		}
		
		if (checkFull()){
			state.endTheGame();
			printBoard();
			System.out.println("CATS GAME");
		}		
	}
	
	public boolean checkFull(){		
		System.out.println(state.checkState());
		if (!state.checkState()){
			for (int row = 0; row < 3; row++){
				for (int col = 0; col < 3; col++){
					if(!checkNotNull(row, col))
						return false;

				}
			}
			return true;

		}
		return false;
	}


}
