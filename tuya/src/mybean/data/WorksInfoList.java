package mybean.data;

import java.util.ArrayList;
import java.util.Map;

public class WorksInfoList
{
	public ArrayList<Map<String,Object>> WorksInfoList;

	public ArrayList<Map<String, Object>> getWorksInfoList()
	{
		return WorksInfoList;
	}

	public void setWorksInfoList(ArrayList<Map<String, Object>> worksInfoList)
	{
		WorksInfoList = worksInfoList;
	}

	@Override
	public String toString()
	{
		return "WorksInfoList [WorksInfoList=" + WorksInfoList + "]";
	}
	
}
