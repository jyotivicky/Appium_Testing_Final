package com.utility;

import java.io.File;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

    public class httpHelper {
	
	public static String uploadTheApp(String filePath) throws UnirestException {
		Unirest.setTimeouts(0, 0);
		HttpResponse<String> response = Unirest.post("https://api-cloud.browserstack.com/app-automate/upload")
		  .header("Authorization", "Basic anlvdGkxNzA6OW1MNnMyaTg0a1R4c2syUW9rNGI=")
		  .field("file", new File(filePath))
		  .asString();
		return response.getBody();
	}

  }
