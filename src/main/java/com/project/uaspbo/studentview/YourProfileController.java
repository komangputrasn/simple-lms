package com.project.uaspbo.studentview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

// Class controller untuk halaman 'your profile'
// Method ini juga mengimplementasikan interface Initializable untuk keperluan inisialiasi saat halaman ini dimuat
public class YourProfileController extends SidePaneledScene {
    // Deklarasi variabel komponen dalam halaman ini
    @FXML private TextField nameTextField;
    @FXML private TextField usernameTextField;
    @FXML private TextField emailTextField;
    @FXML private PasswordField passwordField;
    @FXML private Button saveChangesButton;
    @FXML private Text saveChangesMessage;

    // Method action yang dipanggil saat user menekan tombol "save changes"
    public void saveChangesButtonOnAction() {
        // Memastikan apakah user sudah mengisi semua field
        if (!allFieldsAreFilled()) {
            saveChangesMessage.setText("Please fill all fields!");
            return;
        }

        // Periksa apakah usernam dan email yang dimasukan user sudah ada dalam databse
        if (!usernameEmailUnique()) {
            saveChangesMessage.setText("Username and email must be unique!");
            return;
        }

        // Method dibawah ini akan mengganti data username ke setiap tabel course yang dipilih
        changeUserDataOnAllCourse();

        // Mengganti data user dalam database
        changeUserData();

        // Mengganti data username yang sedang login ke data yang baru
        LoginData.setCurrentUsername(usernameTextField.getText());

        // Mengubah teks dalam tombol save changes
        saveChangesButton.setText("Change saved");

        // Mengganti warna tombol
        saveChangesButton.setStyle("-fx-background-color: #1E2023");

        // Menonaktifkan tombol
        saveChangesButton.setDisable(true);

        // Menghapus pesan error saat user sudah sukses melakukan perubahan data
        saveChangesMessage.setVisible(false);
    }


    // Method untuk merubah data user dalam databse
    private void changeUserData() {
        // Membangun koneksi ke database
        DBConnection connection = new DBConnection();

        // Command SQL untuk melakukan perubahan pada tabel 'studentdata'
        // Command ini memiliki 5 placeholder values
        String insertCommand = "UPDATE studentdata SET username = ?, password = ?, email = ?, name = ? WHERE username = ?";

        // Blok try-catch dibawah ini berguna untuk menangani kemungkinan terjadinya kesalahan saat melakukan update
        try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(insertCommand)) {
            // Mengisi 5 placeholder values dalam command sesuai dengan data yang telah diinput user
            psUpdate.setString(1, usernameTextField.getText());
            psUpdate.setString(2, passwordField.getText());
            psUpdate.setString(3, emailTextField.getText());
            psUpdate.setString(4, nameTextField.getText());
            psUpdate.setString(5, LoginData.getCurrentUsername());

            // Mengeksekusi command SQL
            psUpdate.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi database
            connection.closeConnection();
        }
    }

    // Method ini berguna untuk mengubah data user yang ada dalam table courses yang pilih user
    private void changeUserDataOnAllCourse() {
        // Menyimpan data mengenai course yang dipiih user
        String[] selectedCourses = getSelectedCourses();

        // Membangun koneksi ke databse
        DBConnection connection = new DBConnection();

        // Melakukan iterasi dalam setiap course yang dpilih user
        for (String course : selectedCourses) {
            // Command ini akan mengupdate data username dalam tabel course yang dipilih user
            // Command ini memiliki 2 placeholder value
            String updateQuery = String.format("UPDATE %s_grade_table SET username = ? WHERE username = ?", course);
            try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(updateQuery)) {
                // Mengisi placeholder value tersebut
                psUpdate.setString(1, usernameTextField.getText());
                psUpdate.setString(2, LoginData.getCurrentUsername());

                // Mengeksekusi command update SQL
                psUpdate.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Menutup koneksi databse
        connection.closeConnection();
    }

    // Fungsi ini bertujuan untuk mengambil daftar mcourse yang dipilih oleh user dari database
    private String[] getSelectedCourses() {
        // Membangun koneksi ke database
        DBConnection connection = new DBConnection();

        // Mengambil daftar mata kuliah yang dipilih oleh mahasiswa dengan username yang sesuai dengan username yang sedang login saat ini
        String query = String.format("SELECT `selected course` FROM studentdata WHERE username = '%s'", LoginData.getCurrentUsername());
        String[] selectedCourses = new String[2];

        try {
            ResultSet rSet = connection.getResultSetFromQuery(query);
            while (rSet.next()) {
                // Mengambil nilai kolom selected course dari objek ResultSet dan
                // Memisahkan nilainya menjadi beberapa elemen string yang dipisahkan oleh koma (,) dan menyimpan hasilnya pada array
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

    // Memeriksa apakah username dan email unique
    private boolean usernameEmailUnique() {
        // Kedua variabel ini berfungsi untuk menyimpan username dan email yang diinput user
        String usernameInput = usernameTextField.getText();
        String emailInput = emailTextField.getText();

        // Membangun koneksi ke databse
        DBConnection connection = new DBConnection();

        // Memeriksa apakah username dan email yang dimasukkan oleh pengguna sudah terdaftar di database atau belum.
        // Command SQL ini memiliki 2 placeholder value
        String checkCommand = "SELECT * FROM studentdata WHERE username = ? OR EMAIL = ?";
        try (PreparedStatement psCheck = connection.getConnection().prepareStatement(checkCommand)) {
            // Mengisi placeholder values pada query SELECT dengan nilai dari usernameInput dan emailInput yang telah didefinisikan sebelumnya
            psCheck.setString(1, usernameInput);
            psCheck.setString(2, emailInput);

            // Mengeksekusi command SQL
            ResultSet resultSet = psCheck.executeQuery();

            // Memeriksa apakah terdapat baris yang dapat dibaca dari objek ResultSet.
            // Jika terdapat, maka fungsi usernameEmailUnique akan mengembalikan nilai false
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Menutup koneksi database
        connection.closeConnection();
        return true;
    }

    // Memeriksa apakah semua field telah diisi user
    private boolean allFieldsAreFilled() {
        return !usernameTextField.getText().isEmpty() && !passwordField.getText().isEmpty() && !nameTextField.getText().isEmpty() && !emailTextField.getText().isEmpty();
    }
}
