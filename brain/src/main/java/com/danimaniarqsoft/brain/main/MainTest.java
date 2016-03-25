package com.danimaniarqsoft.brain.main;

import java.io.File;

import com.danimaniarqsoft.brain.util.ZipUtils;

public class MainTest {
  private static final String OUTPUT_FOLDER = "/home/daniel/outputzip";

  public static void main(String[] args) throws Exception {
    ZipUtils.extract("/site/site.zip", new File("weekReport"));
  }
}
