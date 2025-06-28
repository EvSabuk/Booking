Feature: Search hotel
  Scenario: Looking for 'Akra Kemer'
    Given booking search page is opened
    When user searches "Akra Kemer"
    Then "Akra Kemer - Ultra All Inclusive" hotel is shown
    And hotel "Akra Kemer - Ultra All Inclusive" rate is "9.2"

  Scenario Outline: Looking for hotels
    Given booking search page is opened
    When user searches "<hotel>"
    Then "<expectedResult>" hotel is shown
    Examples:
    | hotel | expectedResult |
    | Akra Kemer | Akra Kemer - Ultra All Inclusive |
    | Akra Kemer | Akra Kemer - Ultra All Inclusive |
    | Akra Kemer | Akra Kemer - Ultra All Inclusive |