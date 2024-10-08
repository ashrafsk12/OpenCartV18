package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		
	super(driver);
	}
	
	@FindBy(xpath="//h2[normalize-space()='My Account']")  //added in step 6.3
	WebElement msgHeading;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement logout_link;
	
	public boolean isMyAccountExist()
	{
		try {
		return msgHeading.isDisplayed();
		}
		catch(Exception e)
		{
			return false;
		}
	}
	
	public void clickLogoutLink()
	{
		logout_link.click();
	}

}
