package com.danimaniarqsoft.brain.main;

import java.io.IOException;
import java.net.URISyntaxException;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.pdes.service.PersonalReportService;
import com.danimaniarqsoft.brain.util.Constants;
import com.danimaniarqsoft.brain.util.ContextUtil;
import com.danimaniarqsoft.brain.util.UrlContext;

/*
 * WeekReportMain class execute the main app.
 * 
 * @author Daniel Cortes Pichardo
 */
public class WeekReportMain {

  public static void main(String[] args)
      throws NumberFormatException, URISyntaxException, IOException {
    UrlContext context = ContextUtil.getUrlContext();
    try {
      new PersonalReportService().createReport(context);
    } catch (ReportException e) {
      ContextUtil.saveExceptionToDisk(e, Constants.FILE_ERROR_TXT);
    }
  }
}
