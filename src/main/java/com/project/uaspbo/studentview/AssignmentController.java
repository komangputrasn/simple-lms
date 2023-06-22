package com.project.uaspbo.studentview;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

// Sebuah class controller untuk halaman assignment
// Class ini merupakan turunan dari SidePaneledScene sehingga bisa menggunakan tombol-tombol yang berada dalam side-panel
// sebelah kiri
// Method ini mengimplementasi interface Initializable untuk melakukan inisialasi action tombol
public class AssignmentController extends SidePaneledScene implements Initializable {

    // Method helper untuk pindah ke assignment
    @FXML
    private void switchToAssignment(String fxml) {
        try {
            // Memuat file FXML assignment yang diberikan dalam parameter fungsi
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Stage window = (Stage) AOKAssignment1Button.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method inisialisasi untuk memberikan setiap tombol dalam scene ini sesuai dengan aksinya
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AOKAssignment1Button.setOnAction(e -> switchToAssignment("AOK/assignments/assignment 1"));
        AOKAssignment2Button.setOnAction(e -> switchToAssignment("AOK/assignments/assignment 2"));
        AOKAssignment3Button.setOnAction(e -> switchToAssignment("AOK/assignments/assignment 3"));
        PBOAssignment1Button.setOnAction(e -> switchToAssignment("PBO/assignments/assignment 1"));
        PBOAssignment2Button.setOnAction(e -> switchToAssignment("PBO/assignments/assignment 2"));
        PBOAssignment3Button.setOnAction(e -> switchToAssignment("PBO/assignments/assignment 3"));
    }

    // Deklarasi komponen-komponen tombol
    @FXML private Button AOKAssignment1Button;
    @FXML private Button AOKAssignment2Button;
    @FXML private Button AOKAssignment3Button;
    @FXML private Button DAAAssignment1Button;
    @FXML private Button DAAAssignment2Button;
    @FXML private Button DAAAssignment3Button;
    @FXML private Button IMKAssignment1Button;
    @FXML private Button IMKAssignment2Button;
    @FXML private Button IMKAssignment3Button;
    @FXML private Button PBOAssignment1Button;
    @FXML private Button PBOAssignment2Button;
    @FXML private Button PBOAssignment3Button;

}
