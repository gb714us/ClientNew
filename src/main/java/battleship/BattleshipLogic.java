import java.util.ArrayList;

import baseClasses.Location;
import baseClasses.Logic;
import baseClasses.Player;
import baseClasses.State;

public class BattleshipLogic extends Logic {
	protected State otherState;
	
	public BattleshipLogic(Player player1, Player player2)
	{
		super(player1,player2);
		state = new State(player1, player2);
		state.initializeBoard(10, 10);
		otherState = new State(player1, player2);
		otherState.initializeBoard(10, 10);
		fillBoards();
	}
	
	private void fillBoards()
	{
		BattleshipShip temp;
		for (int r = 0; r < state.getRows(); ++r)
		{
			for (int c = 0; c < state.getCols(); ++c)
			{
				temp = new BattleshipShip(null);
				state.placePiece(temp, r, c);
				temp = new BattleshipShip(null);
				otherState.placePiece(temp, r, c);
			}
		}
	}
	
	public ArrayList<Location> getLegalMoves(Player player)
	{
		ArrayList<Location> result = new ArrayList<Location>();
		Location temp;
		for (int r = 0; r < getState().getRows(); ++r)
		{
			for (int c = 0; c < getState().getCols(); ++c)
			{
				if (getState().getPiece(r, c).getType() == null || getState().getPiece(r, c).getType().equals("alive"))
				{
					temp = new Location(r, c);
					result.add(temp);
				}
			}
		}
		return result;
	}
	
	public void placePiece(int x, int y)
	{
		if (getState().getPiece(x, y).getType() == null)
			getState().getPiece(x, y).setType("hit");
		else if (getState().getPiece(x, y).getType().equals("alive"))
			getState().getPiece(x, y).setType("dead");
		updateState();
		if (getState().checkState() == false)
		{
			//Game isn't over, so switch the turn
			changeTurn();
		}
	}
	
	public void updateState()
	{
		for (int r = 0; r < state.getRows(); ++r)
			for (int c = 0; c < state.getCols(); ++c)
				if (getState().getPiece(r, c).getType() != null && getState().getPiece(r, c).getType().equals("alive"))
					return;
		//Someone lost...
		state.setWinner();
	}
	
//Battleship specific:
	public boolean placeShip(int size, Location l, boolean vertical)
	{
		/*
		 * Need to check if a piece is already there
		 * and to see if we're trying to place a piece outside the
		 * game boundaries
		 */
		if (vertical)
		{
			if (l.getX() + size > getState().getCols())
				return false;
			for (int r = l.getX(); r < l.getX() + size; ++r)
				if (getState().getPiece(r, l.getY()).getType() != null)
					return false;
		}
		else
		{
			if (l.getY() + size > getState().getRows())
				return false;
			for (int c = l.getY(); c < l.getY() + size; ++c)
				if (getState().getPiece(l.getX(), c).getType() != null)
					return false;
		}
		/*
		 * No problems so far, place the piece then
		 * If this throws an exception something is really wrong
		 * up there
		*/
		placeObject(size, l, vertical);
		
		return true;
	}
	
	private void placeObject(int size, Location l, boolean vertical)
	{
		BattleshipShip temp;
		if (vertical)
		{
			for (int r = l.getX(); r < l.getX() + size; ++r)
			{
				temp = new BattleshipShip();
				getState().placePiece(temp, r, l.getY());
			}
		}
		else
		{
			for (int c = l.getY(); c < l.getY() + size; ++c)
			{
				temp = new BattleshipShip();
				getState().placePiece(temp, l.getX(), c);
			}
		}
	}
	
	public boolean checkValidMove(int x, int y)
	{
		ArrayList<Location> legal_moves = getLegalMoves(getState().getTurn());
		for (Location p : legal_moves)
		{
			if (p.getX() == x && p.getY() == y)
			{
				return true;
			}
		}
		return false;
	}
	
	public State getState()
	{
		//So BattleshipLogic is special in that it has two states (player1's state, player2's state)
		//this function should return the right state given who's turn it is
		if (state.getTurn().equals(state.getPlayer1()))

			return state;
		else
		return otherState;
	}
	
	public void changeTurn()
	{
		if (state.getTurn().equals(state.getPlayer1()))
		{
			state.setTurn(state.getPlayer2());
			otherState.setTurn(otherState.getPlayer2());
		}
		else
		{
			state.setTurn(state.getPlayer1());
			otherState.setTurn(otherState.getPlayer2());
		}
	}
}
