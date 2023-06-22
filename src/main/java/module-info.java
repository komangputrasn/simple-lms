module com.project.uaspbo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.project.uaspbo to javafx.fxml;

    exports com.project.uaspbo;
    exports com.project.uaspbo.studentview;
    opens com.project.uaspbo.studentview to javafx.fxml;

    exports com.project.uaspbo.mentorview;
    opens com.project.uaspbo.mentorview to javafx.fxml;
}