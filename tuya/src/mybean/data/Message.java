package mybean.data;

public class Message
{
	private String mesContent;
	private String username;
	private String mesTime;
	private String photo;
	
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	public String getMesContent()
	{
		return mesContent;
	}
	public void setMesContent(String mesContent)
	{
		this.mesContent = mesContent;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getMesTime()
	{
		return mesTime;
	}
	public void setMesTime(String mesTime)
	{
		this.mesTime = mesTime;
	}
	@Override
	public String toString() {
		return "Message [mesContent=" + mesContent + ", username=" + username
				+ ", mesTime=" + mesTime + ", photo=" + photo + "]";
	}
	
	
}
