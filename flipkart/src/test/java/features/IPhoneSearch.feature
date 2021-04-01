#Author: rupamsinha2012@gmail.com

@IPhoneSearchUnder40000
Feature: IPhoneSearch
  This feature file is written to perform IPhone search having a maximum price of INR 40,000 on flipkart website.

  @IphoneSearch
  Scenario Outline: User search for iPhones having a maximum price of INR 40,000 on flipkart website 
    Given user is at home page of flipkart website
    When user search for <keyword> in search box
    Then <keyword> are displayed in search result
    And user is able to store the search result in CSV file first sorted by Price then Storage and finally Ratings of the device
    Then Close the flipkart website
    
    Examples:
 			| keyword             |
      | "Iphone under 40000"|
