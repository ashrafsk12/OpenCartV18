package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass{
	
	@Test(groups={"Regression","Master"})
	public void verify_Login()
	{
		loger.info("****TC002_LoginTest Started...****");
		
		try {
		//HomePage
		HomePage hp=new HomePage(driver);
		
		hp.clickMyAccount();
		hp.clickLogin();
		
		//LoginPage
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(p.getProperty("email"));
		loger.info("Giving Email Address....");
		
		lp.setPassword(p.getProperty("password"));
		loger.info("Giving Password....");
		
		lp.clickLogin();
		loger.info("Clicked on Login");
		
		//MyAccountPage
		 MyAccountPage macc=new MyAccountPage(driver);
		 
		 loger.info("Validating My Account page is Exist or Not");
		 
		 boolean targetpage=macc.isMyAccountExist();
		 
		 //Assert.assertEquals(targetpage, true,"Login failed");
		 loger.info("test case is passed,PAGE EXIST");
		 Assert.assertTrue(targetpage);
		}
		catch(Exception e)
		{
			loger.info("test case is failed");
			Assert.fail();
		}
		 loger.info("****TC002_LoginTest is Finished...****");
	}
	

}
