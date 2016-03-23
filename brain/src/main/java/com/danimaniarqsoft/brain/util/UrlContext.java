package com.danimaniarqsoft.brain.util;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.utils.URIUtils;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;

public class UrlContext {
  private String scheme;
  private String host;
  private String port;
  private String projectName;

  private UrlContext() {

  }

  public static UrlContext createUrl() {
    return new UrlContext();
  }

  public UrlContext withScheme(String scheme) {
    this.scheme = scheme;
    return this;
  }

  public UrlContext withHost(String host) {
    this.host = host;
    return this;
  }

  public UrlContext withPort(String port) {
    this.port = port;
    return this;
  }

  public UrlContext withProjectName(String projectName) {
    this.projectName = projectName;
    return this;
  }

  public URI getWeekReportUrl() throws NumberFormatException, URISyntaxException {
    return URIUtils.createURI(scheme, host, Integer.parseInt(port),
        projectName + "//reports/week.class", "tl=auto&labelFilterAuto=t&pathFilterAuto=t", null);
  }

  public URI getEvImageUrl() throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port),
          projectName + "//reports/ev.class",
          "tl=auto&labelFilterAuto=t&pathFilterAuto=t&charts&showChart=pdash.ev.cumValueChart",
          null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getEvImageUrl", e);
    }
  }

  public URI getInProgressTaskUrl() throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port),
          projectName + "//reports/ev.class",
          "tl=auto&labelFilterAuto=t&pathFilterAuto=t&charts&showChart=pdash.ev.tasksInProgressDiscChart",
          null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getInProgressTaskUrl", e);
    }
  }

  public URI getDirectHoursUrl() throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port),
          projectName + "//reports/ev.class",
          "tl=auto&labelFilterAuto=t&pathFilterAuto=t&charts&showChart=pdash.ev.cumDirectTimeChart",
          null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getDirectHoursUrl", e);
    }
  }

  public URI getEarnedValueTrendUrl() throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port),
          projectName + "//reports/ev.class",
          "tl=auto&labelFilterAuto=t&pathFilterAuto=t&charts&showChart=pdash.ev.valueTrendChart",
          null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getEarnedValueTrendUrl", e);
    }
  }

  public URI getDirectTimeTrendUrl() throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port),
          projectName + "//reports/ev.class",
          "tl=auto&labelFilterAuto=t&pathFilterAuto=t&charts&showChart=pdash.ev.timeTrendChart",
          null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getDirectTimeTrendUrl", e);
    }
  }

  public URI getDefectsUrl() throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port),
          projectName + "//cms/TSP/indiv_plan_summary", "frame=content&section=200", null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getDirectTimeTrendUrl", e);
    }
  }

  public URI getImageFromCacheUrl(String reportId) throws ReportException {
    try {
      return URIUtils.createURI(scheme, host, Integer.parseInt(port), projectName + "/" + reportId,
          null, null);
    } catch (NumberFormatException | URISyntaxException e) {
      throw new ReportException("getImageFromCacheUrl", e);
    }
  }

}
