package com.danimaniarqsoft.brain.pdes.service;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.pdes.model.Report;

public abstract class AbstractHtmlTemplate {

  protected abstract void createIndexFile(Report report) throws ReportException;

  protected abstract void createDefectFile(Report report) throws ReportException;

  protected abstract void createExternalCommitmentsFile(Report report) throws ReportException;

  protected abstract void createHoursFile(Report Report) throws ReportException;

  protected abstract void createTaskProgressFile(Report report) throws ReportException;

  protected abstract void createVgFile(Report Report) throws ReportException;

  protected abstract void createWeekResumeFile(Report report) throws ReportException;

  protected abstract void createSupportFile(Report report) throws ReportException;

  protected abstract void createPerformanceFile(Report report) throws ReportException;

  protected abstract void createMilestonesFile(Report report) throws ReportException;

  protected abstract void createWebSite(Report report) throws ReportException;



  public void saveHtmlReport(Report report) throws ReportException {
    createIndexFile(report);
    createWeekResumeFile(report);
    createPerformanceFile(report);
    createVgFile(report);
    createHoursFile(report);
    createExternalCommitmentsFile(report);
    createMilestonesFile(report);
    createTaskProgressFile(report);
    createTaskProgressFile(report);
    createDefectFile(report);
    createSupportFile(report);
    createWebSite(report);
  }

}
