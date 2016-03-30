package com.danimaniarqsoft.brain.dao;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.SystemUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.danimaniarqsoft.brain.pdes.model.SizeTable;

/**
 * ReportDAO, xpath query to the PDES Report
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class OverallMetricsDAO {
  private Document overallMetrics;

  public OverallMetricsDAO(URI uri) throws IOException {
    this.overallMetrics = Jsoup.connect(uri.toString()).get();
  }

  public SizeTable findSizeTable(String xpathQuery) {
    Elements overallMetricsElements = overallMetrics.select(xpathQuery);
    Element sizeTable = overallMetricsElements.get(1);
    Elements rows = sizeTable.select("tr");
    String[][] table = new String[rows.size()][];
    for (int i = 0; i < rows.size(); i++) { // first row is the col names so skip it.
      Element row = rows.get(i);
      Elements cols = row.select("td");
      String[] data = new String[cols.size()];
      for (int j = 0; j < cols.size(); j++) {
        System.out.print(cols.get(j).text());
        data[j] = cols.get(j).text();
      }
      System.out.println();
      table[i] = data;
    }
    return new SizeTable(table);
  }
}
