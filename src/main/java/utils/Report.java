package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.json.JSONException;
import org.json.JSONObject;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.eclipsesource.json.JsonObject;

import generic.GenericWrappers;
import io.restassured.response.Response;


public class Report {

	public static ExtentHtmlReporter obj;
	public static ExtentReports reports;
	public ExtentTest test;
	

	/*public Report() {
		GenericWrappers.prop = new Properties();
		try {
			GenericWrappers.prop.load(new FileInputStream("./prop/Execution.properties"));
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("File not found");
		}
		Path = GenericWrappers.prop.getProperty("ReportPath");
		ReportTheme = GenericWrappers.prop.getProperty("ReportTheme");
		ReportName = GenericWrappers.prop.getProperty("ReportName");
	}*/

	public void startReport(String testcasename) {
		obj = new ExtentHtmlReporter("./reports/"+testcasename+"report.html");
		reports = new ExtentReports();
		reports.attachReporter(obj);
		obj.config().setTheme(Theme.STANDARD);
		obj.config().setReportName(testcasename + "Execution Report");
	}

	public void startTest(String testcasename, String testDesc) {
		test = reports.createTest(testcasename, testDesc);
	}

	public void logRequestInfo(String Method, String url,String Endpoint, Map<String, Object> QueryParameters,
			Map<String, Object> Headers) {
		test.log(Status.INFO, "Request Details :" + "<li>" + "Method Name : " + Method + "<li>" + "Base URL is :" + url
				+ "<li>" + "Endpoint :"+Endpoint+"<li>"+"Query Parameters are : " + QueryParameters + "<li>" + "Headers details :" + Headers);
	}
	
	public void logRequestInfo(String arg1, String arg2){
		test.log(Status.INFO, arg1 + ":" + arg2);
	}

	public void statusInfo(Response response) {
		test.log(Status.INFO,
				"Response Details :" + "<li>" + "Response Code Is :-" + Integer.toString(response.getStatusCode())
						+ "<li>" + "Response Line Is :- " + response.getStatusLine() + "<li>" + "Response Header :- "
						+ response.getHeaders());
	}

	public void logResponse(String status, Response response) {
		statusInfo(response);
		JSONObject json = null;
		if (response.asString().startsWith("{")) {
			try {
				json = new JSONObject(response.asString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			if (status.equalsIgnoreCase("Pass")) {
				test.log(Status.PASS, MarkupHelper.createCodeBlock(json.toString(), CodeLanguage.JSON));
				//GenericWrappers.log.info("Respose is " + response.getBody().asString());
			} else if (status.equalsIgnoreCase("Fail")) {
				test.log(Status.FAIL, MarkupHelper.createCodeBlock(json.toString(), CodeLanguage.JSON));
				//GenericWrappers.log.info("Respose is " + response.getBody().asString());
			}
		} else {
			if (status.equalsIgnoreCase("Pass")) {
				test.log(Status.PASS, "Body not returned");
			} else if (status.equalsIgnoreCase("Fail")) {
				test.log(Status.FAIL, "Body not returned");
			}
		}
	}
	public void endReport(){
		reports.flush();
	}
	
}
