Feature: Assignment 2

  # Have a test deck for each scenario instead of overwriting the deck
  Scenario: JP-Scenario
    Given the game is setup with rigged deck for "A1 scenario"
    When the game deals adventure cards to players
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

    Given the game creates quest for current event card
    And Player 1 declines to sponsor and Player 2 accepts to sponsor quest
    When Player builds the quest stages for "A1 scenario"
    Then Stage 1 is setup with:
      | F5, H10 |
    And Stage 2 is setup with:
      | F15, S10 |
    And Stage 3 is setup with:
      | F15, D5, B15 |
    And Stage 4 is setup with:
      | F40, B15 |
