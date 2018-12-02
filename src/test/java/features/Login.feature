@login
  Feature: Signing and login

    Scenario: Navigate to sign in page
      Given I on Home Page
      When I click Sign in
      Then Sign in Page is opened

    Scenario: Sing in with  incorrect credentials
      Given I on Sign in page
      When I enter "username" in username field
      And I enter "password" in password field
      And Click Sign in button
      Then I see error message

    Scenario: Sign in with correct credentials
      Given I on Sign in page
    When I enter "sakardin@tut.by" in username field
      And I enter "qazwsx123" in password field
      And Click Sign in button
      Then I on Home Page
      And I logged in
