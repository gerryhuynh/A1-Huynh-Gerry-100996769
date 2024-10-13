package org.example;

import org.example.cards.AdventureCard;

import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Display {
  private PrintWriter output;

  public Display(PrintWriter output) {
    this.output = output;
  }

  public void print(String message) {
    output.println(message);
    output.flush();
  }

  public int promptForCardToDiscard(Scanner input, List<AdventureCard> hand) {
    return 0;
  }

  public void printHand(List<AdventureCard> hand) {
    print("Your hand:");
    print(hand.toString());
  }

  public int promptForCardIndex(Scanner input, int maxIndex) {
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
}
