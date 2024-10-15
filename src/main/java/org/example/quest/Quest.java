package org.example.quest;

import java.util.List;
import java.util.ArrayList;

import org.example.Display;
import org.example.Player;
import org.example.cards.AdventureCard;
import org.example.decks.AdventureDeck;
import org.example.enums.adventure.FoeType;
import org.example.enums.adventure.WeaponType;

public class Quest {
  public static final int QUIT = -1;

  private int numStages;
  private List<Stage> stages;
  private Stage currentStage;
  private boolean isActive;
  private List<Participant> participants;
  private Participant currentParticipant;
  private Player sponsor;
  private int sponsorNumCardsUsed;

  public Quest(int numStages) {
    this.isActive = true;
    this.numStages = numStages;
    this.stages = new ArrayList<>();
    for (int i = 0; i < numStages; i++) {
      this.stages.add(new Stage());
    }
    this.currentStage = stages.get(0);
    this.participants = new ArrayList<>();
    this.currentParticipant = null;
    this.sponsor = null;
  }

  public void findSponsor(List<Player> players, Player currentPlayer, Display display) {
    int startIndex = players.indexOf(currentPlayer);
    int playerCount = players.size();

    display.print("\nFinding sponsor for quest...");

    for (int i = 0; i < playerCount; i++) {
      int index = (startIndex + i) % playerCount;
      Player player = players.get(index);

      boolean sponsorFound = display.promptForSponsor(player, currentPlayer, this.numStages);
      if (sponsorFound) {
        this.sponsor = player;
        display.printSponsorFound(player);
        break;
      } else {
        display.printSponsorDeclined(player);
      }
      if (i < playerCount - 1) display.promptNextPlayer();
      else display.promptReturnToOriginalPlayer();

      display.clear();
    }

    if (sponsor == null) display.printSponsorNotFound();
  }

  public void startAttack(Display display, List<Player> players, AdventureDeck adventureDeck) {
    display.print("\nATTACK PHASE...");
    addAllPlayersExceptSponsorToParticipants(players);
    for (int i = 0; i < numStages; i++) {
      if (participants.size() == 0) break;
      display.print(String.format("STAGE: %d", i + 1));
      display.printParticipants(participants);
      participants = display.promptForParticipants(participants);
      for (Participant participant : participants) {
        participant.drawCard(adventureDeck, display);
        setupAttack(i + 1, participant, display);
        display.promptNextPlayer();
        display.clear();
      }
    }
  }

  public void setupAttack(int stageNum, Participant participant, Display display) {
    display.printAttackSetup(stageNum, participant);
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

  public Participant getCurrentParticipant() {
    return currentParticipant;
  }

  public Player getSponsor() {
    return sponsor;
  }

  public void setSponsor(Player sponsor) {
    this.sponsor = sponsor;
  }

  public void setParticipants(List<Player> players) {
    for (Player player : players) {
      participants.add(new Participant(player));
    }
  }

  public int getSponsorNumCardsUsed() {
    return sponsorNumCardsUsed;
  }
}
