package com.project.uaspbo.mentorview;

import com.project.uaspbo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

// Class yang memuat komponen dari side panel
// Terdiri dari button-button navigasi utama seperti
// Your courses, assignments, quiz, grades, your profile, dan kembali
// ke laman login
public class SidePaneledScene {
    @FXML private Button yourCourseButton;
    @FXML private Button assignmentsButton;
    @FXML private Button quizButton;
    @FXML private Button gradesButton;
    @FXML private Button yourProfileButton;
    @FXML private ImageView gotoLoginPageButton;

    // Action untuk ke halaman 'your course'
    public void gotoYourCourses() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Action untuk ke halaman 'assignments'
    public void gotoAssignments() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("assignments.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Action untuk ke halaman 'quiz'
    public void gotoQuiz() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Action untuk ke halmaman 'grades'
    public void gotoGrades() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("grades.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Action untuk ke halaman 'your profile'
    public void gotoYourProfile() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("your profile.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Action untuk ke halaman 'logout'
    public void logoutButtonOnAction() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    public void gotoAboutUs() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("about us.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
