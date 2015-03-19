

import java.util.Scanner;

import tictactoe.TicTacToeLogic;
import baseClasses.Game;
import baseClasses.Player;
import baseClasses.State;

public class OthelloGame extends Game{
	
	public OthelloGame(Player p1, Player p2) {
		super(p1, p2);
	}
	
	
	public void initializeGame() {
		logic = new OthelloLogic(player1,player2);
		state = logic.getState();
		
	}
	
	public void start(){
		while(!state.checkState())
		{
			getLogic().start();
			
		}
	}
	
	public OthelloLogic getLogic()
	{
		return (OthelloLogic) logic;
	}
	

}
