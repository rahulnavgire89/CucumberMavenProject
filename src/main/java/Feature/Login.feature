Feature: Login feature

@E2ETest
Scenario: Login
Given Userenter URL
And Logs in
Then click on the Deals and new deals

Then fill the details and save it 
|Title|Company|PrimaryCon|Amount|
|TestCucumber|HCL|HYD|13000|

Then Cilck on the Deals>Products>Enter the below data and create the product
|Name|Cost|Retail_value|Wholesale_price|SKU|Amount|Desc|
|Test|450|950|1050|test1|1|Test|
#
#@SmokeTest
#Scenario: Login
#Given Userenter URL
#And Logs in
#
#@regression
#Scenario: Login
#Given Userenter URL
#And Logs in
#Then click on the Deals and new deals
#
#Then fill the details and save it 
#|Title|Company|PrimaryCon|Amount|
#|TestCucumber|HCL|HYD|13000|
