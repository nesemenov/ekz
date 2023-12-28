module com.example.ekz {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.postgresql.jdbc;
    requires java.sql;


    opens com.example.ekz to javafx.fxml;
    exports com.example.ekz;


    opens com.example.ekz.Models to javafx.fxml;
    exports com.example.ekz.Models;

}