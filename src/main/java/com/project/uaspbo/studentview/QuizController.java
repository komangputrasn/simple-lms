package com.project.uaspbo.studentview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

// Method class controller untuk halaman kuis
// Method ini merupakan turunan dari class SidePaneledScene agar bisa menggunakan tombol side panel di samping
// Method ini juga mengimplementasikan interface Initializable untuk keperluan inisialiasi saat halaman ini dimuat
public class QuizController extends SidePaneledScene implements Initializable {
    // Method ini menginisialisasi semua tombol-tombol berserta aksinya
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AOKQuiz1Button.setOnAction(e -> switchToAQuiz("AOK/quiz/quiz 1"));
        AOKQuiz2Button.setOnAction(e -> switchToAQuiz("AOK/quiz/quiz 2"));
        PBOQuiz2Button.setOnAction(e -> switchToAQuiz("PBO/quiz/quiz 1"));
        PBOQuiz2Button.setOnAction(e -> switchToAQuiz("PBO/quiz/quiz 2"));
    }

    // Method helper ini berguna untuk memuat halaman kuis berdasarkan paramater dalam method ini
    private void switchToAQuiz(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Stage window = (Stage) AOKQuiz1Button.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Deklarasi variabel variabel komponen tombol
    @FXML private Button AOKQuiz1Button;
    @FXML private Button AOKQuiz2Button;
    @FXML private Button IMKQuiz1Button;
    @FXML private Button IMKQuiz2Button;
    @FXML private Button DAAQuiz1Button;
    @FXML private Button DAAQuiz2Button;
    @FXML private Button PBOQuiz1Button;
    @FXML private Button PBOQuiz2Button;


}
