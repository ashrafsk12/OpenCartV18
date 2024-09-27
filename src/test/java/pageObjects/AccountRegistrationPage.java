package pageObjects;

//import java.time.Duration;

//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;

public class AccountRegistrationPage extends BasePage
{
	
	public AccountRegistrationPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-firstname']") 
	WebElement txt_Firstname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_Lastname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_Email;
	
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_Telephone;
	
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_Confirm_Password;
	
	@FindBy(xpath="//label[normalize-space()='Yes']")
	WebElement btn_Subscribe;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement btn_PrivacyPolicy;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_Continue;
	
	@FindBy(xpath ="//h1 [normalize-space()= 'Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	//ActionMethods
	
	public void setFirstname(String fname)
	{
		txt_Firstname.sendKeys(fname);
	}
	public void setLastname(String lname)
	{
		txt_Lastname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txt_Email.sendKeys(email);
	}
	
	public void setTelephone(String tel)
	{
		txt_Telephone.sendKeys(tel);
	}
	
	public void setPassword(String pwd)
	{
		txt_Password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String confirmpassword)
	{
		txt_Confirm_Password.sendKeys(confirmpassword);
	}
	
	public void clickSubscribe()
	{
		btn_Subscribe.click();
	}
	

	public void clickPrivacyPolicy()
	{
		btn_PrivacyPolicy.click();
	}
	
	public void clickContinue()
	{
		//sol1
		btn_Continue.click();
		
		//sol2
		//btn_Continue.submit();
		
		//sol3 (Actions class object)
		//Actions act=new Actions(driver);
		//act.moveToElement(btn_Continue).click().perform();
		
		//sol4(javascript executor)
		//JavascriptExecutor js=(JavascriptExecutor)driver;
		//js.executeScript("arguments[0].click();",btn_Continue );
		
		//sol5
		//btn_Continue.sendKeys(Keys.RETURN);
		
		//sol5(Explicit wait)
		//WebDriverWait mywait=new WebDriverWait(driver,Duration.ofSeconds(10));
		//mywait.until(ExpectedConditions.elementToBeClickable(btn_Continue)).click();
		
	}	
		public String getConfirmationMsg(){
			try {
				return (msgConfirmation.getText());	
			}
			catch(Exception e)
			{
			return(e.getMessage());
			}	
		}
}
