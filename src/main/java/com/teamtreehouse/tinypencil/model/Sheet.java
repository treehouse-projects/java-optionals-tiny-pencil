package com.teamtreehouse.tinypencil.model;

import java.util.OptionalDouble;
import java.util.stream.IntStream;

public class Sheet {
  public static final int MAX_STROKES = 6;
  public static final int[] PAR = {
          4,
          3,
          2,
          4,
          4,
          4,
          3,
          3,
          4,
          4,
          5,
          4,
          4,
          5,
          4,
          4,
          3,
          4
  };
  private String name;
  private int[] scores;

  public Sheet(String name) {
    this.name = name;
    scores = new int[18];
  }

  public String toString() {
    return String.format("%s (%d holes remaining)", name, getRemainingHoleCount());
  }

  public long getRemainingHoleCount() {
    return IntStream.of(scores)
            .filter(score -> score == 0)
            .count();
  }

  public String getName() {
    return name;
  }

  public int getScoreForHole(int hole) {
    return scores[hole - 1];
  }

  public int getParForHole(int hole) {
    return PAR[hole - 1];
  }

  public void setScoreForHole(int hole, int score) {
    // Holes are 1 thru 18
    scores[hole - 1] = score;
  }

  public int getScore() {
    return IntStream.of(scores)
            .filter(score -> score > 0)
            .sum();
  }

  public int getAdjustedScore() {
    return IntStream.of(scores)
            .map(score -> Math.min(score, MAX_STROKES))
            .filter(score -> score > 0)
            .sum();
  }

  public OptionalDouble getCourseAverage() {
    return IntStream.of(scores)
            .filter(score -> score > 0)
            .average();
  }

  public OptionalDouble getAdjustedCourseAverage() {
    return IntStream.of(scores)
            .map(score -> Math.min(score, MAX_STROKES))
            .filter(score -> score > 0)
            .average();
  }

}
