package com.project.uaspbo.studentview;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

// Class controller untuk tampilan kuis kedua
public class AOKQuiz2Controller extends SidePaneledScene implements Initializable {

    // Inisialisasi variabel komponen dalam page Kuis 2
    @FXML private Button startQuizBtn;
    @FXML private ScrollPane quizScrollPane;

    // Method yang akan dipanggil saat user mengklik tombol mulai kuis
    public void onStartQuizAction() {
        // Menampikan soal kuis
        quizScrollPane.setVisible(true);

        // Merubah warna tombol kuis
        startQuizBtn.setStyle("-fx-background-color: #1E2023");

        // Menonaktifkan tombol kuis
        startQuizBtn.setDisable(true);

        // Mengganti teks yang terdaoat dalam tombol kuis
        startQuizBtn.setText("Quiz started");
        System.out.println("Quiz clicked");
    }

    // Melakukan inisialisasi tombol kuis
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        quizScrollPane.setVisible(false);
    }


    // Deklarasi komponen-komponen UI dalam scene ini
    @FXML public ToggleGroup soal1;
    @FXML public RadioButton pilihan1Soal1;
    @FXML public RadioButton pilihan2Soal1;
    @FXML public RadioButton pilihan3Soal1;
    @FXML public RadioButton pilihan4Soal1;

    @FXML public ToggleGroup soal2;
    @FXML public RadioButton pilihan1Soal2;
    @FXML public RadioButton pilihan2Soal2;
    @FXML public RadioButton pilihan3Soal2;
    @FXML public RadioButton pilihan4Soal2;

    @FXML public ToggleGroup soal3;
    @FXML public RadioButton pilihan1Soal3;
    @FXML public RadioButton pilihan2Soal3;
    @FXML public RadioButton pilihan3Soal3;
    @FXML public RadioButton pilihan4Soal3;

    @FXML public ToggleGroup soal4;
    @FXML public RadioButton pilihan1Soal4;
    @FXML public RadioButton pilihan2Soal4;
    @FXML public RadioButton pilihan3Soal4;
    @FXML public RadioButton pilihan4Soal4;

    @FXML public ToggleGroup soal5;
    @FXML public RadioButton pilihan1Soal5;
    @FXML public RadioButton pilihan2Soal5;
    @FXML public RadioButton pilihan3Soal5;
    @FXML public RadioButton pilihan4Soal5;
}
