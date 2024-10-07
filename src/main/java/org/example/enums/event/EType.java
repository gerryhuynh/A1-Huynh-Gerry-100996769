package org.example.enums.event;

public enum EType implements EventType {
  PLAGUE("Plague"), QUEENS_FAVOR("Queen's Favor"), PROSPERITY("Prosperity");

  private final String name;

  EType(String name) {
    this.name = name;
  }
}
