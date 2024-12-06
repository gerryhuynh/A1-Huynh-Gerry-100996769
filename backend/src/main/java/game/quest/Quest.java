package game.quest;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import game.Display;
import game.Player;
import game.cards.AdventureCard;
import game.decks.AdventureDeck;
import game.enums.adventure.FoeType;
import game.enums.adventure.WeaponType;

public class Quest {
  public static final int QUIT = -1;

  private int numStages;
  private List<Stage> stages;
  private Stage currentStage;
  private boolean isActive;
  private List<Participant> participants;
  private Participant currentParticipant;
  private Player sponsor;
  private Player currentPotentialSponsor;
  private int sponsorNumCardsUsed;

  public Quest(int numStages, Player currentPlayer) {
    this.isActive = true;
    this.numStages = numStages;
    this.stages = new ArrayList<>();
    for (int i = 0; i < numStages; i++) {
      this.stages.add(new Stage(i + 1));
    }
    this.currentStage = stages.get(0);
    this.participants = new ArrayList<>();
    this.currentParticipant = null;
    this.sponsor = null;
    this.currentPotentialSponsor = currentPlayer;
  }

  public void findSponsor(List<Player> players, Player currentPlayer, Display display) {
    display.print("\nFinding sponsor for quest...");

    while (currentPotentialSponsor != null && sponsor == null) {
      promptForSponsor(currentPlayer, display);

      if (sponsor == null) {
        getNextPotentialSponsor(players, currentPlayer);
        if (currentPotentialSponsor != null) display.promptNextPlayer();
        else display.promptReturnToOriginalPlayer();
        display.clear();
      }
    }

    if (sponsor == null) display.printSponsorNotFound();
  }

  public void promptForSponsor(Player currentPlayer, Display display) {
    boolean sponsorFound = display.promptForSponsor(currentPotentialSponsor, currentPlayer, this.numStages);

    if (sponsorFound) {
      this.sponsor = currentPotentialSponsor;
      display.printSponsorFound(currentPotentialSponsor);
      currentPotentialSponsor = null;
    } else {
      display.printSponsorDeclined(currentPotentialSponsor);
    }
  }

  public void getNextPotentialSponsor(List<Player> players, Player currentPlayer) {
    int currentIndex = players.indexOf(currentPotentialSponsor);
    int nextIndex = (currentIndex + 1) % players.size();

    currentPotentialSponsor = (nextIndex == players.indexOf(currentPlayer)) ? null : players.get(nextIndex);
  }

  public void startAttack(Display display, List<Player> players, AdventureDeck adventureDeck) {
    display.print("\nATTACK PHASE...");
    addAllPlayersExceptSponsorToParticipants(players);

    while (currentStage != null && !participants.isEmpty()) {
      display.print(String.format("STAGE: %d", currentStage.getStageNumber()));
      promptForParticipants(display);

      while (currentParticipant != null) {
        currentParticipant.drawCard(adventureDeck, display);
        setupAttack(display);
        getNextParticipant();
      }

      resolveAttacks(display);
      getNextStage();
    }

    rewardShields(display);
    replenishSponsorHand(display, adventureDeck);
  }

  public void promptForParticipants(Display display) {
    display.printParticipants(participants);
    participants = display.promptForParticipants(participants);
    currentParticipant = participants.isEmpty() ? null : participants.get(0);
  }

  public void getNextParticipant() {
    int currentIndex = participants.indexOf(currentParticipant);
    if (currentIndex < participants.size() - 1) {
      currentParticipant = participants.get(currentIndex + 1);
    } else {
      currentParticipant = null;
    }
  }

  public void getNextStage() {
    int currentIndex = stages.indexOf(currentStage);
    if (currentIndex < stages.size() - 1) {
      currentStage = stages.get(currentIndex + 1);
    } else {
      currentStage = null;
    }
  }

  public void replenishSponsorHand(Display display, AdventureDeck adventureDeck) {
    int numCardsToDraw = sponsorNumCardsUsed + numStages;
    display.print(String.format("\nREPLENISHING SPONSOR %s HANDS... (%d cards)", sponsor.getName(), numCardsToDraw));
    sponsor.addToHand(adventureDeck.draw(numCardsToDraw), display);
  }

  public void rewardShields(Display display) {
    display.print("\nQUEST COMPLETE!");
    if (participants.size() > 0) {
      display.print(String.format("REWARDING %d SHIELDS TO PARTICIPANTS...", numStages));
      for (Participant participant : participants) {
        int originalShields = participant.getPlayer().getShields();
        participant.getPlayer().setShields(participant.getPlayer().getShields() + numStages);
        display.print(String.format("\n%s's shields: %d -> %d", participant.getPlayer().getName(), originalShields, participant.getPlayer().getShields()));
      }
    } else {
      display.print("No participants were successful in defeating the quest's stages.");
    }
    display.promptReturnToSponsor();
  }

  public void resolveAttacks(Display display) {
    display.print(String.format("\nRESOLVING STAGE %d ATTACKS...", currentStage.getStageNumber()));
    int stageValue = currentStage.getValue();
    int participantAttackValue = 0;
    List<Participant> activeParticipants = new ArrayList<>();

    for (Participant participant : participants) {
      display.print(String.format("\nPARTICIPANT: %s", participant.getPlayer().getName()));

      participantAttackValue = participant.getAttackValue();
      display.print(String.format("\nSponsor's Defense: %s. Value: %d", currentStage.getCards().toString(), stageValue));
      display.print(String.format("Your Attack: %s. Value: %d", participant.getAttackCards().toString(), participantAttackValue));

      if (participantAttackValue >= stageValue) {
        display.print(String.format("\nYour attack is successful!"));
        display.print(String.format("You will continue to the next stage."));
        activeParticipants.add(participant);
      } else {
        display.print(String.format("\nYour attack is unsuccessful."));
        display.print(String.format("You are no longer eligible to continue the quest."));
      }
      participant.clearAttackCards();
      display.promptNextPlayer();
      display.clear();
    }

    participants = activeParticipants;
  }

  public void setupAttack(Display display) {
    boolean validCard = false;
    int stageNumber = currentStage.getStageNumber();

    display.printAttackSetup(stageNumber, currentParticipant);

    while (true) {
      if (currentParticipant.getAttackCards().size() != 0) {
        display.printHand(currentParticipant.getPlayer().getHand());
      }

      int cardIndex = display.promptForCardIndexWithQuit(currentParticipant.getPlayer().getHand().size(), true);
      if (cardIndex == QUIT) {
        display.promptNextParticipantAttack(stageNumber, currentParticipant);
        break;
      }

      AdventureCard card = currentParticipant.getPlayer().getHand().get(cardIndex);
      validCard = validateAttackCard(currentParticipant.getAttackCards(), card, display);
      if (validCard) {
        currentParticipant.addCardToAttack(card, display);
      }
    }
  }

  public boolean validateAttackCard(List<AdventureCard> attackCards, AdventureCard card, Display display) {
    if (card.getType() instanceof WeaponType) {
      boolean hasDuplicateWeapon = attackCards.stream()
        .filter(c -> c.getType() instanceof WeaponType)
        .anyMatch(c -> c.getType().equals(card.getType()));

      if (hasDuplicateWeapon) {
        display.print("Duplicate Weapon cards are not allowed in a stage.");
        display.printAttackSetupRules();
        return false;
      }
    }
    if (card.getType() instanceof FoeType) {
      display.print("Foe cards are not allowed in an attack.");
      display.printAttackSetupRules();
      return false;
    }
    return true;
  }

  public void addAllPlayersExceptSponsorToParticipants(List<Player> players) {
    for (Player player : players) {
      if (player != sponsor) {
        participants.add(new Participant(player));
      }
    }
  }

  public void setup(Display display) {
    for (int i = 0; i < numStages; i++) {
      boolean validQuit = false;
      boolean validCard = false;
      Stage stage = stages.get(i);
      display.printStageSetup(i + 1, sponsor.getHand());
      while (true) {
        if (stage.getCards().size() != 0) {
          display.printHand(sponsor.getHand());
        }
        int cardIndex = display.promptForCardIndexWithQuit(sponsor.getHand().size(), true);
        if (cardIndex == QUIT) {
          validQuit = validateStageSetupQuit(stage, display);
          if (validQuit) {
            break;
          }
        }
        if (cardIndex != QUIT) {
          validCard = validateStageSetupCard(stage, sponsor.getHand().get(cardIndex), display);
          if (validCard) {
            addCardToStage(stage, sponsor.getHand().get(cardIndex), display);
          }
        }
      }
      if (i == numStages - 1) {
        display.printQuestSetupComplete(this);
      }
    }
  }

  public boolean validateStageSetupQuit(Stage stage, Display display) {
    if (stage.getCards().size() == 0) {
      display.print("A stage cannot be empty.");
      return false;
    }
    if (stages.indexOf(stage) > 0) {
      Stage previousStage = stages.get(stages.indexOf(stage) - 1);
      if (previousStage.getValue() >= stage.getValue()) {
        display.print("Insufficient value for this stage.");
        return false;
      }
    }
    display.promptNextStageSetup(stages.indexOf(stage) + 1, stage);
    return true;
  }

  public boolean validateStageSetupCard(Stage stage, AdventureCard card, Display display) {
    if (card.getType() instanceof FoeType && stage.hasFoe()) {
      display.print("Only one Foe card is allowed per stage.");
      display.printStageSetupRules();
      return false;
    }
    if (card.getType() instanceof WeaponType && stage.hasWeapon(card)) {
      display.print("Duplicate Weapon cards are not allowed in a stage.");
      display.printStageSetupRules();
      return false;
    }
    return true;
  }

  public void addCardToStage(Stage stage, AdventureCard card, Display display) {
    stage.addCard(card);
    sponsor.getHand().remove(card);
    sponsorNumCardsUsed++;
    display.printCardAddedToStage(stage.getCards());
  }

  // Getters and Setters

  public int getNumStages() {
    return numStages;
  }

  public List<Stage> getStages() {
    return stages;
  }

  public Stage getCurrentStage() {
    return currentStage;
  }

  public boolean isActive() {
    return isActive;
  }

  public List<Participant> getParticipants() {
    return participants;
  }

  public List<Player> getParticipantsAsPlayers() {
    return participants.stream()
      .map(Participant::getPlayer)
      .collect(Collectors.toList());
  }

  public Participant getCurrentParticipant() {
    return currentParticipant;
  }

  public Player getCurrentPotentialSponsor() {
    return currentPotentialSponsor;
  }

  public Player getSponsor() {
    return sponsor;
  }

  public void setSponsor(Player sponsor) {
    this.sponsor = sponsor;
    this.currentPotentialSponsor = null;
  }

  public void setParticipants(List<Player> players) {
    for (Player player : players) {
      participants.add(new Participant(player));
    }
  }

  public int getSponsorNumCardsUsed() {
    return sponsorNumCardsUsed;
  }

  public void setSponsorNumCardsUsed(int sponsorNumCardsUsed) {
    this.sponsorNumCardsUsed = sponsorNumCardsUsed;
  }
}
