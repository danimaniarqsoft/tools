package com.danimaniarqsoft.brain.pdes.model;

import java.io.IOException;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.danimaniarqsoft.brain.main.DateCalculator;

public class WeekTable {
  private double[][] dataTable = new double[Row.values().length][Column.values().length];

  public WeekTable(Document doc) throws IOException {
    Elements elements = doc.select("body table tbody tr");
    this.dataTable = readValues(elements);
  }

  public double getProperty(int i, int j) {
    return dataTable[i][j];
  }

  private static double[][] readValues(Elements elements) {

    double[][] dataTableValue = new double[Row.values().length][Column.values().length];
    int rowIndex = 0;

    for (Row row : Row.values()) {
      int colIndex = 0;
      for (Column col : Column.values()) {
        dataTableValue[rowIndex][colIndex] = getValue(row, col, elements);
        colIndex++;
      }
      rowIndex++;
    }
    return dataTableValue;
  }

  private static double getValue(Row row, Column column, Elements table) {
    Elements elements = table.get(row.getIndex()).getAllElements();
    String sValue = elements.get(column.getIndex()).text();
    if (column == Column.HORAS_ACTUAL || column == Column.HORAS_PLAN) {
      return DateCalculator.convertDate(sValue);
    } else if (column == Column.EV_ACTUAL || column == Column.EV_PLAN) {
      return getDoubleValue(sValue.replaceFirst("%", ""));
    } else {
      return getDoubleValue(sValue);
    }
  }

  private static double getDoubleValue(String cad) {
    String cadVal = cad.replaceAll("\\s", "");
    int nbsp = -1;
    if (!cadVal.isEmpty()) {
      nbsp = cadVal.charAt(0);
    }

    if (cadVal == null || "".equals(cadVal) || cadVal.isEmpty() || nbsp == 160) {
      return 0.0;
    }
    return Double.parseDouble(cadVal);
  }
}
