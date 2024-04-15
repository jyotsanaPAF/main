package Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;
import TestComponent.BrowserSetup;

public class DataSupplier2 extends BrowserSetup {
	
	
	@DataProvider()
	public Object[][] getGitaEnquiryFormData() throws IOException // we are providing this data from json file
	{
		List<HashMap<String, String>> data = getJsonDataToMapGitaEnquiryForm(
				System.getProperty("user.dir") + "//src//test//java//Data//gitaEnquiryForm.json");
		return new Object[][] { { data.get(0) } };
	}
	
	@DataProvider()
	public Object[][] getVBCEnquiryFormData() throws IOException // we are providing this data from json file
	{
		List<HashMap<String, String>> data = getJsonDataToMapVBCEnquiryForm(
				System.getProperty("user.dir") + "//src//test//java//Data//VBCEnquiryForm.json");
		return new Object[][] { { data.get(0) } };
	}
	
	@DataProvider()
	public Object[][] getDonationFormData() throws IOException // we are providing this data from json file
	{
		List<HashMap<String, String>> data = getJsonDataToMapGitaEnquiryForm(
				System.getProperty("user.dir") + "//src//test//java//Data//donation.json");
		return new Object[][] { { data.get(0) } };
	}
	
	@DataProvider()
	public Object[][] getDonationReceiptFormData() throws IOException // we are providing this data from json file
	{
		List<HashMap<String, String>> data = getJsonDataToMapGitaEnquiryForm(
				System.getProperty("user.dir") + "//src//test//java//Data//donationReceipt.json");
		return new Object[][] { { data.get(0) } };
	}

}
