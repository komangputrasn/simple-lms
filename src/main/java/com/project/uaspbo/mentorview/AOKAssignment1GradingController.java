package com.project.uaspbo.mentorview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AOKAssignment1GradingController extends SidePaneledScene implements Initializable {
    @FXML private TextArea studentSubmissionTextArea;
    @FXML private TextField findStudentTextField;
    @FXML private TextField gradeStudentTextField;
    @FXML private Button gradeStudentButton;
    @FXML private Text gradingMessage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        studentSubmissionTextArea.setEditable(false);
        gradingMessage.setText("");

        DBConnection connection = new DBConnection();

        String submission = "";
        try (ResultSet resultSet = connection.getResultSetFromQuery("SELECT content FROM assignments_submission WHERE assignment_name = 'assignment1'")) {
            while (resultSet.next()) {
                submission += resultSet.getString(1) + "\n\n";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentSubmissionTextArea.setText(submission);
    }

    public void gradeStudentButtonOnAction() {
        DBConnection connection = new DBConnection();

        if (findStudentTextField.getText().isBlank() || gradeStudentTextField.getText().isBlank()) {
            return;
        }

        String query = "SELECT username FROM aok_grade_table WHERE username = '%s'".formatted(findStudentTextField.getText());
        try (ResultSet resultSet = connection.getResultSetFromQuery(query)) {
            if (resultSet.next()) {
                gradeStudent();
                gradeStudentButton.setText("Assignment graded");
                gradeStudentButton.setDisable(true);
                gradeStudentButton.setStyle("-fx-background-color: #1E2023");
            } else {
                gradingMessage.setText("User not found!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void gradeStudent() {
        String updateCommand = "UPDATE aok_grade_table SET a1 = ? WHERE username = ?";
        DBConnection connection = new DBConnection();

        try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(updateCommand)) {
            psUpdate.setString(1, gradeStudentTextField.getText());
            psUpdate.setString(2, findStudentTextField.getText());
            psUpdate.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }


    }
}
