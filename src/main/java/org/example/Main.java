package org.example;

public class Main {
  public static void main(String[] args) {
    Game game = new Game();
    game.setupPlayers();
    game.dealAdventureCards();

    while (!game.isGameOver()) {
      game.startTurn();
      game.playEventCard();
      game.getPlayers().forEach(player -> player.setShields(Game.SHIELDS_TO_WIN));
      game.getPlayers().get(1).setShields(8);
      game.getPlayers().get(2).setShields(4);
      game.endTurn();
    }
  }
}