public class Player {
	private String name = null;
	private String ipAddress = null;
	private int score = 0;
	
	public Player(String name)
	{
		this.name = name;
	}
	
	public void increaseScore()
	{
		++score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public String getName()
	{
		return name;
	}
}