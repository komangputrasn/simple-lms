package com.project.uaspbo.studentview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

// Sebuah class controller untuk halaman course dashboard
// Method ini merupakan turunan dari class SidePaneledScene agar bisa menggunakan tombol side panel di samping
// Method ini juga mengimplementasikan interface Initializable untuk keperluan inisialiasi saat halaman ini dimuat
public class CourseDashboardController extends SidePaneledScene implements Initializable {
    // Deklarasi variabel komponen-komponen UI
    @FXML private Button AOKCourseButton;
    @FXML private Button DAACourseButton;
    @FXML private Button IMKCourseButton;
    @FXML private Button PBOCourseButton;
    @FXML private Text AOKCourseLabel;
    @FXML private Text DAACourseLabel;
    @FXML private Text IMKCourseLabel;
    @FXML private Text PBOCourseLabel;

    // Method inisialisasi utama yang fungsi untuk menentukan course apa saja yang bisa diakses user
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeAvailableCourses();
    }

    // Method ini berguna dalam menentukan course apa saja yang bisa diakses user berdasarkan pilihan yang dalam proses sign-up
    private void initializeAvailableCourses() {
        // Menyimpan course yang dipilih user dalam variabel selectedCourses
        String[] selectedCourses = getSelectedCourses();

        // Mencetak course yang dipilih
        for (String course : selectedCourses) {
            System.out.println(course);

            // Jika user memilih course AOK, aktifkan tombol AOK dan tampilkan pesan bahwa user bisa mengakses course tersebut
            if (course.equals("aok")) {
                AOKCourseButton.setText("Continue learning");
                AOKCourseLabel.setOpacity(1);
                AOKCourseButton.setOnAction(e -> gotoAOKCoursePage());
            }

            // Jika user memilih course AOK, aktifkan tombol DAA dan tampilkan pesan bahwa user bisa mengakses course tersebut
            if (course.equals("daa")) {
                DAACourseButton.setText("Continue learning");
                DAACourseLabel.setOpacity(1);
                DAACourseButton.setOnAction(e -> gotoDAACoursePage());
            }

            // Jika user memilih course IMK, aktifkan tombol DAA dan tampilkan pesan bahwa user bisa mengakses course tersebut
            if (course.equals("imk")) {
                IMKCourseButton.setText("Continue learning");
                IMKCourseLabel.setOpacity(1);
                IMKCourseButton.setOnAction(e -> gotoIMKCoursePage());
            }

            // Jika user memilih course PBO, aktifkan tombol DAA dan tampilkan pesan bahwa user bisa mengakses course tersebut
            if (course.equals("pbo")) {
                PBOCourseButton.setText("Continue learning");
                PBOCourseLabel.setOpacity(1);
                PBOCourseButton.setOnAction(e -> gotoPBOCoursePage());
            }
        }
    }

    // Mendapatkan array string yang berisi course apa saja yang pilih user
    private String[] getSelectedCourses() {
        DBConnection connection = new DBConnection();
        // Query ini mengambil daftar mata kuliah yang dipilih oleh mahasiswa dengan username yang sesuai dengan username yang sedang login saat ini.
        String query = String.format("SELECT `selected course` FROM studentdata WHERE username = '%s'", LoginData.getCurrentUsername());
        String[] selectedCourses = new String[2];
        try {
            // Pada setiap iterasi, daftar mata kuliah yang dipilih akan diambil dari kolom selected course pada baris tersebut,
            // dipecah menjadi array dengan memisahkan elemen-elemennya menggunakan karakter koma (,), dan disimpan ke dalam array
            ResultSet rSet = connection.getResultSetFromQuery(query);
            while (rSet.next()) {
                selectedCourses = rSet.getString("selected course").split(",");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi databse
            connection.closeConnection();
        }
        return selectedCourses;
    }

    // Method untuk pergi ke halaman course AOK
    @FXML
    private void gotoAOKCoursePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("AOK/main page.fxml"));
            Stage window = (Stage) AOKCourseButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method untuk pergi ke halaman course PBO
    @FXML
    private void gotoPBOCoursePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("PBO/main page.fxml"));
            Stage window = (Stage) AOKCourseButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method untuk pergi ke halaman course DAA
    @FXML
    private void gotoDAACoursePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("DAA/main page.fxml"));
            Stage window = (Stage) AOKCourseButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method untuk pergi ke halaman course IMK
    @FXML
    private void gotoIMKCoursePage() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("IMK/main page.fxml"));
            Stage window = (Stage) AOKCourseButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
