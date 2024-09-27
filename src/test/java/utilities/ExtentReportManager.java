package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
//import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//import org.apache.commons.mail.DefaultAuthenticator;
//import org.apache.commons.mail.ImageHtmlEmail;
//import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext; 
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports. ExtentReports;
import com.aventstack.extentreports. ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration. Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener{
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;

	String repName;
	
	public void onStart(ITestContext testContext) {

		/*SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

		Date dt=new Date();

		String currentdatetimestamp=df.format(dt);
		*/

		String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());// time stamp

		repName = "Test-Report-" + timestamp + ".html";

		sparkReporter = new ExtentSparkReporter (".\\reports\\" + repName);// specify location of the report

		sparkReporter.config().setDocumentTitle("opencart Automation Report"); // Title of report 
		
		sparkReporter.config().setReportName("opencart Functional Testing"); // name of the report

		sparkReporter.config().setTheme (Theme.DARK);

		extent = new ExtentReports();

		extent.attachReporter (sparkReporter);

		extent.setSystemInfo("Application", "opencart"); 
		
		extent.setSystemInfo("Module", "Admin");

		extent.setSystemInfo("Sub Module", "Customers");
		
		extent.setSystemInfo("User Name", System.getProperty("user.name"));  //Tester Name

		extent.setSystemInfo("Environemnt", "QA");
		
		

		String os = testContext.getCurrentXmlTest().getParameter("os");  //getiing  dynamically xml file parameters(os,browser) to report

		extent.setSystemInfo("Operating System", os);   //capture operanting system details from xml file to report
 
		String browser = testContext.getCurrentXmlTest().getParameter("browser"); 

		extent.setSystemInfo("Browser", browser);   //capture browser details from xml file to report

		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups(); //here groups info also we can populated in report
		
		if(!includedGroups.isEmpty()) {  //if it is not empty display group names ,if it is empty dont display

		extent.setSystemInfo("Groups", includedGroups.toString());

		}
	}
	public void onTestSuccess (ITestResult result) {

		test=extent.createTest(result.getTestClass().getName());  //CREATING NEW ENTRY IN REPORT,
		//from result, we are getting the class from the class we're getting the name with that name we are Creating the test in report
		

		test.assignCategory(result.getMethod().getGroups()); // to display groups in report test.log(Status.PASS, result.getName()+" got successfully executed");

		}

		public void onTestFailure (ITestResult result) //a test method is got failed then on test failue method will be triggerrd
		{

		test =extent.createTest(result.getTestClass().getName());
		// from result,we are getting the class from the class we're getting the name with that name we are Creating the test in report

		test.assignCategory(result.getMethod().getGroups());  //(category=group) is same

		test.log(Status.FAIL, result.getName()+" got failed"); // along with the error status we have printed
		test.log(Status.INFO, result.getThrowable().getMessage());  //we also printed the error message 
		
		//apart from that  we should also attach the screenshot of the report,
		//whatever we created in the base class we should  also add one more a method which will capture the screenshot,
		//captureScreen method is common for all test cases so we can create in base class
		
		try {
				String imgPath = new BaseClass().captureScreen(result.getName()); //creating object for baseclass and storing in imgpath variable
				test.addScreenCaptureFromPath(imgPath);  //here screenshot will attach to report
			}
		
		catch (IOException e1)
		{ e1.printStackTrace(); //printstacktrace prdefined mthd,this will just display the exception message  

		}

	}
		/*if the screenshot is not properly taken or screenshot is not available it is still trying to attach  

the screenshot to the report so in that case :File not found exception you will get,

in that case we'll get a file not found exception  so we will put in the try catch block if the screenshot available 

no problem catch block will not execute if the screenshot is not available then it  

will throw some exception so just we are capturing that exception in catch block */

		
		public void onTestSkipped(ITestResult result) 
		{ 
			test=extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName()+" got skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());

		}
			
		public void onFinish(ITestContext testContext) 
		
		{
			extent.flush();
			
			/*suppose as soon as I have executed my test cases as soon as my report is got generated 
			 immediately it will automatically  open for that  we can add this piece of code below,
			 */

			String pathofExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;

			File extentReport = new File(pathofExtentReport);

			try {

			Desktop.getDesktop().browse (extentReport.toURI()); //this method will open this report on the browser automatically,
			
			// we no need to open the report manually

			} 
			catch (IOException e) 
			{
			e.printStackTrace();

			}
		
}

//if this external report is not available this will throw some exception so that's the  

//reason we put in the TRY catch block so this is  additional piece of code,optional code. 
	
	
	


/*suppose as soon as you generated your report,so you want  
to send that report to your team automatically as soon as you automation tests are completed,
then you can enable this piece of code below, */
			
			/*
			try { 
			
			 URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);  //convert report in url format

			// Create the email message

			ImageHtmlEmail email = new ImageHtmlEmail();

			email.setDataSourceResolver (new DataSourceUrlResolver(url));
			
			 email.setHostName("smtp.googlemail.com"); //(this works only for mail ids)

			email.setSmtpPort(465);

			email.setAuthenticator(new DefaultAuthenticator("sskashraf@gmail.com","password")); //own email id and password
			
			email.setSSLOnConnect(true);

			email.setFrom("sskashraf@gmail.com"); //Sender (our mail id address)
			
			email.setSubject("Test Results");

			email.setMsg("Please find Attached Report...."); 

			email.addTo("pavankumar.busyqa@gmail.com,,,"); //Receiver(to whom you want send single mail id or multiple mails) 
			
			email.attach(url, "extent report", "please check report...");

			email.send(); // send the email
			}

			catch(Exception e) { 
			
			e.printStackTrace();
			 
			 }
		}*/
		
}


