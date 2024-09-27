package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

//Scnario--Postive & Negative Testing,

//Data is valid-login success--test pass---logout
//Data is valid--login failed---test fail

//Data is invalid--login success--test fail---logout
//Data is invalid---login failed--test pass


public class TC003_LoginDDT extends BaseClass
{
	
	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups="DataDriven")  //getting dataprovider from different class
	public void verify_loginDDT(String email,String pwd,String exp)
	{
		try {
			loger.info("****Started TC003_LoginDDT*****");
		//HomePage
			HomePage hp=new HomePage(driver);
			
			hp.clickMyAccount();
			hp.clickLogin();
			
			//LoginPage
			LoginPage lp=new LoginPage(driver);
			lp.setEmail(email);
			loger.info("Giving Email Address....");
			
			lp.setPassword(pwd);
			loger.info("Giving Password....");
			
			lp.clickLogin();
			loger.info("Clicked on Login");
			
			//MyAccountPage
			 MyAccountPage macc=new MyAccountPage(driver);
			 
			 loger.info("Validating My Account page is Exist or Not");
			 
			 boolean targetpage=macc.isMyAccountExist();
			 
			 //Assert.assertEquals(targetpage, true,"Login failed");
			 loger.info("test case is passed,PAGE EXIST");
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetpage==true)
			{
				macc.clickLogoutLink();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetpage==true)
			{
				macc.clickLogoutLink();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		}
		catch(Exception e)
		{
			loger.info("****Finshed TC003_LoginDDT*****");
			Assert.fail();
		}
		
	}
	

}
