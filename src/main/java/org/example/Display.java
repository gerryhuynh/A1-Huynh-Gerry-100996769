package org.example;

import org.example.cards.AdventureCard;
import org.example.cards.EventCard;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Display {
  public static final String CLEAR_SCREEN_COMMAND = "\033[H\033[2J";
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
    while (true) {
      print("\nCHOOSE A CARD POSITION:");
      try {
        String line = input.nextLine().trim();
        if (line.isEmpty()) {
          print(String.format("Empty input. Please enter a number between 1 and %d.", maxIndex));
          continue;
        }
        int index = Integer.parseInt(line);
        if (index >= 1 && index <= maxIndex) {
          return index - 1;
        }
        print(String.format("Out of range. Please enter a number between 1 and %d.", maxIndex));
      } catch (NumberFormatException e) {
        print(String.format("Not a valid number. Please enter a number between 1 and %d.", maxIndex));
      } catch (Exception e) {
        print("An error occurred. Please try again.");
      }
    }
  }

  public void promptEndTurn(String playerName) {
    print(String.format("\n%s'S TURN ENDED.", playerName));
    print("\nPress the return key to end your turn and clear the display.");
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

  public void setScanner(Scanner input) {
    this.input = input;
  }
}
