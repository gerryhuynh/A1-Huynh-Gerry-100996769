Feature: Assignment 2

  Scenario: A1_scenario

    # Game and Quest Setup

    Given the game is setup with rigged deck for "A1 scenario"
    And the game deals adventure cards to players

    When the game starts turn

    Then a "Q4" event card is drawn

    And Player 1's hand is:
      | F5, F5, F15, F15, D5, S10, S10, H10, H10, B15, B15, L20 |
    And Player 2's hand is:
      | F5, F5, F15, F15, F40, D5, S10, H10, H10, B15, B15, E30 |
    And Player 3's hand is:
      | F5, F5, F5, F15, D5, S10, S10, S10, H10, H10, B15, L20 |
    And Player 4's hand is:
      | F5, F15, F15, F40, D5, D5, S10, H10, H10, B15, L20, E30 |

    And the game creates quest for current event card
    And Player 1 declines to sponsor quest
    And quest gets next potential sponsor
    And Player 2 accepts to sponsor quest

    And Player 2 builds the quest stages for "A1 scenario"
    And stage 1 is setup with:
      | F5, H10 |
    And stage 2 is setup with:
      | F15, S10 |
    And stage 3 is setup with:
      | F15, D5, B15 |
    And stage 4 is setup with:
      | F40, B15 |

    # Stage 1 Attacks

    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 1 sets up attack for "A1 scenario" on stage 1
    And participant's stage attack is:
      | D5, S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "A1 scenario" on stage 1
    And participant's stage attack is:
      | S10, D5 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "A1 scenario" on stage 1
    And participant's stage attack is:
      | D5, H10 |

    And the quest resolves attacks on stage
    And Player 1's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Stage 2 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 1 sets up attack for "A1 scenario" on stage 2
    And participant's stage attack is:
      | H10, S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "A1 scenario" on stage 2
    And participant's stage attack is:
      | B15, S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "A1 scenario" on stage 2
    And participant's stage attack is:
      | H10, B15 |

    And the quest resolves attacks on stage
    And Player 1's attack fails and is removed from quest
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Stage 3 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 3 sets up attack for "A1 scenario" on stage 3
    And participant's stage attack is:
      | L20, H10, S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "A1 scenario" on stage 3
    And participant's stage attack is:
      | B15, S10, L20 |

    And the quest resolves attacks on stage
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Stage 4 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 3 sets up attack for "A1 scenario" on stage 4
    And participant's stage attack is:
      | B15, H10, L20 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "A1 scenario" on stage 4
    And participant's stage attack is:
      | D5, S10, L20, E30 |

    And the quest resolves attacks on stage
    And Player 3's attack fails and is removed from quest
    And Player 4's attack succeeds and remains a participant

    # Quest Resolution

    And next stage starts
    And there are no more stages
    And Player 1's hand is:
      | F5, F10, F15, F15, F30, H10, B15, B15, L20 |
    And Player 3's hand is:
      | F5, F5, F15, F30, S10 |
    And Player 4's hand is:
      | F15, F15, F40, L20 |

    And shields are rewarded
    And Player 1 has 0 shields
    And Player 2 has 0 shields
    And Player 3 has 0 shields
    And Player 4 has 4 shields

    And Player 1's hand has 9 cards
    And Player 2's hand has 3 cards
    And Player 3's hand has 5 cards
    And Player 4's hand has 4 cards

    And replenishing sponsor's hand for "A1 scenario"
    And Player 2's hand has 12 cards

    And checking for winners
    And there are no winners

  Scenario: 2winner_game_2winner_quest

    # Game and Quest Setup

    Given the game is setup with rigged deck for "2winner_game_2winner_quest"
    And the game deals adventure cards to players
    When the game starts turn

    # Quest 1

    Then a "Q4" event card is drawn

    And Player 1's hand is:
      | F10, F15, F20, F25, F30, F40, F50, S10, S10, S10, H10, B15 |
    And Player 2's hand is:
      | F5, F5, D5, S10, S10, H10, H10, B15, B15, L20, L20, E30 |
    And Player 3's hand is:
      | F5, F5, F5, F10, F15, F25, F25, F30, D5, S10, H10, H10 |
    And Player 4's hand is:
      | F15, F30, D5, D5, S10, S10, S10, H10, B15, L20, L20, E30 |

    And the game creates quest for current event card
    And Player 1 accepts to sponsor quest
    And Player 1 builds the quest stages for "2winner_game_2winner_quest-q1"
    And stage 1 is setup with:
      | F10 |
    And stage 2 is setup with:
      | F15 |
    And stage 3 is setup with:
      | F20 |
    And stage 4 is setup with:
      | F25 |

    # Quest 1 Stage 1 Attacks

    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 1
    And participant's stage attack is:
      | B15 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "2winner_game_2winner_quest-q1" on stage 1
    And participant's stage attack is:
      | D5 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 1
    And participant's stage attack is:
      | B15 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack fails and is removed from quest
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 2 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 2
    And participant's stage attack is:
      | L20 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 2
    And participant's stage attack is:
      | L20 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 3 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 3
    And participant's stage attack is:
      | B15, H10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 3
    And participant's stage attack is:
      | S10, H10, D5 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 4 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q1" on stage 4
    And participant's stage attack is:
      | L20, S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q1" on stage 4
    And participant's stage attack is:
      | E30 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Resolution

    And next stage starts
    And there are no more stages

    And shields are rewarded
    And Player 1 has 0 shields
    And Player 2 has 4 shields
    And Player 3 has 0 shields
    And Player 4 has 4 shields

    And replenishing sponsor's hand for "2winner_game_2winner_quest-q1"
    And Player 1's hand has 12 cards

    # Quest 2

    And next player's turn
    And the game starts turn
    And a "Q3" event card is drawn

    And the game creates quest for current event card
    And Player 2 declines to sponsor quest
    And quest gets next potential sponsor
    And Player 3 accepts to sponsor quest
    And Player 3 builds the quest stages for "2winner_game_2winner_quest-q2"
    And stage 1 is setup with:
      | F5 |
    And stage 2 is setup with:
      | F10 |
    And stage 3 is setup with:
      | F15 |

    # Quest 2 Stage 1 Attacks

    And "all except first" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q2" on stage 1
    And participant's stage attack is:
      | S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q2" on stage 1
    And participant's stage attack is:
      | S10 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 2 Stage 2 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q2" on stage 2
    And participant's stage attack is:
      | H10, D5 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q2" on stage 2
    And participant's stage attack is:
      | D5, S10 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 2 Stage 3 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "2winner_game_2winner_quest-q2" on stage 3
    And participant's stage attack is:
      | E30 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "2winner_game_2winner_quest-q2" on stage 3
    And participant's stage attack is:
      | L20 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 2 Resolution

    And next stage starts
    And there are no more stages

    And shields are rewarded
    And Player 1 has 0 shields
    And Player 2 has 7 shields
    And Player 3 has 0 shields
    And Player 4 has 7 shields

    And replenishing sponsor's hand for "2winner_game_2winner_quest-q2"
    And Player 3's hand has 12 cards

    And checking for winners
    And Player 2 is a winner
    And Player 4 is a winner

  Scenario: 1winner_game_with_events

    # Game and Quest Setup

    Given the game is setup with rigged deck for "1winner_game_with_events"
    And the game deals adventure cards to players
    When the game starts turn

    # Quest 1

    Then a "Q4" event card is drawn

    And Player 1's hand is:
      | F5, F5, F5, F5, F5, F5, F10, F10, F15, F15, F15, F20 |
    And Player 2's hand is:
      | F5, D5, S10, S10, S10, S10, S10, H10, H10, H10, B15, B15 |
    And Player 3's hand is:
      | F5, D5, S10, S10, S10, S10, S10, H10, H10, H10, B15, B15 |
    And Player 4's hand is:
      | F10, D5, S10, S10, S10, S10, H10, H10, H10, B15, B15, B15 |

    And the game creates quest for current event card
    And Player 1 accepts to sponsor quest
    And Player 1 builds the quest stages for "1winner_game_with_events-q1"
    And stage 1 is setup with:
      | F5 |
    And stage 2 is setup with:
      | F10 |
    And stage 3 is setup with:
      | F15 |
    And stage 4 is setup with:
      | F20 |

    # Quest 1 Stage 1 Attacks

    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q1" on stage 1
    And participant's stage attack is:
      | S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q1" on stage 1
    And participant's stage attack is:
      | S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "1winner_game_with_events-q1" on stage 1
    And participant's stage attack is:
      | S10 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 2 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q1" on stage 2
    And participant's stage attack is:
      | B15 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q1" on stage 2
    And participant's stage attack is:
      | B15 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "1winner_game_with_events-q1" on stage 2
    And participant's stage attack is:
      | B15 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 3 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q1" on stage 3
    And participant's stage attack is:
      | S10, H10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q1" on stage 3
    And participant's stage attack is:
      | S10, H10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "1winner_game_with_events-q1" on stage 3
    And participant's stage attack is:
      | S10, H10 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Stage 4 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q1" on stage 4
    And participant's stage attack is:
      | S10, H10, D5 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q1" on stage 4
    And participant's stage attack is:
      | S10, H10, D5 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "1winner_game_with_events-q1" on stage 4
    And participant's stage attack is:
      | S10, H10, D5 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack succeeds and remains a participant

    # Quest 1 Resolution

    And next stage starts
    And there are no more stages

    And shields are rewarded
    And Player 1 has 0 shields
    And Player 2 has 4 shields
    And Player 3 has 4 shields
    And Player 4 has 4 shields

    And replenishing sponsor's hand for "1winner_game_with_events-q1"
    And Player 1's hand has 12 cards

    # P2 Plague

    And next player's turn
    And the game starts turn
    And a "Plague" event card is drawn
    And Player 2 loses 2 shields
    And Player 2 has 2 shields

    # P3 Prosperity

    And next player's turn
    And the game starts turn
    And a "Prosperity" event card is drawn
    And all players draw 2 adventure cards for "1winner_game_with_events"
    And Player 1's hand has 12 cards
    And Player 2's hand has 10 cards
    And Player 3's hand has 10 cards
    And Player 4's hand has 10 cards

    # P4 Queens Favor

    And next player's turn
    And the game starts turn
    And a "Queen's Favor" event card is drawn
    And Player 4 draws 2 adventure cards for "1winner_game_with_events"
    And Player 4's hand has 12 cards

    # Quest 2

    And next player's turn
    And the game starts turn
    And a "Q3" event card is drawn

    And the game creates quest for current event card
    And Player 1 accepts to sponsor quest
    And Player 1 builds the quest stages for "1winner_game_with_events-q2"
    And stage 1 is setup with:
      | F5 |
    And stage 2 is setup with:
      | F10 |
    And stage 3 is setup with:
      | F15 |

    # Quest 2 Stage 1 Attacks

    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q2" on stage 1
    And participant's stage attack is:
      | S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q2" on stage 1
    And participant's stage attack is:
      | S10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "1winner_game_with_events-q2" on stage 1
    And participant's stage attack is:
      | |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant
    And Player 4's attack fails and is removed from quest

    # Quest 2 Stage 2 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q2" on stage 2
    And participant's stage attack is:
      | B15 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q2" on stage 2
    And participant's stage attack is:
      | B15 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant

    # Quest 2 Stage 3 Attacks

    And next stage starts
    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "1winner_game_with_events-q2" on stage 3
    And participant's stage attack is:
      | S10, H10 |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "1winner_game_with_events-q2" on stage 3
    And participant's stage attack is:
      | S10, H10 |

    And the quest resolves attacks on stage
    And Player 2's attack succeeds and remains a participant
    And Player 3's attack succeeds and remains a participant

    # Quest 2 Resolution

    And next stage starts
    And there are no more stages

    And shields are rewarded
    And Player 1 has 0 shields
    And Player 2 has 5 shields
    And Player 3 has 7 shields
    And Player 4 has 4 shields

    And replenishing sponsor's hand for "1winner_game_with_events-q2"
    And Player 1's hand has 12 cards

    And checking for winners
    And Player 3 is a winner

  Scenario: 0_winner_quest

    # Game and Quest Setup

    Given the game is setup with rigged deck for "0_winner_quest"
    And the game deals adventure cards to players
    When the game starts turn

    Then a "Q2" event card is drawn

    And Player 1's hand has 12 cards
    And Player 1's hand is:
      | F5, F10, S10, S10, S10, S10, S10, S10, S10, S10, S10, S10 |

    And the game creates quest for current event card
    And Player 1 accepts to sponsor quest
    And Player 1 builds the quest stages for "0_winner_quest"
    And stage 1 is setup with:
      | F5 |
    And stage 2 is setup with:
      | F10 |

    # Quest 1 Stage 1 Attacks

    And "all" participants participate in quest
    And participant draws an adventure card
    And Player 2 sets up attack for "0_winner_quest" on stage 1
    And participant's stage attack is:
      | |

    And next participant's turn
    And participant draws an adventure card
    And Player 3 sets up attack for "0_winner_quest" on stage 1
    And participant's stage attack is:
      | |

    And next participant's turn
    And participant draws an adventure card
    And Player 4 sets up attack for "0_winner_quest" on stage 1
    And participant's stage attack is:
      | |

    And the quest resolves attacks on stage
    And Player 2's attack fails and is removed from quest
    And Player 3's attack fails and is removed from quest
    And Player 4's attack fails and is removed from quest

    # Quest 1 Resolution

    And next stage starts
    And there are no more participants

    And Player 1 has 0 shields
    And Player 2 has 0 shields
    And Player 3 has 0 shields
    And Player 4 has 0 shields

    And Player 1's hand has 10 cards
    And Player 2's hand has 12 cards
    And Player 3's hand has 12 cards
    And Player 4's hand has 12 cards

    And replenishing sponsor's hand for "0_winner_quest"
    And Player 1's hand has 12 cards

    And checking for winners
    And there are no winners
