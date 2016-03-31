package com.danimaniarqsoft.brain.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * WeekReportMain class execute the main app.
 * 
 * @author Daniel Cortes Pichardo
 */
public class WeekReportMain extends Application {
  private static final Logger LOGGER = LoggerFactory.getLogger(WeekReportMain.class);

  public WeekReportMain() {

  }

  public static void main(String[] args) {
    LOGGER.info("Starting Brain");
    launch(args);
  }

  @Override
  public void start(Stage stage) throws Exception {
    LOGGER.info("Starting Brain application");
    String fxmlFile = "/fxml/main.fxml";
    FXMLLoader loader = new FXMLLoader();
    Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
    Scene scene = new Scene(rootNode, 400, 200);
    scene.getStylesheets().add("/styles/styles.css");
    stage.setTitle("Brain Report");
    stage.setScene(scene);
    stage.show();
  }
}
