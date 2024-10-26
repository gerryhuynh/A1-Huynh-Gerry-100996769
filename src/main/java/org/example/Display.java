package org.example;

import org.example.cards.AdventureCard;
import org.example.cards.EventCard;
import org.example.quest.Participant;
import org.example.quest.Quest;
import org.example.quest.Stage;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
public class Display {
  public static final String CLEAR_SCREEN_COMMAND = "\n".repeat(50);
  private PrintWriter output;
  private Scanner input;

  public Display(PrintWriter output) {
    this.output = output;
    this.input = new Scanner(System.in);
  }

  public void print(String message) {
    output.println(message);
    output.flush();
  }

  public void printCurrentPlayer(Player player) {
    print(String.format("\nCURRENT PLAYER: %s", player.getName()));
  }

  public void printDrawnEventCard(EventCard card) {
    print("\nDRAWN EVENT CARD:");
    print(card.toString());
  }

  public void printEventCardEffect(EventCard card) {
    print(String.format("\n%s CARD EFFECT:", card.toString().toUpperCase()));
    print(card.getType().getEffectDesc());
  }

  public int promptForCardToDiscard(List<AdventureCard> hand) {
    print("\nYou must trim your hand. Please discard a card.");
    printHand(hand);
    return promptForCardIndex(hand.size());
  }

  public void printCardsToAdd(List<AdventureCard> cards) {
    print("\nADVENTURE CARDS TO ADD TO HAND:");
    print(cards.toString());
  }

  public void printHand(List<AdventureCard> hand) {
    print("\nYOUR HAND:");
    for (int i = 0; i < hand.size(); i++) {
      print(String.format("%d. %s", i + 1, hand.get(i)));
    }
  }

  public int promptForCardIndex(int maxIndex) {
    return promptForCardIndexWithQuit(maxIndex, false);
  }

  public int promptForCardIndexWithQuit(int maxIndex, boolean allowQuit) {
    while (true) {
      print("\nCHOOSE A CARD POSITION:");
      try {
        String line = input.nextLine().trim();
        if (line.isEmpty()) {
          print(String.format("Empty input. Please enter a number between 1 and %d%s.", maxIndex, allowQuit ? " or QUIT" : ""));
          continue;
        }
        if (allowQuit && line.equalsIgnoreCase("QUIT")) {
          return -1;
        }
        int index = Integer.parseInt(line);
        if (index >= 1 && index <= maxIndex) {
          return index - 1;
        }
        print(String.format("Out of range. Please enter a number between 1 and %d%s.", maxIndex, allowQuit ? " or QUIT" : ""));
      } catch (NumberFormatException e) {
        print(String.format("Not a valid number. Please enter a number between 1 and %d%s.", maxIndex, allowQuit ? " or QUIT" : ""));
      } catch (Exception e) {
        print("An error occurred. Please try again.");
      }
    }
  }

  public boolean promptForSponsor(Player player, Player currentPlayer, int numStages) {
    print(String.format("\n%s, do you want to be the sponsor for this %d-stage quest?", player.getName(), numStages));
    if (!player.equals(currentPlayer)) printHand(player.getHand());
    while (true) {
      print("\nEnter Y or N:");
      String line = input.nextLine().trim().toLowerCase();
      if (line.equals("y")) {
        return true;
      } else if (line.equals("n")) {
        return false;
      } else {
        print("Invalid input. Please enter Y or N.");
      }
    }
  }

  public void printSponsorFound(Player player) {
    print(String.format("\n%s will be the sponsor for this quest.", player.getName()));
    print("\nPress the return key to clear the display and begin setting up the quest.");
    input.nextLine();
    clear();
  }

  public void printSponsorDeclined(Player player) {
    print(String.format("\n%s declined to be the sponsor.", player.getName()));
  }

  public void printSponsorNotFound() {
    print("\nNo sponsor found. Quest will not continue.");
  }

  public void printStageSetup(int stageNum, List<AdventureCard> hand) {
    print(String.format("\nSETTING UP STAGE %d...", stageNum));
    printHand(hand);
    printStageSetupRules();
  }

  public void printAttackSetup(int stageNum, Participant participant) {
    print(String.format("\nCURRENT PARTICIPANT: %s", participant.getPlayer().getName()));
    print(String.format("ATTACK ON STAGE: %d", stageNum));
    printHand(participant.getPlayer().getHand());
    printAttackSetupRules();
  }

  public void printAttackSetupRules() {
    print("\nATTACK SETUP RULES: Each stage attack must consist of only non-repeated Weapon cards.");
    print("Choose the cards you want to use to attack this stage.");
    print("Enter QUIT once you are satisfied with your attack setup.");
  }

  public void printStageSetupRules() {
    print("\nSTAGE SETUP RULES: Each stage must consist of a single Foe card and 0/+ non-repeated Weapon cards.");
    print("Choose the cards you want to add to this stage.");
    print("Enter QUIT once you are ready to proceed to the next stage.");
  }

  public void printCardAddedToStage(List<AdventureCard> cards) {
    print("\nCARD ADDED TO STAGE:");
    print(cards.toString());
  }

  public void printCardAddedToAttack(List<AdventureCard> cards) {
    print("\nCARD ADDED TO ATTACK:");
    print(cards.toString());
  }

  public void promptNextParticipantAttack(int stageNum, Participant participant) {
    print(String.format("\nSTAGE %d ATTACK SETUP COMPLETE FOR %s.", stageNum, participant.getPlayer().getName()));
    printCardAddedToAttack(participant.getAttackCards());
    promptNextPlayer();
    clear();
  }

  public void printQuestSetupComplete(Quest quest) {
    print("\nQUEST SETUP COMPLETE.");
    print("Cards used for the quest:");
    for (Stage stage : quest.getStages()) {
      print(String.format("Stage %d: %s", quest.getStages().indexOf(stage) + 1, stage.getCards().toString()));
    }
    print("\nPress the return key to clear the display for participants.");
    input.nextLine();
    clear();
  }

  public void promptContinueToAttack() {
    print("\nPress the return key to continue to setting up the attack.");
    input.nextLine();
    clear();
  }

  public void printParticipants(List<Participant> participants) {
    print("\nELIGIBLE PARTICIPANTS:");
    print(participants.toString());
  }

  public List<Participant> promptForParticipants(List<Participant> participants) {
    List<Participant> activeParticipants = new ArrayList<>();
    for (Participant participant : participants) {
      print(String.format("\n%s, do you want to be a participant for this stage?", participant.getPlayer().getName()));
      printHand(participant.getPlayer().getHand());

      while (true) {
        print("\nEnter Y or N:");
        String line = input.nextLine().trim().toLowerCase();
        if (line.equals("y")) {
          activeParticipants.add(participant);
          print(String.format("%s will be a participant for this stage.", participant.getPlayer().getName()));
          break;
        } else if (line.equals("n")) {
          print(String.format("%s will not be a participant for this stage.", participant.getPlayer().getName()));
          break;
        } else {
          print("Invalid input. Please enter Y or N.");
        }
      }

      print("\nPress the return key to clear the display for the next participant.");
      input.nextLine();
      clear();
    }
    return activeParticipants;
  }

  public void promptNextStageSetup(int stageNumber, Stage stage) {
    print(String.format("\nSTAGE %d SETUP COMPLETE.", stageNumber));
    printCardAddedToStage(stage.getCards());
    print("\nPress the return key to clear the display and continue setting up the next stage.");
    input.nextLine();
    clear();
  }

  public void promptReturnToSponsor() {
    print("\nPress the return key to clear the display and return to the sponsor.");
    input.nextLine();
    clear();
  }

  public void promptEndTurn(String playerName) {
    print(String.format("\n%s'S TURN ENDED.", playerName));
    print("\nPress the return key to end your turn and clear the display.");
    input.nextLine();
  }

  public void promptNextPlayer() {
    print("\nPress the return key to clear the display for the next player.");
    input.nextLine();
  }

  public void promptReturnToOriginalPlayer() {
    print("\nPress the return key to clear the display and return to the original player.");
    input.nextLine();
  }

  public void printGameOver(List<Player> winners) {
    print("\nGame Over! Winners:");
    winners.forEach(winner -> print(String.format("- %s", winner.getName())));
  }

  public void clear() {
    output.print(CLEAR_SCREEN_COMMAND);
    output.flush();
  }

  public void setInput(String input) {
    this.input = new Scanner(input);
  }
}
