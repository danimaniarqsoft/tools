package com.danimaniarqsoft.brain.dao;

import java.io.IOException;
import java.net.URI;

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

    for (int i = 1; i < rows.size(); i++) { // first row is the col names so skip it.
      Element row = rows.get(i);
      Elements cols = row.select("td");
      Object[] data = new Object[cols.size()];
      for (int j = 0; j < data.length; j++) {
        data[j] = cols.get(0);
      }
    }
    return new SizeTable();
  }
}
