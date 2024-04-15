package Data;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.annotations.DataProvider;

import TestComponent.BaseTest;

public class DataSupplier1 extends BaseTest {

	@DataProvider()
	public Object[][] getLoginData() throws IOException // we are providing this data from json file
	{
		List<HashMap<String, String>> data = getJsonDataToMapLogin(
				System.getProperty("user.dir") + "//src//test//java//Data//login.json");
		return new Object[][] { { data.get(0) }};
	}
	
	


}
