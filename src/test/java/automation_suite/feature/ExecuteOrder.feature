Feature: I want to Execute Order from Luma Ecommerce WebApplication
 I want to execute a purchase Order from Luma Ecommerce Platform

@Sanity
  Scenario Outline: I want to Execute a Purchase Order
    Given user launches the Ecommerce webpage
    When user enters the credentials for <username> and <password>
    When user navigates to Homepage
    When user navigates to <Gender> category tab to search for Products
    And user clicks to <productCat   egory> and <product> 
    Then user enters the required <Size> and <Color> for the product and adds the product to cart
    Then user confirms and confirms the order on checkout page
    Then user gets the Order confirmation page
    
 

    Examples: 
      | username  						 | password 		| product               				|productCategory      |Gender|Size|Color|
      | tushar.0082r@gmail.com | Tushar1234   | Hollister Backyard Sweatshirt |Hoodies & Sweatshirts|Men   |M   |Green|
      | sh5639@gmail.com       | Shraiya1234  | Montana Wind Jacket           |Jackets              |Men   |XS  |Green|
