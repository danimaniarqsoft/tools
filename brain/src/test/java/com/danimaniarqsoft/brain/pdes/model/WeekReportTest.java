package com.danimaniarqsoft.brain.pdes.model;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

public class WeekReportTest {
  @Test
  public void test() throws IOException, URISyntaxException {
    // String URL
    // ="http://localhost:2468/dads_strategy2016//reports/week.class?tl=auto&labelFilterAuto=t&pathFilterAuto=t";
    String URL = readFile("test.html", Charset.defaultCharset());
    String report = new WeekReport().createReport(URL);
  }

  private static String readFile(String resource, Charset encoding) throws IOException, URISyntaxException {
    byte[] encoded = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource(resource).toURI()));
    return new String(encoded, encoding);
  }
}
