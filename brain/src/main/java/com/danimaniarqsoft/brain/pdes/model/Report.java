package com.danimaniarqsoft.brain.pdes.model;

/**
 * 
 * @author Daniel Cortes Pichardo
 *
 */
public class Report {

  private WeekTable        weekTable;
  private PerformanceTable performanceTable;
  private GeneralTable     generalTable;

  public Report(GeneralTable gTable, WeekTable weekTable, PerformanceTable performanceTable) {
    this.generalTable = gTable;
    this.weekTable = weekTable;
    this.performanceTable = performanceTable;
  }

  public PerformanceTable getPerformanceTable() {
    return performanceTable;
  }

  public void setPerformanceTable(PerformanceTable performanceTable) {
    this.performanceTable = performanceTable;
  }

  public WeekTable getWeekTable() {
    return weekTable;
  }

  public void setWeekTable(WeekTable weekTable) {
    this.weekTable = weekTable;
  }

  public GeneralTable getGeneralTable() {
    return generalTable;
  }

  public void setGeneralTable(GeneralTable generalTable) {
    this.generalTable = generalTable;
  }
}
