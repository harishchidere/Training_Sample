package stepDefinations;

import generic.GenericWrappers;
import io.cucumber.java.After;
import io.cucumber.java.Before;


public class PreConditions extends GenericWrappers {
	
	@Before
	public void before(){
		System.out.println("Normal Hooks");	
	}
	
	@After
	public void after(){
		System.out.println("Normal Hooks After");
		endReport();
	}
	

}
