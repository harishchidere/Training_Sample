Feature: Title of your feature
I want to use this template for my feature file

Background:
Given Launch Application with url as - "https://www.facebook.com/"

@TC001
Scenario: Facebook Testcase
When user executes "TC_001" which is "description"
When user enter data "Girish" into "firstname" textbox
And user enter data "Sabari" into "lastname" textbox
And user enter data "maggie@gmail.com" into "email" textbox
And user enter data "maggie@gmail.com" into "confirmemail" textbox
And user enter data "maggie" into "newpassword" textbox
And user Selects "31" from "date" dropdown
And Verify "Calender" list contains following Values
|month|Month|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sept|Oct|Nov|Dec|