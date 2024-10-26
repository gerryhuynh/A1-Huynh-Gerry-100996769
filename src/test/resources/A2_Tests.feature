Feature: Assignment 2

  # Have a test deck for each scenario instead of overwriting the deck
  Scenario: JP-Scenario
    Given the game is setup with rigged deck for "A1 scenario"
    When the game deals adventure cards
    Then Player 1's starting hand is:
      | F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20 |
    And Player 2's starting hand is:
      | F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30 |
    And Player 3's starting hand is:
      | F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, L20 |
    And Player 4's starting hand is:
      | F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30 |

    Given the game draws an event card
    Then a "Q4" event card is drawn
