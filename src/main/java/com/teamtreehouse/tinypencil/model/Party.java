package com.teamtreehouse.tinypencil.model;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Party {
  private List<Sheet> sheets;

  public Party(String... names) {
    sheets = Stream.of(names)
            .map(Sheet::new)
            .collect(toList());
  }

  // FIXME:  ARRRRGGGGGGGHHHHHH OPTIONAL?   WHYY??!!!!!
  public Optional<Sheet> getSheetFor(String name) {
    return sheets.stream()
            .filter(sheet -> sheet.getName().equalsIgnoreCase(name))
            .findFirst();

  }

  public List<Sheet> getSheets() {
    return sheets;
  }

  public void takeScore(String name, int hole, int score) {
    Optional<Sheet> sheet = getSheetFor(name);
    // FIXME:  HOW IS THIS NOT JUST A NULL CHECK?!!!! OPTIONALS MAKE ME FACEPALM
    if (sheet.isPresent()) {
      sheet.get().setScoreForHole(hole, score);
    } else {
      throw new IllegalArgumentException("Couldn't find sheet for " + name);
    }
  }

  public Optional<Sheet> getWinner() {
    return sheets.stream()
            .filter(sheet -> sheet.getRemainingHoleCount() < Sheet.PAR.length)
            .min(Comparator.comparing(Sheet::getScore));
  }


  public Optional<Sheet> getAdjustedWinner() {
    return sheets.stream()
            .filter(sheet -> sheet.getRemainingHoleCount() < Sheet.PAR.length)
            .min(Comparator.comparing(Sheet::getAdjustedScore));
  }

  public List<Sheet> ranked() {
    return sheets.stream()
            .sorted(Comparator.comparing(Sheet::getAdjustedScore))
            .collect(toList());
  }
}
