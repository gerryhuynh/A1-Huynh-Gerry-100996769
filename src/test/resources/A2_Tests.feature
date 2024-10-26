Feature: Assignment 2

  Scenario: JP-Scenario

    # Game and Quest Setup

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

    Given the game starts turn
    Then a "Q4" event card is drawn

    Given the game creates quest for current event card
    And Player 1 declines to sponsor and Player 2 accepts to sponsor quest
    When Player builds the quest stages for "A1 scenario"
    Then stage 1 is setup with:
      | F5, H10 |
    And stage 2 is setup with:
      | F15, S10 |
    And stage 3 is setup with:
      | F15, D5, B15 |
    And stage 4 is setup with:
      | F40, B15 |

    # Stage 1 Attacks

    Given all participants participate in quest
    And participant draws an adventure card
    When Player 1 sets up attack for "A1 scenario" on stage 1
    Then participant's stage attack is:
      | D5, S10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 3 sets up attack for "A1 scenario" on stage 1
    Then participant's stage attack is:
      | S10, D5 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "A1 scenario" on stage 1
    Then participant's stage attack is:
      | D5, H10 |

    Given the quest resolves attacks on stage
    Then Player 1's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Stage 2 Attacks

    Given next stage starts
    And all participants participate in quest
    And participant draws an adventure card
    When Player 1 sets up attack for "A1 scenario" on stage 2
    Then participant's stage attack is:
      | H10, S10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 3 sets up attack for "A1 scenario" on stage 2
    Then participant's stage attack is:
      | B15, S10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "A1 scenario" on stage 2
    Then participant's stage attack is:
      | H10, B15 |

    Given the quest resolves attacks on stage
    Then Player 1's attack fails and is removed from quest
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Stage 3 Attacks

    Given next stage starts
    And all participants participate in quest
    And participant draws an adventure card
    When Player 3 sets up attack for "A1 scenario" on stage 3
    Then participant's stage attack is:
      | L20, H10, S10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "A1 scenario" on stage 3
    Then participant's stage attack is:
      | B15, S10, L20 |

    Given the quest resolves attacks on stage
    Then Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant
