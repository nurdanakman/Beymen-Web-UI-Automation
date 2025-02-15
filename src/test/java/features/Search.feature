Feature:  Search Behavior

Background:
    Given User opens home page

Scenario: Viewing filtered product list after searching
    When User enters the search text 1 row and 1 column
    Then User should see the filtered product list for 1 row and 1 column text

Scenario: Viewing unfiltered product list after clearing search text
    When User enters the search text 1 row and 1 column
    And User clears the search text
    Then User shouldn't see the filtered product list for 1 row and 1 column text
