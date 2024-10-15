package org.example.quest;

import java.util.List;
import java.util.ArrayList;

import org.example.Display;
import org.example.Player;

public class Quest {
  private int numStages;
  private List<Stage> stages;
  private Stage currentStage;
  private boolean isActive;
  private List<Participant> participants;
  private Participant currentParticipant;
  private Player sponsor;

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
}
