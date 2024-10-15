package org.example.quest;

import java.util.List;
import java.util.ArrayList;
import org.example.Player;

public class Quest {
  public Quest(int numStages) {}

  public int getNumStages() {
    return 0;
  }

  public List<Stage> getStages() {
    List<Stage> stages = new ArrayList<>();
    stages.add(new Stage());
    return stages;
  }

  public Stage getCurrentStage() {
    return new Stage();
  }

  public boolean isActive() {
    return false;
  }

  public List<Participant> getParticipants() {
    return new ArrayList<>();
  }

  public Participant getCurrentParticipant() {
    return new Participant();
  }

  public Player getSponsor() {
    return new Player("");
  }
}
