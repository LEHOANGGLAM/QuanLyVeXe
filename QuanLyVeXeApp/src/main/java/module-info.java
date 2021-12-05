module com.mycompany.quanlyvexeapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.quanlyvexeapp to javafx.fxml;
    exports com.mycompany.quanlyvexeapp;
    exports com.mycompany.pojo;
}
