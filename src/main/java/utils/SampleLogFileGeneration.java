package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleLogFileGeneration {
	
	@Test
	public void sample() throws IOException{
	
		/*File file = new File("logs/log.log");
		file.createNewFile();
		System.setOut(new PrintStream(new FileOutputStream(file)));
		*/
		
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification requestSpecification = RestAssured.given().request().log().all();
		System.out.println("hi");
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("content-type", "application/json");
		requestSpecification.headers(headers);
		Map<String, String> query = new HashMap<String, String>();
		query.put("page", "2");
		
		requestSpecification.queryParameters(query);
		Response response = requestSpecification.get("/api/users");
		response.prettyPrint();
		
		
	}

}
