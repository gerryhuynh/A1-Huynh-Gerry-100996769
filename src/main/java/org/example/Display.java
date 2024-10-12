package org.example;

import org.example.cards.EventCard;

import java.io.PrintWriter;

public class Display {
  private PrintWriter output;

  public Display(PrintWriter output) {
    this.output = output;
  }

  public void showEventCard(EventCard eventCard) {
    output.println(eventCard);
    output.flush();
  }
}
