public abstract class GameObject {
	
	protected String type;
	
	public GameObject()
	{
		type = null;
	}
	
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
}