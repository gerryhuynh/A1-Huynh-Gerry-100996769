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

  public void printHand(List<AdventureCard> hand) {
    return;
  }

  public int promptForCardIndex(Scanner input, int maxIndex) {
    return 0;
  }
}
