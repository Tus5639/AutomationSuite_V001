Feature: Login to Luma E-Commerce WebApplication
  I want to login to Luma Ecommerce WebApplication

  @Sanity
  Scenario: Login to Web Application
    Given user launches the Luma Webapplication in browser
    When user enters the <username> and <password> to the webpage
    Then user is able login to webpApplication

    Examples: 
      | username  | password | 
      | tushar.0082r@gmail.com | Tushar1234 | 
      | sh5639@gmail.com | Shraiya1234 |
