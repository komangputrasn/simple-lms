package com.project.uaspbo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController    {
    @FXML private TextField usernameTextField;
    @FXML private Text loginMessage;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button signupButton;
    @FXML private Button loginAsMentorButton;
    @FXML private Button loginAsAdminButton;

    // method yang akan dipanggil saat tombol login diklik
    public void loginButtonOnAction() throws IOException {
        String usernameInput, passwordInput;

        // mengekstrak usernmae dan password yang diinput user
        usernameInput = usernameTextField.getText();
        passwordInput = passwordField.getText();

        // periksa apakah user mengisi semua field
        if (usernameInput.isBlank() || passwordInput.isBlank()) {
            loginMessage.setText("Please enter your username and password!");
            return;
        }


        // melakukan validasi username dan password - apakah username dan password ditemukan di databse?
        boolean loginSuccess = validateLogin(usernameInput, passwordInput);
        if (loginSuccess) {
            // jika login sukses, pergi ke laman your course
            LoginData.setCurrentUsername(usernameInput);
            System.out.println("Login success!");
            Parent root = FXMLLoader.load(Main.class.getResource("studentview/your course.fxml"));
            Stage window = (Stage) loginButton.getScene().getWindow();
            window.setScene(new Scene(root));
        } else {
            // jika login gagal, tampilkan pesan error
            loginMessage.setText("Invalid login - username and password does not match");
        }
    }

    // fungsi validasi login, menerima username dan password sebagai inputnya
    private boolean validateLogin(String username, String password) {
        // melakukan koneksi ke database
        DBConnection connection = new DBConnection();

        // string ini adalah perintah / statement SQL yang berfungsi untuk mendapatkan username dan password di tabel student data
        String query = String.format("SELECT username, password FROM studentdata WHERE username = '%s'", username);
        try {
            ResultSet resultSet = connection.getResultSetFromQuery(query);
            // mengekseskusi perintah statement SQL
            // menyimpan hasil eksekusi perintah tersebut dalam variabel resultSet
            // jika hasilnya tidak kosong, periksa apakah password dan username sama
            if (resultSet.next()) {
                String passwordFromDb = resultSet.getString("password");

                // menutup koneksi databse - fungsinya menghemat memori
                connection.closeConnection();

                // mengembalikan hasil pemeriksaan - apakah user dan password dapat ditemukan dalam database?
                return passwordFromDb.equals(password);
            } else {
                connection.closeConnection();
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Terjadi error: ");
            e.printStackTrace();
        }

        connection.closeConnection();
        return false;
    }

    // Method untuk pindah ke halaman sign up
    public void switchToSignUp() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("signup.fxml"));
        Stage window = (Stage) signupButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk pindah ke halaman Login
    public void switchTOMentorLogin() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("mentorview/login.fxml"));
        Stage window = (Stage) signupButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

}
