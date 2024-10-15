package org.example.quest;

import java.util.List;
import java.util.ArrayList;

import org.example.Display;
import org.example.Player;
import org.example.cards.AdventureCard;
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


  public void setup(Display display) {
    for (int i = 0; i < numStages; i++) {
      Stage stage = stages.get(i);
      display.printStageSetup(i + 1, sponsor.getHand());
      while (true) {
        if (stage.getCards().size() != 0) {
          display.printHand(sponsor.getHand());
        }
        int cardIndex = display.promptForCardIndexWithQuit(sponsor.getHand().size(), true);
        boolean validCard = validateStageSetupCard(stage, sponsor.getHand().get(cardIndex), display);
        if (validCard) {
          addCardToStage(stage, sponsor.getHand().get(cardIndex), display);
        }
        display.printStageSetupRules();
      }
    }
  }

  public boolean validateStageSetupQuit(Stage stage, Display display) {
    return false;
  }

  public boolean validateStageSetupCard(Stage stage, AdventureCard card, Display display) {
    if (card.getType() instanceof FoeType && stage.hasFoe()) {
      display.print("Only one Foe card is allowed per stage.");
      return false;
    }
    if (card.getType() instanceof WeaponType && stage.hasWeapon(card)) {
      display.print("Duplicate Weapon cards are not allowed in a stage.");
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

  public int getSponsorNumCardsUsed() {
    return sponsorNumCardsUsed;
  }
}
