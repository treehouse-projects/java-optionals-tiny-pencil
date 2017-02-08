package com.teamtreehouse.tinypencil.ui;

import com.teamtreehouse.tinypencil.model.Party;
import com.teamtreehouse.tinypencil.model.Sheet;

import java.util.stream.IntStream;

public class Dashboard {
  private final String separator = "---------------------------";

  public void displayHeading(String title) {
    System.out.printf("%n%n");
    System.out.println(separator);
    System.out.println(title);
    System.out.println(separator);
  }

  public void displayWinner(Party party) {
    displayHeading("Winner");
    System.out.println(party.getAdjustedWinner());
  }

  public void displaySummary(Party party) {
    displayHeading("Final Scores");
    party.ranked().stream()
            .map(sheet -> String.format("%s: Actual(%d) Adjusted(%d) Holes Remaining(%d)",
                    sheet.getName(),
                    sheet.getScore(),
                    sheet.getAdjustedScore(),
                    sheet.getRemainingHoleCount())
            )
            .forEach(System.out::println);
  }

  public void displayPartyDetail(Party party) {
    party.ranked().forEach(this::displaySheetDetail);
  }

  public void displaySheetDetail(Sheet sheet) {
    displayHeading("Detail for " + sheet.getName());
    System.out.println("Course Average: " + sheet.getCourseAverage());
    System.out.println("Adjusted Course average: " + sheet.getAdjustedCourseAverage());
    System.out.println("HOLE\t\tSCORE\t\tPAR");
    IntStream.rangeClosed(1, Sheet.PAR.length)
            .mapToObj(hole -> String.format("%d.\t\t\t%d\t\t\t%d",
                    hole,
                    sheet.getScoreForHole(hole),
                    sheet.getParForHole(hole)))
            .forEach(System.out::println);


  }

}
