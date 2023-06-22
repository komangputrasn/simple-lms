package com.project.uaspbo.studentview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

// Sebuah class controller untuk menu assignment 1
// Class ini merupakan turunan dari SidePaneledScene sehingga bisa menggunakan tombol-tombol yang berada dalam side-panel
// sebelah kiri
// Class ini mengimplementasikan interface initializable untuk melakukan inisialiasi saat class ini panggil
public class AOKAssignment1Controller extends SidePaneledScene implements Initializable {

    // Deklarasi variabel komponen dalam UI
    @FXML private Label assignment1Description;
    @FXML private Button submitAssignmentButton;
    @FXML private TextArea answersTextArea;

    // Method inisialiasi yang akan panggil saat menu 'Assignemnt 1' dimuat
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Membangun koneksi dengan database
        DBConnection connection = new DBConnection();

        // Variabel string query akan menyimpan statement SQL yang berfungsi mengambil deskripsi tugas dengan nama "assignment 1" dari tabel "aok_assignments_description" di databse
        String query = "SELECT assignment_description FROM aok_assignments_description WHERE assignment_name = 'assignment 1'";

        // Jika memiliki baris yang terbaca, isi komponen Text dengan materi yang didapatkan di database
        try (ResultSet resultSet = connection.getResultSetFromQuery(query)){
            if (resultSet.next()) {
                assignment1Description.setText(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Menutup koneksi databse
        connection.closeConnection();
    }

    // Method action yang berfungsi untuk mengirim tugas yang diberikan
    public void setSubmitAssignmentButtonOnAction() {
        // Memasikan apakah user sudah mengisi kolom teks
        if (answersTextArea.getText().isBlank()) {
            return;
        }

        // Melakukan koneksi ke database
        // Jika user sudah mengisi kolom teks, masukan jawabannya dalam tabel 'assignemnt_submission'
        DBConnection connection = new DBConnection();
        String insertCommand = "INSERT INTO assignments_submission (username, assignment_name, course_name, content) VALUES (?, 'assignment1', 'aok', ?)";
        try (PreparedStatement psInsert = connection.getConnection().prepareStatement(insertCommand)) {
            psInsert.setString(1, LoginData.getCurrentUsername());
            psInsert.setString(2, "Submission from " + LoginData.getCurrentUsername() + " - " +answersTextArea.getText());
            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Jika sudah menekan tombol submit, ganti warna,  non-aktifkan tombolnya dan ganti teksnya menjadi "Assignment Submited"
        submitAssignmentButton.setStyle("-fx-background-color: #1E2023");
        submitAssignmentButton.setText("Assignment submitted");
        submitAssignmentButton.setDisable(true);

        // Menutup koneksi databse
        connection.closeConnection();
    }
}
