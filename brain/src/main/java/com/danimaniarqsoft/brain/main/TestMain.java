package com.danimaniarqsoft.brain.main;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.apache.http.client.utils.URIUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class TestMain {

  public static void main(String[] args)
      throws NumberFormatException, URISyntaxException, IOException {

    String project = "dads_strategy2016";
    String port = "2468";
    URI uri = URIUtils.createURI("http", "localhost", Integer.parseInt(port),
        project + "//reports/week.class", "tl=auto&labelFilterAuto=t&pathFilterAuto=t", null);
    String url =
        "http://localhost:2468/dads_strategy2016//reports/ev.class?tl=auto&labelFilterAuto=t&pathFilterAuto=t&charts&showChart=pdash.ev.cumValueChart";
    Document doc = Jsoup.connect(url).get();
    Elements elements = doc.select("body div div div img");
    System.out.println(elements.attr("src"));

    BufferedImage image = null;
    try {
      URL imageUrl =
          new URL("http://localhost:2468/dads_strategy2016//reports/pngCache?id=2611609");
      image = ImageIO.read(imageUrl);
      ImageIO.write(image, "png", new File("ev.png"));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // http://localhost:2468/dads_strategy2016//reports/pngCache?id=2611609
  }

  public static BufferedImage toBufferedImage(Image img) {
    if (img instanceof BufferedImage) {
      return (BufferedImage) img;
    }

    // Create a buffered image with transparency
    BufferedImage bimage =
        new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

    // Draw the image on to the buffered image
    Graphics2D bGr = bimage.createGraphics();
    bGr.drawImage(img, 0, 0, null);
    bGr.dispose();

    // Return the buffered image
    return bimage;
  }
}
