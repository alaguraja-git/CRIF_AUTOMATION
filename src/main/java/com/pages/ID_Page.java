package com.pages;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qa.util.TXTFile;



public class ID_Page {

	public static ExtentHtmlReporter reporter = new ExtentHtmlReporter("./REPORT/CRIF_VALIDATION_REPORT.html");

	public static ExtentReports extent = new ExtentReports();

	public static ExtentTest logger;

	static int count = 0;

	static boolean flag = true;

	private WebDriver driver;
	
	
	

	// Constructor of the page class

	public ID_Page(WebDriver driver) {

		this.driver = driver;		

	}

	/*
	 * 
	 * Name: Alaguraja. R
	 * 
	 * This function is to read the CSV file
	 */

	public BufferedReader readCSVfile(String filePath) {

		String line;

		String splitBy = "\\|";
		
		 reporter.config().setDocumentTitle("CRIF FILE AUTOMATION REPORT");
		 reporter.config().setReportName("CRIF FILE AUTOMATION REPORT");
		 reporter.config().setTheme(Theme.DARK);
		 
		
		try {

			// parsing a CSV file into BufferedReader class constructor

			BufferedReader br = new BufferedReader(new FileReader(filePath));

			return br;

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

		}

		return null;

	}

	/*
	 * 
	 * Name: Alaguraja. R
	 * 
	 * This function is to validate particular field is empty
	 * 
	 */

	public static void validation_isEmpty(String data, String id,TXTFile textFile, int count, String testCaseName) {

		// check Month Format

		boolean n = isEmpty(data);

		if (n) {
			
			System.out.println("EMPTY CELL VALUE" + "  " + "ROW NUMBER-->" + count+"\n" +" CIS NUMBER : " + id +"\n" +" ACTUAL CELL VALUE: NULL");
			String failureMessage = "<b>EMPTY CELL VALUE<b>" + "  " + "<b>ROW NUMBER--><b>" + count+"<br />"+ "<b> CIS NUMBER  : <b>" + id +"<br />" +"<b> ACTUAL CELL VALUE: NULL<b>";
			logger.log(Status.FAIL, failureMessage);			
			textFile.writeTxtFile(testCaseName+".txt", "\n EMPTY CELL VALUE ROW NUMBER -->" + count+ " CIS NUMBER : " + id +" ACTUAL CELL VALUE: NULL");
			
			flag = false;

		}

	}

	/*
	 * 
	 * Name: Alaguraja. R
	 * 
	 * * This below function is to validate Date Format.
	 * 
	 */

	public static void validation_DateFormat(String data,String id, int count,TXTFile textFile, String testCaseName) {

		// check Month Format

		boolean n = validateMonthFormat(data);

		if (n == false) {

			System.out.println("INVALID DATE FORMAT" + "  " + "ROW NUMBER-->" + count+ " CIS NUMBER  : " + id + " ACTUAL CELL VALUE "+ data);

			logger.log(Status.FAIL, "<b>INVALID DATE FORMAT<b>" + "  " + "<b>ROW NUMBER--><b>" + count+"<br />"+ "<b> CIS NUMBER  : <b>" + id +"<br />" +"<b> ACTUAL CELL VALUE: <b>"+ data);
			textFile.writeTxtFile(testCaseName+".txt", "\n INVALID DATE FORMAT ROW NUMBER -->" + count+ " CIS NUMBER : " + id +" ACTUAL CELL VALUE: " + data);
			flag = false;

		}

	}

	/*
	 * 
	 * Name: Alaguraja. R
	 * 
	 * This below function is to validate particular field is Empty or not
	 * 
	 */

	public static boolean isEmpty(String str) {

		if (null == str || str.equals("") || str.length() == 0) {

			return true;

		}

		return false;

	}

	/*
	 * 
	 * Name : ALAguraja
	 * 
	 * This below function is to validate Date
	 * 
	 */

	public void validation_Date(BufferedReader br, int columnNoToBeValidated, int columnNoOfId, String testCaseName) {

		count = 0;

		String line;

		String splitBy = "\\|";

		try {

			extent.attachReporter(reporter);

			logger = extent.createTest(testCaseName);
			 TXTFile textFile = new TXTFile();
        	 textFile.txtFileCreation(this.getClass().getSimpleName(), testCaseName+".txt");

			while ((line = br.readLine()) != null) // returns a Boolean value

			{

				String[] row = line.split(splitBy);

				String data = row[columnNoToBeValidated];
				String id = row[columnNoOfId];

				count++;

				ID_Page.validation_DateFormat(data, id, count,  textFile, this.getClass().getSimpleName() +"/"+ testCaseName);

			}

			if (flag = true)

			{

				logger.log(Status.PASS, "ALL THE REMAINING DATE FORMAT ROWS ARE PROPER");

			}

			extent.flush();
			textFile.closeTxtFile();

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

		}

	}

	/*
	 * 
	 * Name : ALAGURAJA. R
	 * 
	 * This function is to validate Empty field by line by line
	 * 
	 */

	public void validation_Empty(BufferedReader br, int columnNoToBeValidated, int columnNoOfId, String testCaseName) {

		count = 0;

		String line;

		String splitBy = "\\|";

		try {

			extent.attachReporter(reporter);

			logger = extent.createTest(testCaseName);

			   TXTFile textFile = new TXTFile();
          	   textFile.txtFileCreation(this.getClass().getSimpleName(), testCaseName+".txt");
          	   
			while ((line = br.readLine()) != null) // returns a Boolean value

			{

				String[] row = line.split(splitBy);

				String data = row[columnNoToBeValidated];
				
				String id = row[columnNoOfId];

				count++;

				ID_Page.validation_isEmpty(data, id, textFile, count, this.getClass().getSimpleName()+"/"+ testCaseName);

			}

			if (flag)

			{

				logger.log(Status.PASS, "THERE IS  NO EMPTY CELL VALUE IN THE FILE");

			}

			extent.flush();
			textFile.closeTxtFile();

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

		}

	}

	
	public void findDuplicate(BufferedReader br, int columnNoToBeValidated, int columnNoOfId, String testCaseName) {

		count = 0;

		String line;

		String splitBy = "\\|";
		
		HashMap<String, String> map = new HashMap<>();

		try {

			extent.attachReporter(reporter);

			logger = extent.createTest(testCaseName);

			   TXTFile textFile = new TXTFile();
          	   textFile.txtFileCreation(this.getClass().getSimpleName(), testCaseName+".txt");
          	   
          	   
			while ((line = br.readLine()) != null) // returns a Boolean value

			{

				String[] row = line.split(splitBy);

				String data = row[columnNoToBeValidated];
				
				String id = row[columnNoOfId];
				
				if(!"000".equals(data.trim())) {
					if( map.containsValue(data)) {
						logger.log(Status.FAIL, "<b>DUPLICATE CELL VALUE <b>" + "  " + "<b>ROW NUMBER--><b>" + count+"<br />"+ "<b> CIS NUMBER  : <b>" + id +"<br />" +"<b> ACTUAL CELL VALUE: <b>"+ data);
						textFile.writeTxtFile(testCaseName+".txt", "\n DUPLICATE CELL  VALUE ROW NUMBER -->" + count+ " CIS NUMBER : " + id +" ACTUAL CELL VALUE: " +data);
						
						
					} else {
						map.put(data, data);
					}
				}
					
				
				count++;

				

			}

			if (flag)

			{

				logger.log(Status.PASS, "THERE IS NO DUPLICATE CELL VALUE IN THE FILE");

			}

			extent.flush();
			textFile.closeTxtFile();

		} catch (Exception e) {

			e.printStackTrace();

			System.out.println(e.getMessage());

		}

	}
	/*
	 * ALAGURAJA
	 * 
	 * validate Month is proper format or not
	 * 
	 */

	public static boolean validateMonthFormat(String s1) {

		String regex = "^(3[01]|[12][0-9]|0[1-9])(1[0-2]|0[1-9])[0-9]{4}$";

		// Creating a pattern object

		Pattern pattern = Pattern.compile(regex);

		// Matching the compiled pattern in the String

		Matcher matcher = pattern.matcher(s1);

		boolean bool = matcher.matches();

		if (bool) {

			SimpleDateFormat sdf = new SimpleDateFormat("ddmmyyyy");

			sdf.setLenient(false);

			try {

				Date d1 = sdf.parse(s1);

				return true;

			} catch (ParseException e) {

				return false;

			}

		} else

			return false;

	}

}