package com.danimaniarqsoft.brain.controller;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.danimaniarqsoft.brain.pdes.exceptions.ReportException;
import com.danimaniarqsoft.brain.pdes.service.PersonalReportService;
import com.danimaniarqsoft.brain.pdes.service.context.ReportContext;
import com.danimaniarqsoft.brain.util.Constants;
import com.danimaniarqsoft.brain.util.ContextUtil;
import com.danimaniarqsoft.brain.util.PropertyFileUtils;
import com.danimaniarqsoft.brain.util.UrlPd;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class PdesReportController {
  private static final Logger LOGGER = LoggerFactory.getLogger(PdesReportController.class);

  @FXML
  private TextField           projectName;
  @FXML
  private TextField           port;
  @FXML
  private Label               messageLabel;
  @FXML
  private Button              btnReportId;
  @FXML
  private Button              btnSaveProperties;

  private UrlPd               urlPd;

  /**
   * Initializes the controller class. This method is automatically called after the fxml file has
   * been loaded.
   * 
   * @throws IOException
   * @throws ReportException
   */
  @FXML
  private void initialize() throws IOException, ReportException {
    urlPd = PropertyFileUtils.loadUrlContext();
    projectName.setText(urlPd.getProjectName());
    port.setText(urlPd.getPort());
    btnReportId.setOnAction((event) -> {
      Alert alert = new Alert(AlertType.INFORMATION);
      alert.setTitle("PDES Reporter");
      alert.setHeaderText("Reporte Semanal");
      alert.setContentText("Generando...");
      Task<Boolean> task = new Task<Boolean>() {
        @Override
        public Boolean call() {
          try {
            ReportContext context = new ReportContext();
            context.setUrlPd(urlPd);
            PersonalReportService.getInstance().createReport(context);
          } catch (Exception e) {
            ContextUtil.saveExceptionToDisk(e, Constants.FILE_ERROR_TXT, new File("./"));
          }
          LOGGER.info(
              "Thanks for using danimaniarqsoft solutions, visit my web page at www.danimanicp.com for further news");
          return true;
        }
      };
      task.setOnRunning((e) -> alert.show());
      task.setOnSucceeded((e) -> {
        alert.hide();
        alert.close();
      });
      task.setOnFailed((e) -> {
        alert.hide();
        alert.close();
      });
      new Thread(task).start();
      messageLabel.setText("¡Gracias por Utilizar Brain!");
    });
  }

  @FXML
  public void saveProperties(ActionEvent event) {
    Properties props = new Properties();
    props.setProperty(Constants.PROPERTY_PORT, port.getText());
    props.setProperty(Constants.PROPERTY_PROJECT, projectName.getText());
    PropertyFileUtils.saveProperties(props);
    messageLabel.setText("Datos Guardados!");
  }

  public static Popup createPopup(final String message) {
    final Popup popup = new Popup();
    popup.setAutoFix(true);
    popup.setAutoHide(true);
    popup.setHideOnEscape(true);
    Label label = new Label(message);
    label.setOnMouseReleased(new EventHandler<MouseEvent>() {
      @Override
      public void handle(MouseEvent e) {
        popup.hide();
      }
    });
    label.getStyleClass().add("popup");
    popup.getContent().add(label);
    return popup;
  }

  public static void showPopupMessage(final String message, final Stage stage) {
    final Popup popup = createPopup(message);
    popup.setOnShown(new EventHandler<WindowEvent>() {
      @Override
      public void handle(WindowEvent e) {
        popup.setX(stage.getX() + stage.getWidth() / 2 - popup.getWidth() / 2);
        popup.setY(stage.getY() + stage.getHeight() / 2 - popup.getHeight() / 2);
      }
    });
    popup.show(stage);
  }
}
