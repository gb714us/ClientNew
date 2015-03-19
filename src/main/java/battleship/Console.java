import java.util.Scanner;

import baseClasses.Location;
import baseClasses.Player;
import baseClasses.State;

public class Console {
/*
 * This class is just for debugging purposes
 * and should not be included in the end product
 * It should be replaced by a JSON packet send
 * to the client who will then display a GUI of the 
 * game board(s)
 */
	public Console()
	{
		in = new Scanner(System.in);
	}
	
	public void print(BattleshipLogic logic)
	{
		System.out.println("It's " + logic.getState().getTurn().getName() + "'s turn!");
		System.out.println("Opponent's board");
		printBoard(logic.getState(), true);
		System.out.println("Your board");
		logic.changeTurn();
		printBoard(logic.getState(), false);
		logic.changeTurn();
		
	}
	
	public void placePieces(State state, BattleshipLogic logic, int pieces[])
	{
		int row, col;
		boolean placed;
		System.out.println(state.getTurn().getName() + " will place pieces now.");
		Location temp;
		for (int i = 0; i < pieces.length; ++i)
		{
			System.out.print("Placing piece of size " + pieces[i] + ".\nWhat row: ");
			row = in.nextInt();
			System.out.print("What col: ");
			col = in.nextInt();
			System.out.print("Is it vertical or horizontal (0 = horizontal | 1 = vertical): ");
			temp = new Location(row, col);
			if (in.nextInt() == 1)
			{
				placed = logic.placeShip(pieces[i], temp, true);
			}
			else
			{
				placed = logic.placeShip(pieces[i], temp, false);
			}
			if (placed == false)
			{
				System.out.println("Place not pieced: illegal position");
				--i;
			}
		}
	}
	
	public void printBoard(State state, boolean opponents)
	{
		System.out.print("   ");
		for (int ncol = 0; ncol < state.getCols(); ++ncol)
			System.out.print(" " + (ncol + 1) + " ");
		System.out.println();
		for (int r = 0; r < state.getRows(); ++r)
		{
			if (r < 9)
				System.out.print((r + 1) + "  ");
			else
				System.out.print((r + 1) + " ");
			for (int c = 0; c < state.getCols(); ++c)
			{
				if (state.getPiece(r, c).getType() == null)
				{	
					System.out.print(" . ");
				}
				else if (state.getPiece(r, c).getType().equals("dead"))
				{
					System.out.print(" O ");
				}
				else if (state.getPiece(r, c).getType().equals("hit"))
				{
					System.out.print(" X ");
				}
				else if (state.getPiece(r, c).getType().equals("alive"))
				{
					if (opponents == true)
						System.out.print(" . ");
					else
						System.out.print(" S ");
				}
				else
				{
					System.out.print(" . ");
				}
			}
			System.out.println();
		}
	}
	
	public int getRow()
	{
		System.out.print("Enter the row: ");
		return in.nextInt() - 1;
	}
	
	public int getCol()
	{
		System.out.print("Enter the col: ");
		return in.nextInt() - 1;
	}
	
	public Location getLocation()
	{
		Location result = new Location(getRow(), getCol());
		return result;
	}
	
	public void printInvalid()
	{
		System.out.println("Illegal move!");
	}
	
	public void printWinner(Player winner)
	{
		System.out.println("And the winner is...");
		System.out.println(winner.getName() + "!");
		System.out.println(winner.getName() + "'s score: " + winner.getScore());
	}
//Private:
	private Scanner in;
}
