package com.project.uaspbo.mentorview;

import com.project.uaspbo.LoginData;
import com.project.uaspbo.studentview.SidePaneledScene;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AssignmentController extends SidePaneledScene implements Initializable {
    @FXML
    // Sebuah method untuk memuat halaman assignment
    private void switchToAssignment(String fxml) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(fxml + ".fxml"));
            Stage window = (Stage) AOKAssignment1Button.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method inisialisasi yang akan panggil saat halaman 'assignment' dimuat
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Ketiga tombol ini masing-masing berfungsi untuk memuat halaman assignment 1, 2, dan 3.
        AOKAssignment1Button.setOnAction(e -> switchToAssignment("AOK/assignments/assignment 1"));
        AOKAssignment2Button.setOnAction(e -> switchToAssignment("AOK/assignments/assignment 2"));
        AOKAssignment3Button.setOnAction(e -> switchToAssignment("AOK/assignments/assignment 3"));


        // Jika username yang sedang login adalah "fairo", maka tampilkan bagian assignment course "AOK"
        if (LoginData.getCurrentUsername().equals("fairo")) {
            coursesAssignmentGroups.getChildren().removeAll(DAACourseGroup, IMKCourseGroup, PBOCourseGroup);
        }
    }

    // Deklarasi variabel untuk komponen-kompenen dalam halaman ini
    @FXML private VBox coursesAssignmentGroups;

    @FXML private Group AOKCourseGroup;
    @FXML private Group DAACourseGroup;
    @FXML private Group IMKCourseGroup;
    @FXML private Group PBOCourseGroup;
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
