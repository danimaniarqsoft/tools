package com.danimaniarqsoft.brain.util;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.utils.URIUtils;

public class UrlContext {
  private String scheme;
  private String host;
  private String port;
  private String projectName;

  public String getScheme() {
    return scheme;
  }

  public void setScheme(String scheme) {
    this.scheme = scheme;
  }

  public String getHost() {
    return host;
  }

  public void setHost(String host) {
    this.host = host;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public URI getWeekReportUrl() throws NumberFormatException, URISyntaxException {
    return URIUtils.createURI(scheme, host, Integer.parseInt(port),
        projectName + "//reports/week.class", "tl=auto&labelFilterAuto=t&pathFilterAuto=t", null);
  }
}
