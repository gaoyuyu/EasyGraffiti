package util;

import java.util.StringTokenizer;

public class ToUnicode
{
	public static String unicodeToGB(String s)
	{
		StringBuffer sb = new StringBuffer();
		StringTokenizer st = new StringTokenizer(s, "\\u");
		while (st.hasMoreTokens())
		{
			sb.append((char) Integer.parseInt(st.nextToken(), 16));
		}
		return sb.toString();
	}

	public static String toUnicodeString(String s)
	{
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++)
		{
			char c = s.charAt(i);
			if (c >= 0 && c <= 255)
			{
				sb.append(c);
			} else
			{
				sb.append("\\u" + Integer.toHexString(c));
			}
		}
		return sb.toString();
	}
}
