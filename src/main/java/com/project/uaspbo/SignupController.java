package com.project.uaspbo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Controller untuk menu sign up user
public class SignupController {
    // Deklarasi variabel komponen-komponen halaman sign up
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button signupButton;
    @FXML private Button loginButton;
    @FXML private Text signupMessage;

    // Method yang akan dipanggil saat user menekan tombol login
    // Method user ke halaman login jika tidak ingin sign up
    public void loginButtonOnAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) signupButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Method ini akan dipanggil saat user menekan tombol sign up
    @FXML
    public void signupButtonOnAction() throws IOException {
        Student studentData = new Student(
                nameTextField.getText(),
                usernameTextField.getText(),
                emailTextField.getText(),
                passwordField.getText()
        );

        // Memeriksa apakah semua field telah diisi
        if (allFieldsAreFilled(studentData)) {
            signupMessage.setText("Please fill all fields!");
            return;
        }

        // Memeriksa apakah username dan email sudah digunakan atau belum
        if (!isUsernameEmailUsed(studentData.username(), studentData.email())) {
            signupMessage.setText("Username or email is used!");
            return;
        }

        // Memasukan data student ke dalam database
        insertStudentData(studentData);
        System.out.println("Sign in success!");

        // Membawa user ke halaman pemilihan course
        LoginData.setCurrentUsername(studentData.username());
        Parent root = FXMLLoader.load(getClass().getResource("choose course.fxml"));
        Stage window = (Stage) signupButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Memeriksa apakah semua field telah terisi
    private boolean allFieldsAreFilled(Student student) {
        return student.name().isBlank() && student.username().isBlank() && student.email().isBlank() && student.password().isBlank();
    }

    // Memasukan data student ke dalam databse
    private void insertStudentData(Student student) {
        DBConnection connection = new DBConnection();
        String insertCommand = "INSERT INTO studentdata (username, `password`, email, `name`) VALUES (?, ?, ?, ?)";

        try (PreparedStatement psInsert = connection.getConnection().prepareStatement(insertCommand)) {
            psInsert.setString(1, student.username());
            psInsert.setString(2, student.password());
            psInsert.setString(3, student.email());
            psInsert.setString(4, student.name());
            psInsert.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
    }

    // Method ini memeriksa apakah email sudah terdaftar dalam databse
    private boolean isUsernameEmailUsed(String username, String email) {
        DBConnection connection = new DBConnection();
        String checkCommand = "SELECT * FROM studentdata WHERE username = ? OR EMAIL = ?";

        try (PreparedStatement psCheck = connection.getConnection().prepareStatement(checkCommand)) {
            psCheck.setString(1, username);
            psCheck.setString(2, email);
            ResultSet resultSet = psCheck.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.closeConnection();
        }
        return true;
    }
}
