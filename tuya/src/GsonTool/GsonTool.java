package GsonTool;

import com.google.gson.Gson;

public class GsonTool {
	public  static String createJsonString(Object value){
		Gson gson = new Gson();
		String gString = gson.toJson(value);
		    return gString;
		}

}
