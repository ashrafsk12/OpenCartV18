package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage        

//1)Costructor
{
	
	public HomePage	(WebDriver driver)
	{
		super(driver);                   //invoked immediate parent class Constructor variable by using super keyword,
		
	}
	
		
		 /*homepage,this.driver is not required again because we already created here and the homepage we have  
	
		created another Constructor because Constructor name should be same as a class so without creating  
		
		this Constructor we cannot invoke the parent class Constructor this is inheritance reusability 
		
		*Q) so from the homepage from the child class how  we can invoke the parent class Constructor can we invoke the parent class Constructor?
		
		A)yes we can invoke we have seen three parts during inheritance classes we have discussed immediate  

		parent class variable we can invoke immediate  parent class method also we can invoke immediate  	

		parent class Constructor also we can invoke by  using Super Keyword,
		
		
		 base page doesn't have any web elements action methods nothing this is just a reusable  component of all the page object 
		 
		 classes so all the page object classes should be derived from base page and why we have done like  
		 
		 this because to achieve the reusability simple concept instead of writing this page factory. 
		 
		 init elements every time in every class we just  created a separate class and where we mentioned only Constructor
*/
	
//2) Locators

	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement linkmyAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Register']")
	WebElement RegisterAccount;
	
	@FindBy(xpath="//ul[@class='dropdown-menu dropdown-menu-right']//a[normalize-space()='Login']")
	WebElement Loginlink;
	
//3)Action Methods for Above Locators
	
	public void clickMyAccount()
	{
		linkmyAccount.click();
		
	}
	
	public void clickRegister()
	{
		RegisterAccount.click();
	}
	
	public void clickLogin()
	{
		Loginlink.click();
		
	}
}

