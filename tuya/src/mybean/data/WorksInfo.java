package mybean.data;

public class WorksInfo
{
	String title=null;//��Ʒ����
	String introduce=null;//����
	String sort=null;//��Ʒ����
	String worksUploadTime=null;//��Ʒ�ϴ�ʱ��
	String label=null;//��Ʒ��ǩ
	String userId=null;//����
	String worksid=null;//��Ʒ���
	String worksnum=null;
	
	public String getWorksnum()
	{
		return worksnum;
	}
	public void setWorksnum(String worksnum)
	{
		this.worksnum = worksnum;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public String getIntroduce()
	{
		return introduce;
	}
	public void setIntroduce(String introduce)
	{
		this.introduce = introduce;
	}
	public String getSort()
	{
		return sort;
	}
	public void setSort(String sort)
	{
		this.sort = sort;
	}
	public String getWorksUploadTime()
	{
		return worksUploadTime;
	}
	public void setWorksUploadTime(String worksUploadTime)
	{
		this.worksUploadTime = worksUploadTime;
	}
	public String getLabel()
	{
		return label;
	}
	public void setLabel(String label)
	{
		this.label = label;
	}
	
	public String getUserId()
	{
		return userId;
	}
	public void setUserId(String userId)
	{
		this.userId = userId;
	}
	public String getWorksid()
	{
		return worksid;
	}
	public void setWorksid(String worksid)
	{
		this.worksid = worksid;
	}
	@Override
	public String toString() {
		return "WorksInfo [title=" + title + ", introduce=" + introduce
				+ ", sort=" + sort + ", worksUploadTime=" + worksUploadTime
				+ ", label=" + label + ", userId=" + userId + ", worksid="
				+ worksid + ", worksnum=" + worksnum + "]";
	}
	
}
