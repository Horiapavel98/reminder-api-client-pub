package com.horia.reminder.api.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;

public class PrimaryController {

    /* Reminder data */
    public TextField toField;
    public TextField titleField;
    public TextArea descriptionField;

    /* Time */
    public DatePicker dateField;
    public TextField timeField;

    /* Buttons */
    public Button sendButton;
    public ToggleButton localhostButton;

    /* Invalid flags */
    public Text invalidTo;
    public Text invalidTitle;
    public Text invalidDescription;
    public Text invalidTimestamp;
    public TextField tunnelField;

    @FXML
        public void initialize() {
            invalidTo.setText("");
            invalidTitle.setText("");
            invalidDescription.setText("");
            invalidTimestamp.setText("");

            sendButton.setDisable(false);
        }

    @FXML
    public void localhostButtonSet(ActionEvent actionEvent) {
        if (!localhostButton.isSelected()) {
            tunnelField.setDisable(false);
        } else {
            tunnelField.setDisable(true);
        }
    }

    @FXML
    public void sendButtonClick(ActionEvent actionEvent) {

        if (FieldValidator.validatePhoneField(toField.getText())) {
            invalidTo.setText("Correct!");
            invalidTo.setFill(Color.GREEN);
        } else {
            invalidTo.setText("Invalid");
            invalidTo.setFill(Color.RED);
            return;
        }

        if (FieldValidator.validateTitleField(titleField.getText())) {
            invalidTitle.setText("Correct!");
            invalidTitle.setFill(Color.GREEN);
        } else {
            invalidTitle.setText("Invalid");
            invalidTitle.setFill(Color.RED);
            return;
        }

        if (FieldValidator.validateDescriptionField(descriptionField.getText())) {
            invalidDescription.setText("Correct!");
            invalidDescription.setFill(Color.GREEN);
        } else {
            invalidDescription.setText("Invalid");
            invalidDescription.setFill(Color.RED);
            return;
        }

        if (FieldValidator.validateDueDateAndTime(dateField.getValue(), timeField.getText())) {
            invalidTimestamp.setText("Correct!");
            invalidTimestamp.setFill(Color.GREEN);
        }else {
            invalidTimestamp.setText("Invalid");
            invalidTimestamp.setFill(Color.RED);
            return;
        }

        invalidTo.setText("");
        invalidTitle.setText("");
        invalidTimestamp.setText("");
        invalidDescription.setText("");

        // Create reminder from input data
        Reminder reminderToSend = new Reminder(
                titleField.getText(),
                descriptionField.getText(),
                dateField.getValue().toString() + "T" + timeField.getText(),
                toField.getText()
           );

        System.out.println(reminderToSend);

        String endpoint = localhostButton.isSelected() ? "http://localhost:8080" : tunnelField.getText();
        System.out.println(endpoint);

        try {
            int responseStatusCode = RequestBuilder.postNewReminder(reminderToSend, endpoint);
            if (responseStatusCode == 200) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Reminder sent ...");
                alert.setHeaderText(null);
                alert.setContentText("Your reminder has been set to " + dateField.getValue() + " at " + timeField.getText());

                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Reminder has not been sent ...");
                alert.setHeaderText("Internal server error");
                alert.setContentText(null);

                alert.showAndWait();
            }

        } catch (IOException | InterruptedException | URISyntaxException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Reminder has not been sent ...");
            alert.setHeaderText("Internal server error");
            alert.setContentText(null);

            alert.showAndWait();
            e.printStackTrace();
        }
    }
}
