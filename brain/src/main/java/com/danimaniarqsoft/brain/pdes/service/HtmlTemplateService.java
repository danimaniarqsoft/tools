package com.danimaniarqsoft.brain.pdes.service;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.pdes.model.PerformanceTable;
import com.danimaniarqsoft.brain.pdes.model.Report;
import com.danimaniarqsoft.brain.util.Constants;
import com.danimaniarqsoft.brain.util.TemplateUtil;
import com.danimaniarqsoft.brain.util.ZipUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class HtmlTemplateService extends AbstractHtmlTemplate {
  private static Configuration cfg = null;
  private static Template      mainTemplate;

  static {
    cfg = new Configuration(new Version("2.3.0"));
    cfg.setClassForTemplateLoading(HtmlTemplateService.class, "/");
  }

  @Override
  protected void createIndexFile(Report report) throws ReportException {
    try {
      new File(Constants.REPORT_FOLDER).mkdir();
      mainTemplate = cfg.getTemplate("templates/mainLayout.html");
      HashMap<String, Object> data = new HashMap<String, Object>();
      data.put("gn", report.getGeneralTable());
      TemplateUtil.saveTemplate(mainTemplate, cfg, data, "index.html");
    } catch (IOException e) {
      throw new ReportException("createIndexFile", e);
    }


  }

  @Override
  protected void createDefectFile(Report report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(), "defectChart.html");
  }

  @Override
  protected void createExternalCommitmentsFile(Report report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(),
        "extCommitmentsChart.html");
  }

  @Override
  protected void createHoursFile(Report Report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(), "hrsChart.html");
  }

  @Override
  protected void createTaskProgressFile(Report report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(),
        "taskProgressChart.html");


  }

  @Override
  protected void createVgFile(Report Report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(), "vgChart.html");

  }

  @Override
  protected void createWeekResumeFile(Report report) throws ReportException {
    Map<String, Object> templateData = new HashMap<String, Object>();
    for (int i = 0; i < report.getWeekTable().getNumRows(); i++) {
      for (int j = 0; j < report.getWeekTable().getNumCols(); j++) {
        templateData.put("data" + i + "" + j, report.getWeekTable().getStringProperty(i, j));
      }
    }
    TemplateUtil.saveTemplate(mainTemplate, cfg, templateData, "weekResume.html");

  }

  @Override
  protected void createSupportFile(Report report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(), "support.html");

  }

  @Override
  protected void createPerformanceFile(Report report) throws ReportException {
    PerformanceTable pt = report.getPerformanceTable();
    Map<String, Object> templateData = new HashMap<String, Object>();
    templateData.put("pTable", pt);
    TemplateUtil.saveTemplate(mainTemplate, cfg, templateData, "performance.html");
  }

  @Override
  protected void createMilestonesFile(Report report) throws ReportException {
    TemplateUtil.saveTemplate(mainTemplate, cfg, new HashMap<String, Object>(),
        "milestonesChart.html");
  }

  @Override
  protected void createWebSite(Report report) throws ReportException {
    ZipUtils.extract("/site/site.zip", new File("weekReport"));
  }
}
