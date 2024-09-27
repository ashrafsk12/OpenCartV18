package testCases;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass{
	
	@Test(groups={"sanity","Master"})
	public void verify_account_registration() 
	{
		loger.info("**** Starting TC001_AccountRegistration*******");
		
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		loger.info("**Here We Clicking On MyAccount...**");
		hp.clickRegister();
		loger.info("**Here We Clicking on Register Link...**");
		 AccountRegistrationPage regpge=new AccountRegistrationPage(driver);
		 
		 loger.info("We Providing the CustomerDetails.... ");
		  regpge.setFirstname(randomeString().toUpperCase());
		 regpge.setLastname(randomeString().toUpperCase());
		 regpge.setEmail(randomeString()+"@gmail.com");   //randomly generated email
		 regpge.setTelephone(randomeNumber());
		 
		 String password=randomeAlphaNumeric();
		 regpge.setPassword(password);
		 regpge.setConfirmPassword(password);
		 regpge.clickSubscribe();
		 regpge.clickPrivacyPolicy();
		 regpge.clickContinue();;
		 String confirmationmsg=regpge.getConfirmationMsg();
		 
		 loger.info("Validationg Expected Message....");
		 
		 if(confirmationmsg.equals("Your Account Has Been Created!"))
		 {
			 Assert.assertTrue(true);
			 loger.info("Test is passed");
		 }
		 
		 else
		 {
			 loger.error("Test Case Failed....");
			 loger.debug("debug logs");
			 Assert.assertTrue(false);
			  }
		}
		
		 catch(Exception e)
		{
			Assert.fail(); 
		}
		
		loger.info("**** Finsihed TC001_AccountRegistration Test*******");
		 
		 //Assert.assertEquals(confirmationmsg, "Your Account Has Been Created!");
	
	}
}
