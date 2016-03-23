package com.danimaniarqsoft.brain.main;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class MainTest {
  private static Configuration cfg = null;

  static {
    cfg = new Configuration(new Version("2.3.0"));
  }

  public static void main(String[] args) {
    try {
      // Load template
      Template template = cfg.getTemplate("/src/main/resources/templates/weekReport.html");

      // Create data for template
      Map<String, Object> templateData = new HashMap<String, Object>();
      templateData.put("name", "Java Honk");

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
      Writer file = new FileWriter(new File("/home/daniel/BuildXMLTemplateXML.ftl"));
      template.process(templateData, file);
      file.flush();
      file.close();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
