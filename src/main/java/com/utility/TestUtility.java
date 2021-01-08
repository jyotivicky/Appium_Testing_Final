package com.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.BaseClass.TestBase;

    public class TestUtility {
    	public static JSONObject loginUsers;
    	public static final long WAIT = 10;
    /*
     * To get the Validation and Test Data messages from XML files for the validation 
     * messages and Test Data, which we can get based the String	
     */
	public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
		HashMap<String, String> stringMap = new HashMap<String, String>();
		// Get Document Builder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Build Document
		Document document = builder.parse(file);

		// Normalize the XML Structure; It's just too important !!
		document.getDocumentElement().normalize();

		// Here comes the root node
		Element root = document.getDocumentElement();

		// Get all elements
		NodeList nList = document.getElementsByTagName("string");

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				// Store each element key value in map
				stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
			}
		}
		return stringMap;
	}
	
	/*
	 *To get the Time and Date to add in the Report 
	 */
	public String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/*
	 * To get the Log messages and Print it on to the Log File
	 */
	public void log(String txt) {
		TestBase base = new TestBase();
		String msg = Thread.currentThread().getId() + ":" + base.getPlatform() + ":" + base.getDeviceName() + ":"
				+ Thread.currentThread().getStackTrace()[2].getClassName() + ":" + txt;
		System.out.println(msg);
		String strFile = "Logs" + File.separator + base.getPlatform() + "_" + base.getDeviceName() + File.separator
				+ base.getDateTime();
		File logFile = new File(strFile);
		if (!logFile.exists()) {
			logFile.mkdirs();
		}
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter(logFile + File.separator + "log.txt", true);
		} catch (IOException e) {
			e.printStackTrace();
		}
		PrintWriter printWriter = new PrintWriter(fileWriter);
		printWriter.println(msg);
		printWriter.close();
	}

	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}
	
}

    
    
    
    
    
    
    
    
    
    
    
    
    