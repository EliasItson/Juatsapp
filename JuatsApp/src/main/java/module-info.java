module com.bda.juatsapp {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.bda.juatsapp to javafx.fxml;
    exports com.bda.juatsapp;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires org.mongodb.driver.core;
}
