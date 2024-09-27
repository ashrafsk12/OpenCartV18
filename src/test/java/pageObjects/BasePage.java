package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BasePage {                      //this class is parent of all page object classes
	WebDriver driver;
	
	public BasePage(WebDriver driver)        //this is Constructor or constructor method
	{
		this.driver=driver;
		
		PageFactory.initElements(driver,this);
	}

}

/*Q) Why we need to create separate class for Contructor?

A)  because all page object classes contain The  Constructor so instead of writing the

	Constructor in every page object class we separated  the Constructor in another class that is extended  
   
	into this Constructor for example here I created one more class base page and what is  

	this base page? the base page contains only Constructor nothing else only Constructor  

	web driver variable and constructtor that's it and  this particular base page is extended into every  
	
	page object class so  this is a parent of all the page object classes 
	
	so why I make this as a  parent? because the Constructor is same right if  

	you create any number of page object classes  for every page object class we have to create one Constructor right so instead of creating this  
	
	part for every page object class what have done I created a new class called base page I defined the  driver variable also have created one constructor  

	and this will take the driver
*/
