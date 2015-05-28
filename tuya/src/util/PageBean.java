package util;

public class PageBean
{
	int currPage;//当前页
	int pageNum;//每页能显示的记录数
	static int recordNum;//记录数
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
