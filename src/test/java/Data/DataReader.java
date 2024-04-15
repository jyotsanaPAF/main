package Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	// this is utility class

/*	public List<HashMap<String, String>> getJsonDataToMap() throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//Data//login.json"),
				StandardCharsets.UTF_8);

		// convert string to hashmap
		// for those we need add one dependency in pom.xml "jackson Databind" this is
		// the dependency which convert
		// json string to hashmap

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	*/
	
	//Login data reader
	public List<HashMap<String, String>> getJsonDataToMapLogin() throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//Data//login.json"),
				StandardCharsets.UTF_8);

		// convert string to hashmap
		// for those we need add one dependency in pom.xml "jackson Databind" this is
		// the dependency which convert
		// json string to hashmap

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}
	
	
	//Gita enquiry form data reader
	public List<HashMap<String, String>> getJsonDataToMapGitaEnquiryForm() throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(
				new File(System.getProperty("user.dir") + "//src//test//java//Data//gitaEnquiryForm.json"),
				StandardCharsets.UTF_8);

		// convert string to hashmap
		// for those we need add one dependency in pom.xml "jackson Databind" this is
		// the dependency which convert
		// json string to hashmap

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

}
