package com.project.uaspbo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Sebuah class controller untuk halaman pemilihan course saat user melakukan sign-up
public class ChooseCourseController {
    // Konstanta yang menyatakan bahwa jumlah course maksimum yang dipilih user hanya 2 saja
    public final int MAX_STUDENT_COURSE_COUNT = 2;

    // Mendeklarasi variabel-variabel komponen dalam halaman choose course
    @FXML private CheckBox checkBoxAOK;
    @FXML private CheckBox checkBoxIMK;
    @FXML private CheckBox checkBoxDAA;
    @FXML private CheckBox checkBoxPBO;
    @FXML private Button selectCoursesButton;
    @FXML private Text selectCourseInfo;
    @FXML private Button cancelButton;

    // Method ini akan dipanggil saat user membatalkan pilihan course
    public void cancelButtonOnAction() throws IOException {
        removeCurrentUserFromDatabase();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage window = (Stage) cancelButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Menghapus data user dalam databsee jika membatalkan pemilihan course
    private void removeCurrentUserFromDatabase() {
        // Membangun koneksi databse
        DBConnection connection = new DBConnection();

        // Menghapus data pengguna yang sudah didaftar sebelumnya
        // Command ini berisi 1 placeholder value yang akan diisi berikutnya
        String deleteCommand = "DELETE FROM studentdata WHERE username = ?";

        //
        try (PreparedStatement psDelete = connection.getConnection().prepareStatement(deleteCommand)) {
            // Mengisi placeholder value pada query DELETE dengan username dari pengguna yang sudah terdaftar sebelumnya
            psDelete.setString(1, LoginData.getCurrentUsername());
            psDelete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi
            connection.closeConnection();
        }
    }

    // Method yang akan dipanggil saat user mengklik tombol 'select courses'
    public void selectCoursesButtonOnAction() throws IOException {
        // Memastikan bahwa user harus memilih tepat 2 course
        if (getNumberOfSelectedCourses() != MAX_STUDENT_COURSE_COUNT) {
            selectCourseInfo.setText("Error - please select two course, not " + getNumberOfSelectedCourses() + " courses");
            return;
        }

        // Jika user memilih tepat 2 course, masukan data user kedalam databse kemudian pergi ke halaman your course
        insertSelectedCourseToDatabase();
        insertSelectedCourseToMentorDatabase();
        gotoYourCourseDashboard();
    }

    // Method untuk pergi ke halaman your course
    private void gotoYourCourseDashboard() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("studentview/your course.fxml"));
        Stage window = (Stage) selectCoursesButton.getScene().getWindow();
        window.setScene(new Scene(root));
    }

    // Masukan data user ke setiap database
    private void insertSelectedCourseToDatabase() {
        String[] selectedCoursesList = getSelectedCourses();

        // Menggunakan StringBuilder sebagai alternatif dari konkatenasi string dalam looping
        StringBuilder concatenatedCourseList = new StringBuilder();

        for (int i = 0; i < MAX_STUDENT_COURSE_COUNT; ++i) {
            if (i == MAX_STUDENT_COURSE_COUNT - 1) {
                concatenatedCourseList.append(selectedCoursesList[i]);
            } else {
                concatenatedCourseList.append(selectedCoursesList[i]).append(",");
            }
        }

        // Mengubah data StringBuilder menjadi String
        String finalConcatenatedCourseList = concatenatedCourseList.toString();

        // Membangun koneksi database
        DBConnection connection = new DBConnection();

        // Query untuk mengupdate data course yang dipilih user
        // Query ini meiliki 2 placeholder value
        String insertCommand = "UPDATE studentdata SET `selected course` = ? WHERE username = ?";

        try (PreparedStatement psInsert = connection.getConnection().prepareStatement(insertCommand)) {
            // Mengisi placeholder value tersebut dengan course yang sudah dipilih user dan data usernmae yang sedang login
            psInsert.setString(1, finalConcatenatedCourseList);
            psInsert.setString(2, LoginData.getCurrentUsername());

            // Mengeksekusi command SQL
            psInsert.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi databse
            connection.closeConnection();
        }
    }

    // Method ini berguna untuk memasukan data user ke dalam databse mentor
    private void insertSelectedCourseToMentorDatabase() {
        // Meminta data course yang dipilih user
        String[] selectedCoursesList = getSelectedCourses();

        // Membangun koneksi ke databse
        DBConnection connection = new DBConnection();

        // Melakukan iterasi untuk semua course yang dipilih user
        for (String course : selectedCoursesList) {
            // Command SQL untuk memasukan data username ke dalam tabel masing-masing course
            String insertCommand = String.format("INSERT INTO  %s_grade_table (username) VALUES (?)", course);
            try (PreparedStatement psInsert = connection.getConnection().prepareStatement(insertCommand)) {
                psInsert.setString(1, LoginData.getCurrentUsername());
                psInsert.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
                return;
            }
        }

        // Menutup koneksi
        connection.closeConnection();
    }

    // Sebuah method untuk mendapatkan course yang pilih oleh user
    private String[] getSelectedCourses() {
        List<String> courses = new ArrayList<>();

        // Jika user memilih course AOK, tambahkan ke dalam list course yang dipilih
        if (checkBoxAOK.isSelected()) {
            courses.add("aok");
        }

        // Jika user memilih course IMK, tambahkan ke dalam list course yang dipilih
        if (checkBoxIMK.isSelected()) {
            courses.add("imk");
        }
        // Jika user memilih course DAA, tambahkan ke dalam list course yang dipilih
        if (checkBoxDAA.isSelected()) {
            courses.add("daa");
        }

        // Jika user memilih course PBO, tambahkan ke dalam list course yang dipilih
        if (checkBoxPBO.isSelected()) {
            courses.add("pbo");
        }

        // Mencetak semua course yang dipilih ke konsol
        for (var i : courses) {
            System.out.println(i);
        }

        // Mengkonversikan ArrayList menjadi array biasa
        return courses.toArray(new String[0]);
    }


    // Memberikan jumlah course yang dipilih user/
    private int getNumberOfSelectedCourses() {
        return (checkBoxAOK.isSelected() ? 1 : 0) + (checkBoxIMK.isSelected() ? 1 : 0) + (checkBoxDAA.isSelected() ? 1 : 0) + (checkBoxPBO.isSelected() ? 1 : 0);
    }

}
