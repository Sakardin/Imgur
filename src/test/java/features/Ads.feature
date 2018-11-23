Feature: Different conditions require different ads

#  There is several options when user see/don't see ads in post
#
#  1.
  Scenario: User logged out Adblock is off
    Given I see post
    And I logged out
    When Adblock is off
    Then I see Top ads
    And I see middle ads
    And I see bottom ads

#  2.
  Scenario: User logged in AdBlock is off
    Given I see post
    And I logged in
    When AdBlock in off
    Then I see Top ads
    And I see middle ads
    And I see bottom ads