package com.danimaniarqsoft.brain.pdes.service;

import java.io.IOException;
import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.danimaniarqsoft.brain.pdes.model.PerformanceTable;
import com.danimaniarqsoft.brain.pdes.model.Report;
import com.danimaniarqsoft.brain.pdes.model.WeekTable;

/**
 * WeekReportService
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class WeekReportService {
  private WeekReportService() {

  }

  /**
   * 
   * @param uri
   * @return
   * @throws IOException
   */
  public static Report createReport(final URI uri) throws IOException {
    Document doc = Jsoup.connect(uri.toString()).get();
    WeekTable table = new WeekTable(doc);

    String vg = computeVg(table);
    String vgDiff = computeVgDiff(table);
    String vgFalta = computeVgFalta(table);
    String horasTarea = computeTaskHours(table);
    String tareaCerradas = computeClosedTask(table);
    double hsTareasTerm = computeHoursTaskNotFinished(table);
    String semHrTarNoTerm = computeWeekhrsTaskNotFinished(table, hsTareasTerm);
    double vhxH = computeVgXh(table);
    String vgNoRe = computeVgNotPerformed(vhxH, hsTareasTerm);
    String recup = computeRecoveryWeeks(table);

    PerformanceTable pTable = PerformanceTable.getBuilder().withVg(vg).withVgDiff(vgDiff)
        .withVgFalta(vgFalta).withTaskHours(horasTarea).withTaskClosed(tareaCerradas)
        .withHoursNotFinished(Double.toString(hsTareasTerm)).withWeekHrsNotFinished(semHrTarNoTerm)
        .withVgPerHour(Double.toString(vhxH)).withVgNotPerformed(vgNoRe).withRecovery(recup)
        .build();

    return new Report(table, pTable);
  }


  public static String computeVg(WeekTable table) {
    return Double.toString(table.getDoubleProperty(1, 4));
  }

  public static String computeVgDiff(WeekTable table) {
    return Double.toString(table.getDoubleProperty(1, 4) - table.getDoubleProperty(1, 3));
  }


  public static String computeVgFalta(WeekTable table) {
    return Double.toString((1 - table.getDoubleProperty(1, 5)) * 100);
  }

  public static String computeTaskHours(WeekTable table) {
    return Double.toString(table.getDoubleProperty(1, 1));
  }

  public static String computeClosedTask(WeekTable table) {
    return Double.toString((table.getDoubleProperty(3, 2) * 100) - 100);
  }

  public static double computeHoursTaskNotFinished(WeekTable table) {
    return table.getDoubleProperty(1, 1) - table.getDoubleProperty(3, 1);
  }

  public static String computeWeekhrsTaskNotFinished(WeekTable table, double hsTareasTerm) {
    return Double.toString(hsTareasTerm / table.getDoubleProperty(2, 1));
  }

  public static double computeVgXh(WeekTable table) {
    return table.getDoubleProperty(1, 4) / table.getDoubleProperty(3, 1);
  }

  public static String computeVgNotPerformed(double vhxH, double hsTareasTerm) {
    return Double.toString(vhxH * hsTareasTerm);
  }

  public static String computeRecoveryWeeks(WeekTable table) {
    return Double.toString(table.getDoubleProperty(1, 3)
        - (table.getDoubleProperty(1, 4)) / table.getDoubleProperty(2, 4));
  }

}
