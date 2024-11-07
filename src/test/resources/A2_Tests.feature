Feature: Assignment 2

  Scenario: A1_scenario

    # Game and Quest Setup

    Given the game is setup with rigged deck for "A1 scenario"
    When the game deals adventure cards to players
    Then Player 1's hand is:
      | F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20 |
    And Player 2's hand is:
      | F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30 |
    And Player 3's hand is:
      | F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, L20 |
    And Player 4's hand is:
      | F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30 |

    Given the game starts turn
    Then a "Q4" event card is drawn

    Given the game creates quest for current event card
    And Player 1 declines to sponsor quest
    And quest gets next potential sponsor
    And Player 2 accepts to sponsor quest
    When Player 2 builds the quest stages for "A1 scenario"
    Then stage 1 is setup with:
      | F5, H10 |
    And stage 2 is setup with:
      | F15, S10 |
    And stage 3 is setup with:
      | F15, D5, B15 |
    And stage 4 is setup with:
      | F40, B15 |

    # Stage 1 Attacks

    Given "all" participants participate in quest
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
    And "all" participants participate in quest
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
    And "all" participants participate in quest
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

    # Stage 4 Attacks

    Given next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    When Player 3 sets up attack for "A1 scenario" on stage 4
    Then participant's stage attack is:
      | B15, H10, L20 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "A1 scenario" on stage 4
    Then participant's stage attack is:
      | D5, S10, L20, E30 |

    Given the quest resolves attacks on stage
    Then Player 3's attack fails and is removed from quest
    And Player 4's attack succeeds and remains a participant

    # Quest Resolution

    Given next stage starts
    Then there are no more stages
    And Player 1's hand is:
      | F5, F10, F15, F15, F30, H10, B15, B15, L20 |
    And Player 3's hand is:
      | F5, F5, F15, F30, S10 |
    And Player 4's hand is:
      | F15, F15, F40, L20 |

    When shields are rewarded
    Then Player 1 has 0 shields
    And Player 3 has 0 shields
    And Player 4 has 4 shields

    When replenishing sponsor's hand for "A1 scenario"
    Then Player 2's hand has 12 cards

  Scenario: 2winner_game_2winner_quest

    # Game and Quest Setup

    Given the game is setup with rigged deck for "2winner_game_2winner_quest"
    When the game deals adventure cards to players
    Then Player 1's hand is:
      | F10, F15, F20, F25, F30, F40, F50, S10, S10, S10, H10, B15 |
    And Player 2's hand is:
      | F5, F5, D5, S10, S10, H10, H10, B15, B15, L20, L20, E30 |
    And Player 3's hand is:
      | F5, F5, F5, F10, F15, F25, F25, F30, D5, S10, H10, H10 |
    And Player 4's hand is:
      | F15, F30, D5, D5, S10, S10, S10, H10, B15, L20, L20, E30 |

    # Quest 1

    Given the game starts turn
    Then a "Q4" event card is drawn

    Given the game creates quest for current event card
    And Player 1 accepts to sponsor quest
    When Player 1 builds the quest stages for "2winner_game_2winner_quest-q1"
    Then stage 1 is setup with:
      | F10 |
    And stage 2 is setup with:
      | F15 |
    And stage 3 is setup with:
      | F20 |
    And stage 4 is setup with:
      | F25 |

    # Quest 1 Stage 1 Attacks

    Given "all" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 1
    Then participant's stage attack is:
      | B15 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 3 sets up attack for "2winner_game_2winner_quest-q1" on stage 1
    Then participant's stage attack is:
      | D5 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 1
    Then participant's stage attack is:
      | B15 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 3's attack fails and is removed from quest
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 2 Attacks

    Given next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 2
    Then participant's stage attack is:
      | L20 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 2
    Then participant's stage attack is:
      | L20 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 3 Attacks

    Given next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 3
    Then participant's stage attack is:
      | B15, H10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 3
    Then participant's stage attack is:
      | S10, H10, D5 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 4 Attacks

    Given next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 4
    Then participant's stage attack is:
      | L20, S10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 4
    Then participant's stage attack is:
      | E30 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Resolution

    Given next stage starts
    Then there are no more stages

    When shields are rewarded
    Then Player 1 has 0 shields
    And Player 2 has 4 shields
    And Player 3 has 0 shields
    And Player 4 has 4 shields

    When replenishing sponsor's hand for "2winner_game_2winner_quest-q1"
    Then Player 1's hand has 12 cards

    # Quest 2

    Given next player's turn
    And the game starts turn
    Then a "Q3" event card is drawn

    Given the game creates quest for current event card
    And Player 2 declines to sponsor quest
    And quest gets next potential sponsor
    And Player 3 accepts to sponsor quest
    When Player 3 builds the quest stages for "2winner_game_2winner_quest-q2"
    Then stage 1 is setup with:
      | F5 |
    And stage 2 is setup with:
      | F10 |
    And stage 3 is setup with:
      | F15 |

    # Quest 2 Stage 1 Attacks

    Given "all except first" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q2" on stage 1
    Then participant's stage attack is:
      | S10 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q2" on stage 1
    Then participant's stage attack is:
      | S10 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 2 Stage 2 Attacks

    Given next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q2" on stage 2
    Then participant's stage attack is:
      | H10, D5 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q2" on stage 2
    Then participant's stage attack is:
      | D5, S10 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 2 Stage 3 Attacks

    Given next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    When Player 2 sets up attack for "2winner_game_2winner_quest-q2" on stage 3
    Then participant's stage attack is:
      | E30 |

    Given next participant's turn
    And participant draws an adventure card
    When Player 4 sets up attack for "2winner_game_2winner_quest-q2" on stage 3
    Then participant's stage attack is:
      | L20 |

    Given the quest resolves attacks on stage
    Then Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 2 Resolution

    Given next stage starts
    Then there are no more stages

    When shields are rewarded
    Then Player 1 has 0 shields
    And Player 2 has 7 shields
    And Player 3 has 0 shields
    And Player 4 has 7 shields

    When replenishing sponsor's hand for "2winner_game_2winner_quest-q2"
    Then Player 3's hand has 12 cards

    When checking for winners
    Then Player 2 is a winner
    And Player 4 is a winner
