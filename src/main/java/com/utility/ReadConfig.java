package com.utility;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.File;

    public class ReadConfig {
	Properties Pro = null;

	public ReadConfig() {
		File src = new File("Config_file");
		File fs=new File(src,"Config.properties");
		String path = fs.getAbsolutePath();
		try {
			FileInputStream fis = new FileInputStream(path);
			Pro = new Properties();
			Pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception Is : " + e.getMessage());
		}
	}

	/*
	 * to get the data from the properties files
	 */
	public String getProp(String str) {
		String profile = Pro.getProperty(str);
		return profile;
	}
  }
