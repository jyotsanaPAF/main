package PageObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class Donation extends AbstractComponent {

	static WebDriver driver;

	public Donation(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "(//span[@class='font-en svelte-t3mcl'][normalize-space()='Donate'])[1]")
	WebElement donate; // donate button on header

	// @FindBy(xpath = "(//span[@class='font-en
	// svelte-t3mcl'][normalize-space()='Donate'])[4]")
	@FindBy(xpath = "//button[@class='flex items-center rounded-full hover:bg-brand-700 hover:text-white btn-lg shadow-none h-full text-white btn-type-solid svelte-uvdaqi']")
	WebElement donateButton1; // donate button
	
	@FindBy(xpath ="(//span[@class='font-en svelte-t3mcl'][normalize-space()='Donate'])[3]")
			WebElement donateButton;

	@FindBy(id = "amount")
	WebElement amountField; // Amount field to enter amount

	@FindBy(id = "name")
	WebElement nameField; // name field to enter name

	@FindBy(id = "mobile")
	WebElement mobileField; // mobile no. field to enter mobile no.

	@FindBy(id = "email")
	WebElement emailField; // email field to enter email

	@FindBy(id = "address")
	WebElement addressField; // address field to enter address

	@FindBy(id = "disclaimer")
	WebElement tncCheckbox; // accept terms & conditions checkbox

	@FindBy(xpath = "(//span[@class='font-en svelte-t3mcl'][normalize-space()='Donate'])[3]")
	WebElement formDonateButton; // donate button on form

	By thankyouPage = By.xpath("//span[text()='Thank you for your contribution.']");

	@FindBy(xpath = "//span[text()='Thank you for your contribution.']")
	WebElement thankyou; // thank you page

	@FindBy(xpath = "//span[text()='Download Receipt']")
	WebElement downloadReceiptButton; // Download receipt button on thank you page

	By downloadReceiptPage = By.xpath("//span[text()='Download Receipt']");

	@FindBy(id = "form-field-pincode")
	WebElement pinCodeTextField; // Pin code text field

	@FindBy(id = "form-field-state")
	WebElement stateField; // State field

	@FindBy(id = "form-field-search-city")
	WebElement cityField; // City field

	@FindBy(css = ".h-full.overflow-y-scroll")
	List<WebElement> cityList; // City list from dropdown
	
	@FindBy(id = "form-field-pid")
	WebElement idProofDropdown; // Pan or adhar selection dropdown
	
	@FindBy(id = "form-field-aadhaar")
	WebElement aadhaarTextField; // adhar text field
	
	@FindBy(id = "form-field-disclaimer")
	WebElement checkbox; // terms & conditions checkbox
	
	@FindBy(xpath = "//div[@class='my-4 rounded-full tab:w-56 mx-auto']/div/span[text()='Download Receipt']")
	WebElement downloadReceiptButton2; // Download receipt button on download receipt page


	public void donate(String amount, String name, String mobile, String email, String address)
			throws InterruptedException, IOException {
		donate.click();
		scrollDown();
		donateButton.click();
		amountField.sendKeys(amount);
		nameField.sendKeys(name);
		mobileField.sendKeys(mobile);
		emailField.sendKeys(email);
		addressField.sendKeys(address);
		scrollDown();
		tncCheckbox.click();
		formDonateButton.click();
		Thread.sleep(1000);
		razorpayPayment(); // method in AbstractComponent.java
		waitForElementToAppear(thankyouPage);
		System.out.println(thankyou.getText());
		Assert.assertEquals(thankyou.getText(), "Thank you for your contribution.");
//		scrollDown();
//		downloadReceiptButton.click();
//		waitForElementToAppear(downloadReceiptPage);
//		scrollDown();
//		enterDetailsInDownloadReceiptPage("110044", "Delhi", "New Delhi", "Aadhaar Card", "111122223333");
//		Thread.sleep(2000);
//		verifyDonationReceipt();
	}
	
	public void verifyDonationReceipt(String pinCode, String state, String city, String idProof, String adhar) throws InterruptedException, IOException
	{
		scrollDown();
		downloadReceiptButton.click();
		waitForElementToAppear(downloadReceiptPage);
		scrollDown();
		enterDetailsInDownloadReceiptPage(pinCode, state, city, idProof, adhar);
		Thread.sleep(2000);
		readReceiptPDF();
	}

	public void enterDetailsInDownloadReceiptPage(String pinCode, String state, String city, String idProof, String adhar) throws InterruptedException {
		pinCodeTextField.sendKeys(pinCode); // Enter pin code
		selectByElement(stateField, state); // Select state from dropdown
		cityField.click(); // Select city from dropdown
		for (WebElement el : cityList) {
			if (el.getText().contains(city)) {
				el.click();
			}
		}
		selectByElement(idProofDropdown, idProof); // Select state from dropdown
		aadhaarTextField.sendKeys(adhar);
		checkbox.click();
		scrollDown();
		downloadReceiptButton2.click();
	}
	
	public void readReceiptPDF() throws IOException
	{
		String pdfUrl = driver.getCurrentUrl();
		System.out.println(pdfUrl);
		
		//read data from pdf
		URL url =new URL(pdfUrl);
		InputStream is= url.openStream();
		BufferedInputStream bis=new BufferedInputStream(is);
		PDDocument doc = PDDocument.load(bis);
		String pdfContent=new PDFTextStripper().getText(doc);
		Assert.assertTrue(pdfContent.contains("PrashantAdvait Foundation"));
		doc.close();
	}

}
