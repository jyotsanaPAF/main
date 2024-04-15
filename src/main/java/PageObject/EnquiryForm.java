package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import AbstractComponent.AbstractComponent;

public class EnquiryForm extends AbstractComponent {

	static WebDriver driver;

	public EnquiryForm(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="div[id='main-website-nav-item-in-place-live'] span[class='font-en svelte-t3mcl']")
	WebElement liveSession;
	
	@FindBy(xpath="//div[@class='rounded-md p-2 hover:bg-brand-100']")
	List<WebElement> sessions;
	
	By waitingForSessionList = By.xpath("//div[@class='rounded-md p-2 hover:bg-brand-100']");
	
	By waitingForJoinNowButton = By.xpath("//button[@class='flex items-center rounded-full hover:bg-brand-700 hover:text-white btn-2xl shadow-none h-full text-white btn-type-solid svelte-1tue00m']");
	
	@FindBy(xpath="//button[@class='flex items-center rounded-full hover:bg-brand-700 hover:text-white btn-2xl shadow-none h-full text-white btn-type-solid svelte-1tue00m']")
	WebElement joinNowButton;
	
	By waitingForNameTextField = By.id("form-field-name");
	
	@FindBy(id = "form-field-name")
	WebElement nameTextField; // name text field 
	
	@FindBy(id = "form-field-phone")
	WebElement phoneTextField; // phone text field in gita enquiry form
	
	@FindBy(id = "form-field-email")
	WebElement emailTextField; // email text field
	
	@FindBy(id = "form-field-age")
	WebElement ageTextField; // age text field
	
	@FindBy(id = "form-field-gender")
	WebElement genderField; // gender drop down field
	
	@FindBy(id = "form-field-listening-time")
	WebElement listeningToAcharyaPrashantSinceField; // //Listening to Acharya Prashant Since dropdown field
	
	@FindBy(id = "form-field-profession")
	WebElement professionField; // profession drop down field
	
	@FindBy(id = "form-field-country")
	WebElement countryField; // country drop down field
	
	@FindBy(id = "form-field-pincode")
	WebElement pincodeTextField; // pin code text field 
	
	@FindBy(id = "form-field-address")
	WebElement addressTextField; // address code text field 
	
	@FindBy(id = "form-field-state")
	WebElement stateField; // state drop down field 
	
	@FindBy(id = "form-field-search-city")
	WebElement cityField; // city field in gita enquiry form
	
	@FindBy(css = ".h-full.overflow-y-scroll")
	List<WebElement> cityList; // city list in gita enquiry form
	
	@FindBy(id = "form-field-is-default-address")
	WebElement defaultAddressCheckbox; // 'make this my default address' check box
	
	@FindBy(id = "form-field-disclaimer")
	WebElement tncCheckbox; // terms & conditions checkbox
	
	@FindBy(xpath = "//span[text()='Submit']")
	WebElement submit; // submit button
	
	@FindBy(xpath = "//div[@class ='mx-12 my-20 text-center text-slate-700']/div[2]")
	WebElement thankyouPage; // thank you page
	
	
	public void clickOnLiveSession()
	{
		liveSession.click();
	}
	
	public void selectSession(String session)
	{
		//List<WebElement> options = driver.findElements(By.xpath("//div[@class='rounded-md p-2 hover:bg-brand-100']"));
		//List<WebElement> options = sessions;
		for(WebElement el:sessions) 
		{
			
			if(el.getText().contains(session)) 
			{
				System.out.println(el.getText());
				el.click();
			}

		}
	}
	
	public void clickOnJoinNow()
	{
		joinNowButton.click();
	}
	
	public void gitaEnquiryFormDetails(String session, String name, String email, String phone, String age, String gender, String duration, String profession, String state, String city) throws InterruptedException
	{
		clickOnLiveSession();
		waitForElementToAppear(waitingForSessionList);
		selectSession(session);
		waitForElementToAppear(waitingForJoinNowButton);
		clickOnJoinNow();
		fillFormDetails(session, name, email, phone, age, gender, duration, profession, state, city );
		
	}
	
	public void VBCEnquiryFormDetails(String session, String name, String email, String phone, String age, String gender, String duration, String profession, String state, String city) throws InterruptedException
	{
		clickOnLiveSession();
		waitForElementToAppear(waitingForSessionList);
		selectSession(session);
//		waitForElementToAppear(waitingForJoinNowButton);
//		clickOnJoinNow();
		fillFormDetails(session, name, email, phone, age, gender, duration, profession, state, city );
		
	}
	
	public void fillFormDetails(String session, String name, String email, String phone, String age, String gender, String duration, String profession, String state, String city) throws InterruptedException
	{
		waitForElementToAppear(waitingForNameTextField);
		nameTextField.sendKeys(name);
		emailTextField.sendKeys(email);
		phoneTextField.sendKeys(phone);
		ageTextField.sendKeys(age);
		selectByElement(genderField, gender);
		selectByElement(listeningToAcharyaPrashantSinceField, duration);
		selectByElement(professionField, profession);
		scrollDown();
		selectByElement(stateField, state);
		Thread.sleep(1000);
		selectCity(city);
		tncCheckbox.click();
		submit.click();
		thankyouPage();
	}
	
	public void selectCity(String city)
	{
		cityField.click();
		System.out.println(cityList.size());
		driver.findElement(By.id("form-field-search-city-input-desktop")).sendKeys(city);
		
		   for(WebElement el:cityList) 
			{
				if(el.getText().contains(city)) 
				{
					el.click();
				}

			}
	}
	
	public void goTo()
	{
		driver.get("https://dev-main-master1.vedant.life/");
	}
	
	public void thankyouPage()
	{
		System.out.println(thankyouPage.getText());
		Assert.assertEquals(thankyouPage.getText(), "Thank you for submitting your enquiry");
	}

}
