package TestCases;

import java.util.HashMap;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Data.DataSupplier2;
import PageObject.EnquiryForm;
import TestComponent.BaseTest2;
import TestComponent.BrowserSetup;

public class EnquiryTest extends BaseTest2 {

	public EnquiryForm enquiryform;
//
//	@BeforeMethod
//	@Parameters("browser")
//	public void setup(String browser)  {
//	  driver = BrowserSetup.getDriver(browser);
//	  driver.get("https://dev-main-master1.vedant.life/");
//	}
	
//	@Test(priority = 0, dataProvider = "getGitaEnquiryFormData", dataProviderClass = DataSupplier2.class)
	//Verify that user is able to fill gita enquiry form, Successfull !!")
	public void fillGitaEnquiryForm(HashMap<String, String> hm) throws InterruptedException {
		enquiryform = new EnquiryForm(driver);
		enquiryform.gitaEnquiryFormDetails(hm.get("session"), hm.get("name"), hm.get("email"), hm.get("phone"), hm.get("age"), hm.get("gender"), hm.get("duration"), hm.get("profession"), hm.get("state"), hm.get("city"));
	}
	
	@Test(priority = 0, dataProvider = "getVBCEnquiryFormData", dataProviderClass = DataSupplier2.class)
	//Verify that user is able to fill gita enquiry form, Successfull !!")
	public void fillVBCEnquiryForm(HashMap<String, String> hm) throws InterruptedException {
		enquiryform = new EnquiryForm(driver);
		enquiryform.VBCEnquiryFormDetails(hm.get("session"), hm.get("name"), hm.get("email"), hm.get("phone"), hm.get("age"), hm.get("gender"), hm.get("duration"), hm.get("profession"), hm.get("state"), hm.get("city"));
	}
}
