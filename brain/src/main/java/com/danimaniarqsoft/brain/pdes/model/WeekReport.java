package com.danimaniarqsoft.brain.pdes.model;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class WeekReport {
  public String createReport(final String url) throws IOException {
     Document doc = Jsoup.connect(url).get();
//    Document doc = Jsoup.parse(url);
    WeekTable table = new WeekTable(doc);

    for (int i = 0; i < Row.values().length; i++) {
      for (int j = 0; j < Column.values().length; j++) {
        System.out.print(table.getProperty(i, j));
        System.out.print(", ");
      }
      System.out.println("");
    }
    return null;
  }
}
