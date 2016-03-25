package com.danimaniarqsoft.brain.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;

import javax.imageio.ImageIO;

public class ContextUtil {
  private ContextUtil() {

  }

  public static UrlContext getUrlContext() throws IOException {
    Properties mainProperties = new Properties();
    FileInputStream file;
    String path = Constants.FILE_PDES_PROPERTIES;
    file = new FileInputStream(path);
    mainProperties.load(file);
    file.close();
    return UrlContext.createUrl().withHost(Constants.PDES_CLIENT_HOST_NAME)
        .withScheme(Constants.PDES_SCHEME)
        .withPort(mainProperties.getProperty(Constants.PROPERTY_PORT))
        .withProjectName(mainProperties.getProperty(Constants.PROPERTY_PROJECT));
  }

  public static void saveToDisk(StringBuilder sb, String dataFile) {
    new File(Constants.REPORT_FOLDER).mkdir();
    FileOutputStream fop = null;
    File file;
    String content = sb.toString();

    try {

      file = new File(Constants.REPORT_FOLDER + "/" + dataFile);
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

    } catch (IOException e) {
      saveExceptionToDisk(e, "error.txt");
    } finally {
      try {
        if (fop != null) {
          fop.close();
        }
      } catch (IOException e) {
        saveExceptionToDisk(e, "error.txt");
      }
    }
  }

  public static void saveImageToDisk(BufferedImage image, String fileName) throws IOException {
    new File(Constants.REPORT_IMG_FOLDER).mkdirs();
    ImageIO.write(image, Constants.EXTENSION_PNG,
        new File(Constants.REPORT_IMG_FOLDER + "/" + fileName + "." + Constants.EXTENSION_PNG));
  }

  public static void saveExceptionToDisk(Throwable e, String fileName) {
    StringWriter sw = new StringWriter();
    PrintWriter pw = new PrintWriter(sw);
    e.printStackTrace(pw);
    saveToDisk(new StringBuilder("Please send this log to: daniel.cortes@infotec.mx").append("\n\n")
        .append(sw.toString()), fileName);
  }
}
