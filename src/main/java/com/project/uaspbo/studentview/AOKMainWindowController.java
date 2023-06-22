package com.project.uaspbo.studentview;

import com.project.uaspbo.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

// Sebuah class controller untuk tampilan course AOK
// Class ini merupakan turunan dari SidePaneledScene sehingga bisa menggunakan tombol-tombol yang berada dalam side-panel
// sebelah kiri
public class AOKMainWindowController extends SidePaneledScene {
    // Mendelarasi variabel komponen-komponen UI dalam halaman ini
    @FXML
    private Text fileMateri1Button;
    @FXML
    private Text fileMateri2Button;
    @FXML
    private Text fileMateri3Button;
    @FXML
    private Text fileMateri4Button;
    @FXML
    private Text quiz1Button;
    @FXML
    private Text quiz2Button;
    @FXML
    private Text quiz3Button;
    @FXML
    private Text quiz4Button;

    // Method untuk pergi ke halaman materi 1
    @FXML
    private void gotoMateri1OnAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AOK/materi/materi 1.fxml"));
        Stage window = (Stage) fileMateri1Button.getScene().getWindow();
        window.setScene(new Scene(root));
    }


    // Method untuk pergi ke halaman materi 2
    @FXML
    private void gotoMateri2OnAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AOK/materi/materi 2.fxml"));
        Stage window = (Stage) fileMateri1Button.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk pergi ke halaman materi 3
    @FXML
    private void gotoMateri3OnAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AOK/materi/materi 3.fxml"));
        Stage window = (Stage) fileMateri1Button.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    @FXML
    private void gotoMateri4OnAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AOK/materi/materi 4.fxml"));
        Stage window = (Stage) fileMateri1Button.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk pergi ke halaman kuis 1
    @FXML
    private void gotoQuiz1OnAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AOK/quiz/quiz 1.fxml"));
        Stage window = (Stage) fileMateri1Button.getScene().getWindow();
        window.setScene(new Scene(root));
    }
}
