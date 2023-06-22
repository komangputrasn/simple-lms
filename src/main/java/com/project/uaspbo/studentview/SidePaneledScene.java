package com.project.uaspbo.studentview;

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

// Sebuah class yang menampung elemen elemen yang terdapat dari side panel
public class SidePaneledScene {
    // Deklarasi variabel komponen komponen interface
    @FXML private Button yourCourseButton;
    @FXML private Button assignmentsButton;
    @FXML private Button quizButton;
    @FXML private Button gradesButton;
    @FXML private Button yourProfileButton;
    @FXML private ImageView gotoLoginPageButton;
    @FXML private Text aboutUsButton;

    // Method untuk pergi ke dalam halaman your course
    public void gotoYourCourses() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("your course.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    // Method untuk pergi ke dalam halaman your assignments
    public void gotoAssignments() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("assignments.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk pergi ke dalam halaman kuis
    public void gotoQuiz() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("quiz.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    // Method untuk pergi ke dalam halaman grades
    public void gotoGrades() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("grades.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
    // Method untuk pergi ke dalam halaman profile
    public void gotoYourProfile() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("your profile.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk pergi ke dalam halaman about us
    public void gotoAboutUs() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("about us.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk pergi ke dalam halaman log out
    public void logoutButtonOnAction() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        Stage window = (Stage) yourCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
