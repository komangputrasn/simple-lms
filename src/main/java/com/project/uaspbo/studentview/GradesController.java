package com.project.uaspbo.studentview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;


// Sebuah class controller untuk halaman grades
// Method ini merupakan turunan dari class SidePaneledScene agar bisa menggunakan tombol side panel di samping
// Method ini juga mengimplementasikan interface Initializable untuk keperluan inisialiasi saat halaman ini dimuat
public class GradesController extends SidePaneledScene implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        DBConnection connection = new DBConnection();
        try (ResultSet resultSet = connection.getResultSetFromQuery("SELECT a1 FROM aok_grade_table WHERE username = '%s'".formatted(LoginData.getCurrentUsername()))) {
            if (resultSet.next()) {
                AOKAssignment1GradeLabel.setText(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    int getUserGradeOnCourse(String course, String activityType) {
        DBConnection connection = new DBConnection();
        try (ResultSet resultSet = connection.getResultSetFromQuery("SELECT %s FROM %s_grade_table WHERE username = '%s'".formatted(activityType, course, LoginData.getCurrentUsername()))) {
            if (resultSet.next()) {
                AOKAssignment1GradeLabel.setText(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private ArrayList<String> getSelectedCourses() {
        DBConnection connection = new DBConnection();
        String query = String.format("SELECT `selected course` FROM studentdata WHERE username = '%s'", LoginData.getCurrentUsername());
        String[] selectedCourses = new String[2];

        try {
            ResultSet rSet = connection.getResultSetFromQuery(query);
            while (rSet.next()) {
                selectedCourses = rSet.getString("selected course").split(",");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }

        return new ArrayList<>(Arrays.asList(selectedCourses));
    }
    @FXML private VBox coursesGradesTiles;

    @FXML private Text AOKQuiz1GradeLabel;
    @FXML private Text AOKQuiz2GradeLabel;
    @FXML private Text AOKAssignment1GradeLabel;
    @FXML private Text AOKAssignment2GradeLabel;
    @FXML private Text AOKAssignment3GradeLabel;

    @FXML private Text IMKQuiz1GradeLabel;
    @FXML private Text IMKQuiz2GradeLabel;
    @FXML private Text IMKAssignment1GradeLabel;
    @FXML private Text IMKAssignment2GradeLabel;
    @FXML private Text IMKAssignment3GradeLabel;

    @FXML private Text DAAQuiz1GradeLabel;
    @FXML private Text DAAQuiz2GradeLabel;
    @FXML private Text DAAAssignment1GradeLabel;
    @FXML private Text DAAAssignment2GradeLabel;
    @FXML private Text DAAAssignment3GradeLabel;

    @FXML private Text PBOQuiz1GradeLabel;
    @FXML private Text PBOQuiz2GradeLabel;
    @FXML private Text PBOAssignment1GradeLabel;
    @FXML private Text PBOAssignment2GradeLabel;
    @FXML private Text PBOAssignment3GradeLabel;
}
