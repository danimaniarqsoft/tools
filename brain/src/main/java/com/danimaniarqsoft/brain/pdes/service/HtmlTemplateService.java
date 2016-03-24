package com.danimaniarqsoft.brain.pdes.service;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.danimaniarqsoft.brain.main.MainTest;
import com.danimaniarqsoft.brain.pdes.model.PerformanceTable;
import com.danimaniarqsoft.brain.pdes.model.Report;
import com.danimaniarqsoft.brain.util.Constants;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class HtmlTemplateService {
  private static Configuration cfg = null;

  static {
    cfg = new Configuration(new Version("2.3.0"));
    cfg.setClassForTemplateLoading(HtmlTemplateService.class, "/");
  }

  public static void saveHtmlReport(Report report) {
    try {
      // Load template
      Template template = cfg.getTemplate("templates/weekReport.html");
      PerformanceTable pt = report.getPerformanceTable();
      // Create data for template
      Map<String, Object> templateData = new HashMap<String, Object>();
      templateData.put("pTable", pt);
      for (int i = 0; i < report.getWeekTable().getNumRows(); i++) {
        for (int j = 0; j < report.getWeekTable().getNumCols(); j++) {
          templateData.put("data" + i + "" + j, report.getWeekTable().getStringProperty(i, j));
        }
      }
      // Write output on console example 1
      StringWriter out = new StringWriter();
      template.process(templateData, out);
      out.flush();
      // File output
      new File(Constants.REPORT_FOLDER).mkdir();
      Writer file = new FileWriter(new File(Constants.REPORT_FOLDER + "/" + "report.html"));
      template.process(templateData, file);
      file.flush();
      file.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
