package TestCases;
import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import Data.DataSupplier2;
import PageObject.Donation;
import TestComponent.BaseTest2;

public class DonationTest extends BaseTest2{

	public Donation donation;

	@Test(priority = 0)
	//Verify that user is able to donate amount, Successfull !!")
	public void donateForFoundation() throws InterruptedException, IOException 
	{
		donation=new Donation(driver);
		donation.donate("1000", "Jyotsana", "9310980049", "jp1@yopmail.com", "Delhi");
		donation.verifyDonationReceipt("110044", "Delhi", "New Delhi", "Aadhaar Card", "111122223333");
	}
	
	
//	@Test(priority = 1, dependsOnMethods= {"donateForFoundation"}, dataProvider = "getDonationReceiptFormData", dataProviderClass = DataSupplier2.class)
//	//Verify that user is able get the donation receipt, Successfull !!")
//	public void donationReceipt(HashMap<String, String> hm) throws InterruptedException, IOException 
//	{
//		donation.verifyDonationReceipt(hm.get("pinCode"), hm.get("state"), hm.get("city"), hm.get("idProof"), hm.get("adhar"));
//	}
}
