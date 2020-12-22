module com.horia.reminder.api.client {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    requires java.net.http;

    requires com.fasterxml.jackson.databind;

    // opens com.horia.reminder.api.client to javafx.fxml;
    opens com.horia.reminder.api.client;
    exports com.horia.reminder.api.client;
}