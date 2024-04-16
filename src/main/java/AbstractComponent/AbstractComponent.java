package AbstractComponent;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "razorpay-checkout-frame")
	WebElement razorPayiFrame;

	@FindBy(xpath = "//div[text()='Netbanking']")
	WebElement netBanking; // net banking payment option

	@FindBy(xpath = "//div[text()='HDFC']")
	WebElement hdfc; // hdfc netbanking option

	@FindBy(xpath = "//div[text()='Netbanking - HDFC Bank']")
	WebElement hdfcNetbanking;

	@FindBy(xpath = "//div[text()='SBI']")
	WebElement sbi; // sbi netbanking option

	@FindBy(xpath = "//button[text()='Pay Now']")
	WebElement payNowButton; // Pay now button

	@FindBy(className = "success")
	WebElement successButton;

	public void waitForElementToAppear(By findBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void scrollDown() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,250)", "");
		Thread.sleep(500);
	}

	public void scrollDownDynamic(int px) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0," + px + ")", "");
	}

	public void scrollUpBy500() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-500)", "");
		Thread.sleep(1000);
	}

	public void scrollUp() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0, -200)", "");
	}

	public void scrollIntoView(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void razorpayPayment() throws InterruptedException {
		switchToFrame(); // switch to razor pay payment i frame window

//		check if the user have to enter the phone number before proceed to pay
		try {
			WebElement phoneInputField = driver.findElement(By.id("contact"));
			WebElement proceedButton = driver.findElement(By.id("redesign-v15-cta"));

			phoneInputField.sendKeys("8654217847");
			proceedButton.click();
		} catch (Exception e) {

		}

		scrollDown();
		WebElement netBankingOption = driver.findElement(By.xpath("//button[contains(@method,'netbanking')]"));
		waitForElementToBeClickable(netBankingOption);

		netBankingOption.click();

//		netBanking.click(); // Click on net banking
//		Thread.sleep(1000); // wait for net banking option get displayed
//		sbi.click(); // click on SBI
//		waitForElementToBeClickable(hdfcNetbanking);
//		hdfcNetbanking.click();

		WebElement SBI = driver.findElement(By.id("bank-item-SBIN"));
		SBI.click();
		payNowButton.click(); // click on pay now button
		Thread.sleep(1000); // wait for success window
		switchToChildWindow(); // switch on success(child)window
		successButton.click(); // click on success button
		switchToParentWindow(); // switch on main(parent)window
	}

	public void switchToFrame() throws InterruptedException {
		driver.switchTo().frame(razorPayiFrame); // switch to razor pay payment i frame window
		// Thread.sleep(1000);
	}

	public void switchToChildWindow() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
	}
	
//	get the current url
	public String getCurrentUrl() {
		String url = driver.getCurrentUrl();
		return url;
	}

	public void switchToParentWindow() {
		Set<String> windows = driver.getWindowHandles();
		Iterator<String> it = windows.iterator();
		String parentId = it.next();
		// String childId = it.next();
		driver.switchTo().window(parentId);
	}

	public static void selectByElement(WebElement ele, String string) {
		Select select = new Select(ele);
		select.selectByVisibleText(string);
	}

	public void redirectToPage(String url) {
		driver.get(url);
	}

	public WebElement getElementByXpath(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return element;
	}

	public List<WebElement> getElements(String xpath) {
		List<WebElement> list = driver.findElements(By.xpath(xpath));
		return list;
	}

	public List<WebElement> getElementsById(String id) {
		List<WebElement> list = driver.findElements(By.id(id));
		return list;
	}

	public List<WebElement> getElementsByRelatedXpath(WebElement baseElement, String relatedXpath) {
		List<WebElement> list = baseElement.findElements(By.xpath(relatedXpath));
		return list;
	}

	public WebElement getElementByRelatedXpath(WebElement baseElement, String relatedXpath) {
		WebElement element = baseElement.findElement(By.xpath(relatedXpath));
		return element;
	}

	public WebElement getElementById(String id) {
		WebElement element = driver.findElement(By.id(id));
		return element;
	}

	public WebElement accessElementByIndex(List<WebElement> elements, int index) {
		WebElement selectedElement = elements.get(index);
		return selectedElement;
	}
	
	public WebElement accessFirstElementFromList(List<WebElement> elements) {
	//	WebElement selectedElement = elements.getFirst();
		WebElement selectedElement = elements.get(0);
		
		return selectedElement;
	}
	
	public WebElement accessLastElementFromList(List<WebElement> elements) {
		//WebElement selectedElement = elements.getLast();
		WebElement selectedElement =elements.get(elements.size()-1);
		return selectedElement;
	}

//	public WebElement filterElementFromListUsingInternalText(List<WebElement> list, String text) {
//		WebElement matchedElement = list.stream().filter(item -> item.getText().equalsIgnoreCase(text)).findFirst()
//				.orElse(null);
//		return matchedElement;
//	}
//	
//	public WebElement filterElementFromListUsingChildElementText(List<WebElement> list, String xpath, String text) {
//		WebElement matchedElement = list.stream().filter(item -> item.findElement(By.xpath(xpath)).getText().equalsIgnoreCase(text)).findFirst().orElse(null);
//		return matchedElement;
//	}

}

