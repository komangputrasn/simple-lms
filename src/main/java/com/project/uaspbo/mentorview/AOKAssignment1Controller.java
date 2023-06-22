package com.project.uaspbo.mentorview;

import com.project.uaspbo.DBConnection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AOKAssignment1Controller extends SidePaneledScene implements Initializable {
    @FXML private TextArea assignment1Description;
    @FXML private Button saveChangesButton;

    @FXML private Group editCourseGroup;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnection connection = new DBConnection();
        String query = "SELECT assignment_description FROM aok_assignments_description WHERE assignment_name = 'assignment 1'";
        try (ResultSet resultSet = connection.getResultSetFromQuery(query)){
            if (resultSet.next()) {
                assignment1Description.setText(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        connection.closeConnection();
    }

    public void setSaveChangesButton() {
        if (assignment1Description.getText().isEmpty()) {
            return;
        }

        DBConnection connection = new DBConnection();
        String updateCommand = "UPDATE aok_assignments_description SET assignment_description = ? WHERE assignment_name = 'assignment 1'";
        try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(updateCommand)) {
            psUpdate.setString(1, assignment1Description.getText());
            psUpdate.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        connection.closeConnection();
        saveChangesButton.setText("Changes saved");
        saveChangesButton.setStyle("-fx-background-color: #1E2023");
        saveChangesButton.setDisable(true);
    }

    public void gotoGradingPage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AOK/assignments/assignment 1 grading.fxml"));
            Stage window = (Stage) saveChangesButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
