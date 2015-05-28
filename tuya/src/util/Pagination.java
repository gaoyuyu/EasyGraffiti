package util;

public class Pagination
{
	public Pagination()
	{
	}
	public static String getPagination(int currPage, int pageNum, int recordNum, String condition, String url)
	{
		
		StringBuffer sb=new StringBuffer();
		int RecordNum=recordNum;//�ܼ�¼��
		int StartRecord=0;//��ʼ�ļ�¼��
		int EndRecord=0;//�����ļ�¼��
		int PagesNum=0;//��ҳ��
		int LineNum=pageNum;//ÿҳ��ʾ�ļ�¼��
		int CurrentPage=currPage;//��ǰҳ
		if(RecordNum>0)
		{
			PagesNum=(int)(RecordNum/LineNum);
			if((RecordNum%LineNum)!=0)
			{
				PagesNum++;
			}
		}
		StartRecord = CurrentPage*LineNum;
		EndRecord=StartRecord+LineNum;
		if(EndRecord>RecordNum)
		{
			EndRecord=RecordNum;
		}
		sb.append("��<font color=\"#3366cc\">"+(recordNum-1)+"</font>����¼,");
		sb.append("��ҳ��ʾ<font color=\"#3366cc\">"+((recordNum>0)?(StartRecord+1):0)+"-"+(EndRecord)+"</font>��");
		sb.append("��<font color=\"#3333cc\">"+((recordNum>0)?(currPage+1):0)+"/"+PagesNum+"</font>ҳ");
		if(CurrentPage==0)
		{
			sb.append("|��ҳ  ��һҳ");
		}
		else
		{
			sb.append("|<a href="+url+"?pages=0>��ҳ  </a>");
			sb.append("<a href="+url+"?pages="+(CurrentPage-1)+">��һҳ  </a>");
		}
		if((CurrentPage+1)>=PagesNum)
		{
			sb.append("��һҳ  ĩҳ|");
		}
		else
		{
			sb.append("<a href="+url+"?pages="+(CurrentPage+1)+">  ��һҳ  </a>");
			sb.append("<a href="+url+"?pages="+(PagesNum-1)+">  ĩҳ</a>|");
		}
		return sb.toString();
	}
	
}
