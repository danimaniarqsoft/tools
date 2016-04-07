package com.danimaniarqsoft.brain.main;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/*
 * WeekReportMain class execute the main app.
 * 
 * @author Daniel Cortes Pichardo
 */
public class WeekReportMain extends Application {
  private static final Logger LOGGER = LoggerFactory.getLogger(WeekReportMain.class);
  private Stage               primaryStage;
  private BorderPane          rootLayout;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    this.primaryStage = primaryStage;
    this.primaryStage.setTitle("Monkey Brain");
    initRootLayout();
    initMainAppLayout();
  }


  private void initMainAppLayout() throws IOException {
    String fxmlFile = "/fxml/Main.fxml";
    FXMLLoader loader = new FXMLLoader();
    Parent rootNode = (Parent) loader.load(getClass().getResourceAsStream(fxmlFile));
    primaryStage.show();
    rootLayout.setCenter(rootNode);
  }

  private void initRootLayout() throws IOException {
    // Load root layout from fxml file.
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(WeekReportMain.class.getResource("/fxml/RootLayout.fxml"));
    rootLayout = (BorderPane) loader.load();
    // Show the scene containing the root layout.
    Scene scene = new Scene(rootLayout);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  /**
   * Returns the main stage.
   * 
   * @return
   */
  public Stage getPrimaryStage() {
    return primaryStage;
  }
}
