package generic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import utils.Report;

public class GenericWrappers extends Report{
	
	public RequestSpecification requestSpecification;
	
	public void loadBaseUrl(String baseUrl){
		RestAssured.baseURI = baseUrl;
		logRequestInfo("Base Url", baseUrl);
	}
	
	public void loadHeaders(List<List<String>> list){
		
		requestSpecification = RestAssured.given().request().urlEncodingEnabled(false);
		
		Map<String, String> map = new HashMap<String, String>();
		String status = null;
		String finalStatus = "";
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).get(0), list.get(i).get(1));
			status = "<li>"+list.get(i).get(0)+"="+list.get(i).get(1);
			finalStatus = finalStatus + status;
		}
		
		requestSpecification.headers(map);
		
		logRequestInfo("Hearders Details", finalStatus);
	}
	
	public void loadQueryParameters(Map<String, String> map){
		
		requestSpecification.queryParameters(map);
		logRequestInfo("Query Parameters", "<li>"+ map);
	}
	
	public void sendGetRequest(String url){
		Response response= null;
		try {
			 response = requestSpecification.get(url);
			logRequestInfo("Endpoint Url :", url);
			
			response.prettyPrint();
			logResponse("Pass", response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logRequestInfo("Endpoint Url ", url);
			statusInfo(response);
			logResponse("Fail", response);
		}
	}

}
