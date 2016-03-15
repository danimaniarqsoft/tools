package com.danimaniarqsoft.brain.main;

import java.text.ParseException;

public class DateCalculator {

  public static void main(String[] args) throws ParseException {
    String[] dateone = "11:30".split(":");
    int hours = Integer.parseInt(dateone[0]);
    double minutes = Integer.parseInt(dateone[1]);
    double minutess = minutes / 60;
    System.out.print(hours + minutess);
  }

  public static double convertDate(String date) {
    String[] dateone = date.split(":");
    int hours = Integer.parseInt(dateone[0]);
    double minutes = (Double.parseDouble(dateone[1]) / 60);
    return hours + minutes;
  }
}
