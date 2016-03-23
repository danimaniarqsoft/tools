package com.danimaniarqsoft.brain.pdes.service;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.util.Constants;
import com.danimaniarqsoft.brain.util.ContextUtil;
import com.danimaniarqsoft.brain.util.UrlContext;

/**
 * 
 * @author Daniel Cortes Pichardo
 *
 */
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
    locateResource(context.getEvImageUrl().toString(), "body div div div img", "ev", context);
  }

  @Override
  protected void locateInProgressTaskImage(UrlContext context) throws ReportException {
    locateResource(context.getInProgressTaskUrl().toString(), Constants.BODY_EVCHARITEM_IMG,
        "inPogressTasks", context);
  }

  @Override
  protected void locateCumDirectTimeImage(UrlContext context) throws ReportException {
    locateResource(context.getDirectHoursUrl().toString(), Constants.BODY_EVCHARITEM_IMG,
        "directHours", context);
  }

  @Override
  protected void locateEvTrendImage(UrlContext context) throws ReportException {
    locateResource(context.getEarnedValueTrendUrl().toString(), Constants.BODY_EVCHARITEM_IMG,
        "evTrend", context);
  }

  @Override
  protected void locateDirectTimeTrendImage(UrlContext context) throws ReportException {
    locateResource(context.getDirectTimeTrendUrl().toString(), Constants.BODY_EVCHARITEM_IMG,
        "directTimeTrend", context);
  }

  @Override
  protected void locateDefectImages(UrlContext context) throws ReportException {
    locateResource(context.getDefectsUrl().toString(), "body p img", "defects", context);
  }

  @Override
  protected void locateExternalCommitmentsImage(UrlContext context) throws ReportException {
    locateResource(context.getExternalCommitmentsUrl().toString(), Constants.BODY_EVCHARITEM_IMG,
        "externalCommitments", context);
  }

  @Override
  protected void locateMilestonesImage(UrlContext context) throws ReportException {
    locateResource(context.getMilestonesUrl().toString(), Constants.BODY_EVCHARITEM_IMG,
        "milestones", context);
  }

  private void locateResource(final String contextUrl, final String xPathQuery,
      final String fileName, UrlContext context) {
    try {
      Document doc = Jsoup.connect(contextUrl).get();
      Elements elements = doc.select(xPathQuery);
      BufferedImage image;
      int count = 0;
      for (Element element : elements) {
        image =
            ImageIO.read(context.getImageFromCacheUrl(element.attr(Constants.ATTR_SRC)).toURL());
        ContextUtil.saveImageToDisk(image, fileName + blankIfZero(count++));
      }
    } catch (IOException | NumberFormatException | ReportException e) {
      ContextUtil.saveExceptionToDisk(e, Constants.FILE_ERROR_TXT);
    }
  }

  private static String blankIfZero(int val) {
    if (val == 0) {
      return "";
    } else {
      return Integer.toString(val);
    }
  }
}
