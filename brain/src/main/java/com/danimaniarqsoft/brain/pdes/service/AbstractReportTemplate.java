package com.danimaniarqsoft.brain.pdes.service;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.util.UrlContext;

public abstract class AbstractReportTemplate {

  protected abstract void createWeekReport(UrlContext context) throws ReportException;

  protected abstract void locateEvImage(UrlContext context) throws ReportException;

  public void createReport(UrlContext context) throws ReportException {
    createWeekReport(context);
    locateEvImage(context);
  }
}
