package util;

public class PageBean
{
	int currPage;//��ǰҳ
	int pageNum;//ÿҳ����ʾ�ļ�¼��
	static int recordNum;//��¼��
	String condition;
	
	public String getCondition()
	{
		return condition;
	}
	public void setCondition(String condition)
	{
		this.condition = condition;
	}
	public int getCurrPage()
	{
		return currPage;
	}
	public void setCurrPage(int currPage)
	{
		this.currPage = currPage;
	}
	public int getPageNum()
	{
		return pageNum;
	}
	public void setPageNum(int pageNum)
	{
		this.pageNum = pageNum;
	}
	public static int getRecordNum()
	{
		return recordNum;
	}
	public static void setRecordNum(int recordNum)
	{
		PageBean.recordNum = recordNum;
	}
	
	
}
