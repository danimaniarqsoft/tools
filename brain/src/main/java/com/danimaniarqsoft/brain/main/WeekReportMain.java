package com.danimaniarqsoft.brain.main;

import java.io.IOException;

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

  private WeekReportMain() {
    
  }

  public static void main(String[] args) {
    try {
      UrlContext context = ContextUtil.getUrlContext();
      new PersonalReportService().createReport(context);
    } catch (ReportException | IOException e) {
      ContextUtil.saveExceptionToDisk(e, Constants.FILE_ERROR_TXT);
    }
    System.out.println(
        "Thanks for using danimaniarqsoft solutions, visit my web page at www.danimanicp.com for futher news");
  }
}
