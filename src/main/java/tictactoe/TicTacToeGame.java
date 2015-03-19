import java.util.Scanner;

import baseClasses.Game;
import baseClasses.Player;
import baseClasses.State;

public class TicTacToeGame extends Game{
	
	public TicTacToeGame(Player p1, Player p2)
	{
		super(p1,p2);
	}
	
	@Override
	public void initializeGame(){
		logic = new TicTacToeLogic(player1,player2);
		state = logic.getState();

	}
	
	public void start(){
		while(!state.checkState())
		{
			Scanner in = new Scanner(System.in);
			System.out.print("Enter Col for " + state.getTurn().getName() + ": ");
			int col = in.nextInt();	
			System.out.print("Enter row for " + state.getTurn().getName() + ": ");
			int row = in.nextInt();	
			
			getLogic().placePiece(row, col);
		}
	}
	
	public TicTacToeLogic getLogic()
	{
		return (TicTacToeLogic) logic;
	}
	
}