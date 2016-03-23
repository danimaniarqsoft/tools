package com.danimaniarqsoft.brain.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

public class ContextUtil {
  public static UrlContext getUrlContext() throws IOException {
    Properties mainProperties = new Properties();
    FileInputStream file;
    String path = Constants.FILE_PDES_PROPERTIES;
    file = new FileInputStream(path);
    mainProperties.load(file);
    file.close();
    UrlContext urlCtx = new UrlContext();
    urlCtx.setHost(Constants.PDES_CLIENT_HOST_NAME);
    urlCtx.setScheme(Constants.PDES_SCHEME);
    urlCtx.setPort(mainProperties.getProperty(Constants.PROPERTY_PORT));
    urlCtx.setProjectName(mainProperties.getProperty(Constants.PROPERTY_PROJECT));
    return urlCtx;
  }

  public static void saveToDisk(StringBuilder sb, String dataFile) {
    FileOutputStream fop = null;
    File file;
    String content = sb.toString();

    try {

      file = new File(dataFile);
      fop = new FileOutputStream(file);
      // if file doesnt exists, then create it
      if (!file.exists()) {
        file.createNewFile();
      }

      // get the content in bytes
      byte[] contentInBytes = content.getBytes();

      fop.write(contentInBytes);
      fop.flush();
      fop.close();

      System.out.println("Done");

    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (fop != null) {
          fop.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

  }
  
  public static void saveExceptionToDisk(Throwable e, String fileName){
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    saveToDisk(new StringBuilder(sw.toString()), fileName);
  }
}
