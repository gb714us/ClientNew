import java.util.ArrayList;
import java.util.Scanner;

import tictactoe.OPiece;
import tictactoe.XPiece;
import baseClasses.GameObject;
import baseClasses.Logic;
import baseClasses.Player;
import baseClasses.State;

public class OthelloLogic extends Logic{
	
	private class InvalidMoveException extends Exception{
		
		private String message = null;
		public InvalidMoveException(){
			super();
		}
		
		InvalidMoveException(String message) {
	        super(message);
	        this.message = message;
	    }
	
	}
	
	boolean gameEnd;
	private GameObject player = new BPiece();
	
	OthelloLogic(Player p1, Player p2){
		super(p1,p2);
		state = new State(p1,p2);
		state.initializeBoard(8,8);
		fillBoard();
	}
	
	
	public void start(){
		System.out.println("Running OL.start()");
		fillBoard();
		Scanner in = new Scanner(System.in);
		while(!gameEnd){
		printBoard();
		System.out.print("Enter Col for " + player + ": ");
		int col = in.nextInt();	
		System.out.print("Enter Row for " + player + ": ");
		int row = in.nextInt();	
		
		
		
		is_valid_placement(row,col);
		placePiece(row,col);
	}}
	
	public void fillBoard(){
		System.out.println("Calling fillboard()");
		NullPiece blank = new NullPiece();
		for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++)
			{
				state.getBoard().placeObject(blank, row,col);
				//state.board.board[row][col] = row + "," + col;
			}
		}
		state.getBoard().placeObject(new WPiece(), 3, 3);
		state.getBoard().placeObject(new BPiece(), 3, 4);
		state.getBoard().placeObject(new BPiece(), 4, 3);
		state.getBoard().placeObject(new WPiece(), 4, 4);
	}
	
	
	public void placePiece(int row, int col){
		try {
//			System.out.println("It is currently: " + player.getType() + "'s turn");
			if (is_valid_placement(row,col)){
				generateMoves(row,col,true);
				switchPlayer();
//				printBoard();
			}
			else{
				throw new InvalidMoveException("Invalid Move");
			}
		} catch (InvalidMoveException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean is_valid_placement (int row, int col){
		if (state.getBoard().getGameObject(row, col).getType() == "*"){
			return true;
		}
		return false;
	}
	
	public void printBoard(){
		for (int row = 0; row < 8; row++){
			for (int col = 0; col < 8; col++)
				System.out.print(state.getBoard().getGameObject(row, col).getType() + "  ");
			System.out.println();
		}
	}
	
	public boolean movesLeftValid(){
		for (int i = 0; i< 8; i++){
			for (int j = 0; j< 8; j++){
				if (state.getBoard().getGameObject(i,j).getType() == "*"){
					ArrayList<Boolean> moves = generateMovesList(i,j,false);
					if(checkList(moves)){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public void generateMoves(int row, int col, boolean execute) throws InvalidMoveException{
		ArrayList<Boolean> moveList = generateMovesList(row,col,execute);
		if (!checkList(moveList)){
			throw new InvalidMoveException("Invalid Move");
		}
	}
	
	public ArrayList<Boolean> generateMovesList(int row, int col, boolean execute){
		ArrayList<Boolean> result = new ArrayList<Boolean>();
		result.add(checkForSequence(row,col,0,1,execute));
		result.add(checkForSequence(row,col,1,1,execute));
		result.add(checkForSequence(row,col,-1,1,execute));
		result.add(checkForSequence(row,col,0,-1,execute));
		result.add(checkForSequence(row,col,1,0,execute));
		result.add(checkForSequence(row,col,-1,0,execute));
		result.add(checkForSequence(row,col,1,-1,execute));
		result.add(checkForSequence(row,col,-1,-1,execute));
		return result;
	}
	
	
	public boolean checkList(ArrayList<Boolean> moveList){
		for (int i=0; i<moveList.size();i++){
			if(moveList.get(i)){
				return true;
			}
		}
		return false;
	}

	
	public boolean checkForSequence(int row, int col, int rSlope, int cSlope, boolean execute){
		int newX = row + rSlope;
		int newY = col + cSlope;
		System.out.println("NEW COORD = " + newX + "," + newY);
		if (	(row + rSlope < 0 || row + rSlope > 7)
				|| (col + cSlope < 0 || col + cSlope > 7 )
				|| state.getBoard().getGameObject(row + rSlope,col + cSlope).getType() == player.getType()
				|| state.getBoard().getGameObject(row + rSlope,col + cSlope).getType() == "*"){
			System.out.println("returning false for checkSequence");
			return false;
		}
		else{
			for (int i = 1; row +rSlope*i <8 && 
							row +rSlope*i >=0 &&
							col +cSlope*i < 8 &&
							col + cSlope*i>=0;
					i++){
				if (state.getBoard().getGameObject(row +rSlope*i,col + cSlope*i).getType() == "*")
				{
					return false;
				}
				else if (state.getBoard().getGameObject(row +rSlope*i,col + cSlope*i).getType() == player.getType())
				{
					if(execute)
					{
						performMove(row,col,rSlope,cSlope);
					}
					break;
				}
			}
			
		}
		
		return true;
		
	}
	
	public void performMove(int row, int col, int rSlope, int cSlope){
		state.getBoard().placeObject(player, row, col);
		int i = 1;
		while ((0 <= row + rSlope*i && row + rSlope*i < 8) &&
				(0 <= col + cSlope*i && col + cSlope*i < 8) &&
				state.getBoard().getGameObject(row + rSlope*i,col + cSlope*i).getType() != player.getType()
				){
			state.getBoard().placeObject(player, row + rSlope*i, col + cSlope*i);
			i++;
		}
	}
	
	public void switchPlayer(){
		if (player instanceof BPiece)
			player = new WPiece();
		else if (player instanceof WPiece)
			player = new BPiece();
		
	}
	
}
