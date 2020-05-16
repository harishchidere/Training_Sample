Feature: Title of your feature
I want to use this template for my feature file

@TC001
Scenario: Facebook Testcase
When user executes "TC_001" which is "description"
When user loads the base url as "https://reqres.in/"
And user loads the headers
|content-type|application/json|
|accept|application/json|
And user loads the query parameters
|page|2|
And user sends the get request with endpoint url as "/api/users"
