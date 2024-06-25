Feature: Negative Validation for Luma E-commerce Platform

  @Sanity
  Scenario Outline: To Validate Negative scenario
    Given user launches the Luma Webapplication in browser
    Given user enters the <username> and <password> to the webpage
    When user hits on submit button
    Then user gets the error message printed on the screen

    Examples: 
      | username               | password    |
      | rauskinBomnd@gmail.com | Tushar1234  |
      | JamesBond@gmail.com    | Shraiya1234 |
