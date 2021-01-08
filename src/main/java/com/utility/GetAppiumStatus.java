package com.utility;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

    public class GetAppiumStatus {
	
    /*
     * To run Appium Server by defualt	
     */
	public static AppiumDriverLocalService getAppiumServerDefault() {
		return AppiumDriverLocalService.buildDefaultService();
	}
		
	/*
	 * Configuration to Run the Appium Server programatically and capture the Server Logs
	 * for debugging purpose
	 */
	public static AppiumDriverLocalService getAppiumService() {
				HashMap<String, String> environment = new HashMap<String, String>();
				environment.put("JAVA_HOME", "C:\\Program Files\\Java\\jdk1.8.0_231\\bin");
				environment.put("ANDROID_HOME", "C:\\Users\\hp\\AppData\\Local\\Android\\Sdk");
				return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
				.withAppiumJS(new File("C:\\Users\\hp\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.usingPort(4723)
				.withArgument(GeneralServerFlag.SESSION_OVERRIDE)
			    .withLogFile(new File("ServerLogs/Server.log")));
	}
	
	/*
	 * To Check whether Appium Is Running Or Not, it will return True if Appium is 
	 * already warning or False if Appium is not running
	 */
	public static  boolean checkIfAppiumServerIsRunnning(int port) throws Exception {
	    boolean isAppiumServerRunning = false;
	    ServerSocket socket;
	    try {
	        socket = new ServerSocket(port);
	        socket.close();
	    } catch (IOException e) {
	        isAppiumServerRunning = true;
	    } finally {
	        socket = null;
	    }
	    return isAppiumServerRunning;
	}

}
    
    
    
    
    
    
    
