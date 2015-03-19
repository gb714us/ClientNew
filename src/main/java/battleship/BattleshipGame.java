import java.util.Random;

import baseClasses.Game;
import baseClasses.Location;
import baseClasses.Player;
import baseClasses.State;

public class BattleshipGame extends Game {
	private int pieces[] = {2, 2, 3, 3, 4, 5, 6};
	private Console c = new Console();
	
	public BattleshipGame(Player player1, Player player2)
	{
		super(player1,player2);
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public void initializeGame()
	{
		logic = new BattleshipLogic(player1, player2);
		state = logic.getState();
		randomizeShips();
	}
	
	public BattleshipLogic getLogic()
	{
		return (BattleshipLogic) this.logic;
	}
	
	public void start()
	{
		Location l;
		while(!state.checkState())
		{
			c.print(getLogic());
			while (true)
			{
				l = c.getLocation();
				
				if (getLogic().checkValidMove(l.getX(), l.getY()))
				{
					getLogic().placePiece(l.getX(), l.getY());
					break;
				}
				c.printInvalid();
			}
		}
		getState().getWinner().increaseScore();
	}
	
//Helper functions:
	private void randomizeShips()
	{
		/**
		 * So after thinking about it for a bit, I realized it would
			be difficult for the client and server to follow a pattern
			for every game of sending locations, and then disrupt the
			pattern only for battleship, when the client specifies
			where they want their piece. To counter this, I've 
			implemented this method that will randomize the ships, so
			the clients don't/can't place their pieces
			IF THIS IS A BAD IDEA COMMENT OUT THE randomizeShips()
			LINE IN initializeGame(). Otherwise the JSON required 
			to place ships needs to be in (Location, boolean) format
		 */
		Random r = new Random();
		Location l;
		boolean placed;
		for (int i = 0; i < pieces.length; ++i)
		{
			l = new Location(r.nextInt(10), r.nextInt(10));
			if (r.nextInt(2) == 1)
				placed = getLogic().placeShip(pieces[i], l, true);
			else
				placed = getLogic().placeShip(pieces[i], l, false);
			if (placed == false)
				--i;
		}
		getLogic().changeTurn();
		for (int j = 0; j < pieces.length; ++j)
		{
			l = new Location(r.nextInt(10), r.nextInt(10));
			if (r.nextInt(2) == 1)
				placed = getLogic().placeShip(pieces[j], l, true);
			else
				placed = getLogic().placeShip(pieces[j], l, false);
			if (placed == false)
				--j;
		}
		getLogic().changeTurn();
	}
	
	public State getState()
	{
		return logic.getState();
	}
}
