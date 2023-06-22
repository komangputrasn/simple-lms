package com.project.uaspbo.mentorview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import com.project.uaspbo.Main;
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

// Class controller untuk menu login mentor
public class MentorLoginController implements Initializable {
    @FXML private TextField usernameTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button loginButton;
    @FXML private Button loginAsStudenet;
    @FXML private Text loginMessage;

    // Melakukan operasi inisialisasi
    // Saat halaman ini dimuat, pesan login yang memberikan infomasi ke user
    // ada masalah dalam login tidak akan muncul
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loginMessage.setVisible(false);
    }

    // Method action untuk tombol login
    @FXML
    public void loginButtonOnAction() throws IOException {
        // Jika semua field belum terisi, maka tampilkan pesan bahwa
        // user harus mengisi semua field
        if (!allFieldsAreFilled()) {
            loginMessage.setVisible(true);
            loginMessage.setText("Username and password must be filled!");
            return;
        }

        // Jika username dan password tidak sesuai dengan database,
        // tampilkan pesan ke user
        if (!validateLogin()) {
            loginMessage.setVisible(true);
            loginMessage.setText("Username and password does not match");
            return;
        }

        System.out.println("Login success!");
        // Menyimpan data username yang sedang login
        LoginData.setCurrentUsername(usernameTextField.getText());

        // Memuat halaman dashboard jika user berhasil login
        Parent root = FXMLLoader.load(getClass().getResource("dashboard.fxml"));
        Stage window = (Stage) loginButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method action untuk pergi ke halaman login
    public void gotoStudentLoginPage() throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("login.fxml"));
        Stage window = (Stage) loginButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method untuk memvalidasi data login
    private boolean validateLogin() {
        // Membangun koneksi dengan database
        DBConnection connection = new DBConnection();

        // Command SQL untuk mengambil password dari tabel 'mentordata' di database sesuai dengan username yang dimasukan oleh pengguna.
        String query = "SELECT password FROM mentordata WHERE username = '%s'".formatted(usernameTextField.getText());

        // Hasil pemanggilan getResultSetFromQuery disimpan dalam resultSet
        try (ResultSet resultSet = connection.getResultSetFromQuery(query)) {
            // Memeriksa apakah resultSet memiliki baris yang bisa dibaca atau tidak
            if (resultSet.next()) {
                // Jika memiliki baris yang bisa dibaca, password yang ada di simpan akan dibandingkan dengan password yang dimasukan oleh pengguna
                boolean isUsernamePasswordMatch = resultSet.getString(1).equals(passwordField.getText());
                connection.closeConnection();
                return isUsernamePasswordMatch;
            } else {
                // Jika tidak memiliki baris yang bisa dibaca, return false
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    // Method untuk memeriksa apakah semua field telah diisi user
    private boolean allFieldsAreFilled() {
        return !usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty();
    }
}
