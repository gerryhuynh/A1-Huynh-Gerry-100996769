package org.example;

import org.example.cards.AdventureCard;

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

  public int promptForCardToDiscard(List<AdventureCard> hand) {
    print("You must discard a card.");
    printHand(hand);
    return promptForCardIndex(hand.size());
  }

  public void printCardsToAdd(List<AdventureCard> cards) {
    print("Cards to add to hand:");
    print(cards.toString());
  }

  public void printHand(List<AdventureCard> hand) {
    print("Your hand:");
    print(hand.toString());
  }

  public int promptForCardIndex(int maxIndex) {
    while (true) {
      print("Choose a card position:");
      try {
        String line = input.nextLine().trim();
        if (line.isEmpty()) {
          print("Empty input. Please enter a number between 1 and " + maxIndex);
          continue;
        }
        int index = Integer.parseInt(line);
        if (index >= 1 && index <= maxIndex) {
          return index - 1;
        }
        print("Out of range. Please enter a number between 1 and " + maxIndex);
      } catch (NumberFormatException e) {
        print("Not a valid number. Please enter a number between 1 and " + maxIndex);
      } catch (Exception e) {
        print("An error occurred. Please try again.");
      }
    }
  }

  public void promptEndTurn(String playerName) {
    print(String.format("%s's turn ended", playerName));
    print("Press the return key to end your turn and clear the display");
    input.nextLine();
  }

  public void clear() {
    output.print(CLEAR_SCREEN_COMMAND);
    output.flush();
  }

  public void setScanner(Scanner input) {
    this.input = input;
  }
}
