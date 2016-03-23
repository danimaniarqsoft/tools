package com.danimaniarqsoft.brain.pdes.service;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.util.UrlContext;

public abstract class AbstractReportTemplate {

  protected abstract void createWeekReport(UrlContext context) throws ReportException;

  protected abstract void locateEvImage(UrlContext context) throws ReportException;

  protected abstract void locateCumDirectTimeImage(UrlContext context) throws ReportException;

  protected abstract void locateEvTrendImage(UrlContext context) throws ReportException;

  protected abstract void locateDirectTimeTrendImage(UrlContext context) throws ReportException;

  protected abstract void locateInProgressTaskImage(UrlContext context) throws ReportException;
  
  protected abstract void locateDefectImages(UrlContext context) throws ReportException;
  
  protected abstract void locateExternalCommitmentsImage(UrlContext context) throws ReportException;
  
  protected abstract void locateMilestonesImage(UrlContext context) throws ReportException;
  
  
  public void createReport(UrlContext context) throws ReportException {
    createWeekReport(context);
    locateEvImage(context);
    locateCumDirectTimeImage(context);
    locateEvTrendImage(context);
    locateDirectTimeTrendImage(context);
    locateInProgressTaskImage(context);
    locateDefectImages(context);
    locateExternalCommitmentsImage(context);
    locateMilestonesImage(context);
  }
}
