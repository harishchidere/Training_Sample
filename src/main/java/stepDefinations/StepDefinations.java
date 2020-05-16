package stepDefinations;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import generic.GenericWrappers;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class StepDefinations {
	
	GenericWrappers g;
	
	@When("user executes {string} which is {string}")
	public void user_executes_which_is(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
		g = new GenericWrappers();
		g.startReport(string);
	    g.startTest(string, string2);
	}
	
	@When("user loads the base url as {string}")
	public void user_loads_the_base_url_as(String string) {
	   
		g.loadBaseUrl(string);
		
	}

	@When("user loads the headers")
	public void user_loads_the_headers(DataTable dataTable) {
	   
		List<List<String>> list = dataTable.asLists();
		g.loadHeaders(list);
	}

	@When("user loads the query parameters")
	public void user_loads_the_query_parameters(DataTable dataTable) {
		List<List<String>> list = dataTable.asLists();
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < list.size(); i++) {
			map.put(list.get(i).get(0), list.get(i).get(1));
		}
		g.loadQueryParameters(map);
	}

	@When("user sends the get request with endpoint url as {string}")
	public void user_sends_the_get_request_with_endpoint_url_as(String string) {
	
		g.sendGetRequest(string);
	}
	
	
}
