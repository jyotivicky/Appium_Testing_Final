package com.utility;

import java.io.InputStream;
import org.json.JSONObject;
import org.json.JSONTokener;

   public class JsonParser {
	
	public static JSONObject parse(String file) {
        InputStream is = JsonParser.class.getClassLoader().getResourceAsStream(file);
        assert is != null;
        return new JSONObject(new JSONTokener(is));
    }

}
