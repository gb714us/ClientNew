package baseClasses;

import baseClasses.GameObject;

public class BattleshipShip extends GameObject {
	public BattleshipShip()
	{
		type = "alive";
	}
	
	public BattleshipShip(String type)
	{
		type = null;
	}
}
