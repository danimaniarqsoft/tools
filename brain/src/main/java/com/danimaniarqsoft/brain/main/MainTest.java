package com.danimaniarqsoft.brain.main;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import com.danimaniarqsoft.brain.pdes.model.PerformanceTable;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class MainTest {
  private static Configuration cfg = null;

  static {
    cfg = new Configuration(new Version("2.3.0"));
  }

  public static void main(String[] args) {
    cfg.setClassForTemplateLoading(MainTest.class, "/");

    try {
      // Load template
      Template template = cfg.getTemplate("templates/weekReport.html");
      

      PerformanceTable pt = dummyTable();
      // Create data for template
      Map<String, Object> templateData = new HashMap<String, Object>();
      templateData.put("name", "Java Honk");
      templateData.put("pTable", pt);
      // Write output on console example 1
      StringWriter out = new StringWriter();
      template.process(templateData, out);
      System.out.println(out.getBuffer().toString());
      out.flush();

      // Write output on console example 2
      /*
       * Writer out = new OutputStreamWriter(System.out); template.process(templateData, out);
       * out.flush();
       */

      // File output
      Writer file = new FileWriter(new File("./report.html"));
      template.process(templateData, file);
      file.flush();
      file.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static PerformanceTable dummyTable() {
    return PerformanceTable.getBuilder().withVg("7.75").withVgDiff("0.0").withVgFalta("0.0")
        .withTaskHours("37.95").withTaskClosed("33.0")
        .withHoursNotFinished(Double.toString(7.350000000000001d))
        .withWeekHrsNotFinished(Double.toString(0.7750439367311074d))
        .withVgPerHour(Double.toString(0.25326797385620914d))
        .withVgNotPerformed(Double.toString(1.8615196078431375d))
        .withRecovery(Double.toString(3.7551546391752577d)).build();
  }
}
