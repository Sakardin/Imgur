@homepage
Feature: New Home Page

  Scenario: Open Home Page
    Given I open URL
    Then I see Home Page

  Scenario: Tag page open
    Given I see Home Page
    When I click on Explore tag  bar
    Then Right tag page is open

  Scenario: More Tags are displayed
    Given I see Home Page
    And  I see tags icons
    When I click "MORE TAGS +"
    Then I see more tags icons

  Scenario Outline: Sort order is changed(Most viral)
    Given I see Home Page
    And  I see "<target>"
    When I choose "<result>"
    Then Order of post has changed
    Examples:
            |target|result|
            |Newest|Popular|
            |Popular|Random|
            |Most Viral|User Submitted|

  Scenario: Waterfall and uniform page layout
    Given I see Home Page
    And Waterfall layout is active
    When I hover mouse over Unoform icon
    And I click
    Then I see layout has changed

  Scenario: I see Footer
    Given I see Home Page
    Then I see Footer

  Scenario: Searching tags
    Given I see Home Page
    When I search "#game"
    Then I see only tags

  Scenario: Searching users
    Given I see Home Page
    When I search "@game"
    Then I see only users

  Scenario: Globally searching
    Given I see Home Page
    When I search "game"
    Then I see posts, tags and user

  Scenario: Top Comments page
    Given I see Home Page
    When I click on Comments button
    Then I see Best Comments page

#  Scenario: Number on post thumbnail is same as number of images in the post
#    Given I see Home Page
#    And I see Post with number
#    When I open post
#    Then I see same number of images

  Scenario: Take me up - button is pul HP up
    Given I see Home Page
    When I scroll page
    And I click on take me up
    Then I on top of HP

  Scenario: Header minimaze when I scroll
    Given I see Home Page
    And I see big header
    When I scroll page
    Then Header is minimaze
  @wip
  Scenario Outline:  Autocomplete suggests the search request
    Given I see Home Page
     When I search "<target>"
     Then I see "<result>" in search suggeasteed string
    Examples:
        |target|result|
        |C     |c     |
        |Ca    |ca    |
        |Car   |car   |


