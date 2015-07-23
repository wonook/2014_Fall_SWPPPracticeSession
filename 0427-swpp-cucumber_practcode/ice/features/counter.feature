Feature: Counter accumulates

  @javascript
  Scenario: Counter goes up
    Given I logged in as "username" with "password" "10" times
    And I am on my homepage
    When I login as "username" with "password"
    Then I see my counter at "11"
