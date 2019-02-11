package it.sella;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtil {
	
	private static Gson gson = new GsonBuilder().serializeNulls().create();

	public static Gson getInstance() {
		return gson;
	}	
}
