package com.danimaniarqsoft.brain.pdes.service;

import java.io.IOException;
import java.net.URISyntaxException;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.util.Constants;
import com.danimaniarqsoft.brain.util.ContextUtil;
import com.danimaniarqsoft.brain.util.UrlContext;

public class PersonalReportService extends AbstractReportTemplate {

  @Override
  protected void createWeekReport(UrlContext context) throws ReportException {
    try {
      StringBuilder sb = WeekReportService.createReport(context.getWeekReportUrl());
      ContextUtil.saveToDisk(sb, Constants.FILE_WEEKDATA_TXT);
    } catch (NumberFormatException | IOException | URISyntaxException e) {
      throw new ReportException("createWeekReport", e);
    }
  }

  @Override
  protected void locateEvImage(UrlContext context) throws ReportException {
    // TODO Auto-generated method stub
  }
}
