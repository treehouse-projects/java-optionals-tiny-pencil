package com.teamtreehouse.tinypencil.model;

import java.util.Random;
import java.util.stream.IntStream;

public class Helpers {

  public static void randomizeScores(Sheet sheet) {
    Random random = new Random();
    int[] scores = random.ints(Sheet.PAR.length, 1, 8)
            .toArray();
    IntStream.rangeClosed(1, 18)
            .forEach(hole -> sheet.setScoreForHole(hole, scores[hole - 1]));
  }

  public static void randomizeParty(Party party) {
    party.getSheets()
            .forEach(Helpers::randomizeScores);
  }
}
