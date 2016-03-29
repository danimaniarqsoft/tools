package com.danimaniarqsoft.brain.pdes.service;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.pdes.service.context.ReportContext;

public abstract class AbstractReportTemplate {

  protected abstract void createWeekReport(ReportContext context) throws ReportException;

  protected abstract void locateEvImage(ReportContext context) throws ReportException;

  protected abstract void locateCumDirectTimeImage(ReportContext context) throws ReportException;

  protected abstract void locateEvTrendImage(ReportContext context) throws ReportException;

  protected abstract void locateDirectTimeTrendImage(ReportContext context) throws ReportException;

  protected abstract void locateInProgressTaskImage(ReportContext context) throws ReportException;

  protected abstract void locateDefectImages(ReportContext context) throws ReportException;

  protected abstract void locateExternalCommitmentsImage(ReportContext context)
      throws ReportException;

  protected abstract void locateMilestonesImage(ReportContext context) throws ReportException;


  public void createReport(ReportContext context) throws ReportException {
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
