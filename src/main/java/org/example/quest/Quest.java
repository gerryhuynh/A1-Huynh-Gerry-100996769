package org.example.quest;

import java.util.ArrayList;
import java.util.List;

import org.example.Player;

public class Quest {
  private boolean isActive;
  private int numStages;
  private List<Stage> stages;
  private Stage currentStage;
  private Player sponsor;
  private List<Participant> participants;
  private Participant currentParticipant;

  public Quest(int numStages) {
    this.isActive = true;
    this.numStages = numStages;
    this.stages = new ArrayList<>();
    for (int i = 0; i < numStages; i++) {
      stages.add(new Stage());
    }
    this.currentStage = stages.get(0);
    this.sponsor = null;
    this.participants = new ArrayList<>();
    this.currentParticipant = null;
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

  public void addParticipant(Participant participant) {
    participants.add(participant);
  }

  public List<Participant> getParticipants() {
    return participants;
  }

  public void setCurrentParticipant(Participant participant) {
    currentParticipant = participant;
  }

  public Participant getCurrentParticipant() {
    return currentParticipant;
  }

  public void nextParticipant() {
    int currentIndex = participants.indexOf(currentParticipant);
    currentParticipant = participants.get((currentIndex + 1) % participants.size());
  }

  public void nextStage() {
    int currentIndex = stages.indexOf(currentStage);
    currentStage = stages.get((currentIndex + 1) % stages.size());
  }

  public Player getSponsor() {
    return sponsor;
  }

  public void setSponsor(Player sponsor) {
    this.sponsor = sponsor;
  }
}
