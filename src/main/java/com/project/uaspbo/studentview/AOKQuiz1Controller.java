package com.project.uaspbo.studentview;

import com.project.uaspbo.DBConnection;
import com.project.uaspbo.LoginData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

// Sebuah class controller untuk halaman kuis 1
// Class ini merupakan turunan dari SidePaneledScene sehingga bisa menggunakan tombol-tombol yang berada dalam side-panel
// sebelah kiri
public class AOKQuiz1Controller extends SidePaneledScene implements Initializable {
    @FXML private Button startQuizButton;
    @FXML private ScrollPane quizScrollPane;
    @FXML private Button submitQuizButton;

    // Method yang melakukan action (tindakan) jika user mengklik tombol submit
    @FXML
    private void submitQuizButtonOnAction() {
        // Memastikan apakah user telah mengisi semua jawaban
        if ((soal1.getSelectedToggle() == null) || (soal2.getSelectedToggle() == null) ||
            (soal3.getSelectedToggle() == null) || (soal4.getSelectedToggle() == null) ||
            (soal5.getSelectedToggle() == null)) {
            return;
        }

        // Mengirim data nilai ke database
        // Jika sudah menglkik tombol, ganti warna tombol dan nonaktifkan tombolnya
        sendGradesDataToDatabase();
        submitQuizButton.setStyle("-fx-background-color: #1E2023");
        submitQuizButton.setDisable(true);
        submitQuizButton.setText("Submitted");
    }

    // Method untuk mengirim nilai ke databse
    private void sendGradesDataToDatabase() {
        int grades = getUserGrade();
        // Membangun koneksi ke databse
        DBConnection connection = new DBConnection();

        // Command SQL untuk mengupdate data nilai matakuliah AOK
        // Command ini memiliki 2 placeholder value
        String updateQuery = "UPDATE aok_grade_table SET q1 = ? WHERE username = ?";
        try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(updateQuery)) {
            // Mengisi placeholder values tersebut
            // Placeholder pertama berisi nilai dari user
            // Placeholder kedua berisi data username yang sedang login
            psUpdate.setString(1, String.valueOf(grades));
            psUpdate.setString(2, LoginData.getCurrentUsername());

            // Mengeksekusi command SQL
            psUpdate.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi databse
            connection.closeConnection();
        }
    }

    // Method untuk mengkalkukasi nilai kuis user
    private int getUserGrade() {
        int grade = 0;
        // Menyimpan jawaban user dan jawaban yang benar
        ArrayList<String> correctAnswers = getQuizCorrectAnswers();
        ArrayList<String> userAnswers = getUserAnswers();

        // Melakukan pemeriksaan antara jawaban user dengan jawaban yang sebenarnya
        for (int i = 0; i < correctAnswers.size(); i++) {
            if (correctAnswers.get(i).equals(userAnswers.get(i))) {
                grade += 20;
            }
        }

        // Mencetak nilai user ke konsol
        System.out.println("Grade: " + grade);
        return grade;
    }

    // Method untuk mendapatkan jawaban user
    private ArrayList<String> getUserAnswers() {
        ArrayList<String> userAnswers = new ArrayList<>();

        // Memasukan data jawaban user ke dalam ArrayList userAnsers
        userAnswers.add(((RadioButton)soal1.getSelectedToggle()).getText());
        userAnswers.add(((RadioButton)soal2.getSelectedToggle()).getText());
        userAnswers.add(((RadioButton)soal3.getSelectedToggle()).getText());
        userAnswers.add(((RadioButton)soal4.getSelectedToggle()).getText());
        userAnswers.add(((RadioButton)soal5.getSelectedToggle()).getText());

        // Mencetak jawaban user ke konsol
        System.out.println("User answers: ");
        for (var i : userAnswers) {
            System.out.println(i);
        }

        // Mengembalikan jawaban user
        return userAnswers;
    }

    // Method untuk mendapaktan jawaban kuis yang benar
    private ArrayList<String> getQuizCorrectAnswers() {
        // Mendeklarasi variabel correctAnswersKey yang mengandung jawaban yang benar
        ArrayList<String> correctAnswersKey = new ArrayList<>();

        // Membangun koneksi ke database
        DBConnection connection = new DBConnection();
        try {
            // Menyimpan hasil query dalam variabel resultSet
            // Query ini berfungsi untuk mendapatkan 5 answers dari tabel aok_quiz_data
            ResultSet resultSet = connection.getResultSetFromQuery("SELECT answer FROM aok_quiz_data LIMIT 0, 5");

            // Jika terdapat kolom yang dibaca, tambahkan answers ke dalam array list correctAnswerKey
            while (resultSet.next()) {
                correctAnswersKey.add(resultSet.getString(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Menampilkan data jawaban benar ke konsol
        System.out.println("Correct answers: ");
        for (var i : correctAnswersKey) {
            System.out.println(i);
        }

        // Menutup koneksi database
        connection.closeConnection();
        return correctAnswersKey;
    }

    // Method action jika tombol kuis dilkik
    public void onStartQuizAction() {
        // Mengganti warna tombol
        startQuizButton.setStyle("-fx-background-color: #1E2023");

        // Menonaktifkan tombol
        startQuizButton.setDisable(true);

        // Mengganti teks dalam tombol kuis
        startQuizButton.setText("Quiz started");

        // Menampilkan ke konsol jika user mengklik tombol
        System.out.println("Quiz clicked");

        // Menampilkan soal kuis
        quizScrollPane.setVisible(true);
    }

    // Method inisialisasi
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Menyembunyikan soal kuis jika user belum mengklik tombol kuis
        quizScrollPane.setVisible(false);

        // Menginisialisasi jawaban kuis
        initializeAllQuizDecriptionAnswers();
    }

    // Mendapatkan deskripksi kuis dari database
    private String getQuizDescription(String quizNo) {
        // Membangun koneksi database
        DBConnection connection = new DBConnection();

        // Menyimpan string deskripsi yang didapatkan dari database
        String description = "";

        // Query ini mencari deskripsi kuis dengaan nomor yang sesuai dengan paramater yang diberikan dalam method ini dan
        // menyimpan hasilnya dalam variabel resultSet
        try (ResultSet resultSet = connection.getResultSetFromQuery("SELECT description from aok_quiz_data WHERE quiz_no = '%s'".formatted(quizNo))) {
            // Jika memiliki kolom yang dibaca, simpah dalam variabel description
            if (resultSet.next()) {
                description = resultSet.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi databse
            connection.closeConnection();
        }

        // Mengembalikan string deskripsi
        return description;
    }

    // Mengambil pilihan soal kuis berdasarkan nomor kuis dan pilihan kuis yang diberikan
    private String getQuizChoices(String quizNo, int choiceNo) {
        // Membangun koneksi databse
        DBConnection connection = new DBConnection();

        // Menyimpan deskripsi kuis
        String description = "";

        // Query ini mencari deskripsi kuis dengan nomor yang sesuai dengan parameter yang diberikan ke method ini
        try (ResultSet resultSet = connection.getResultSetFromQuery("select a1, a2, a3, a4 from aok_quiz_data where quiz_no = '%s'".formatted(quizNo))) {
            // Memeriksa apakah terdapat baris hasil yang dikembalikan dari query select tersebut.
            if (resultSet.next()) {
                // Jika terdapat baris hasil, maka deskripsi quiz akan diambil dari kolom description pada baris tersebut dan disimpan ke dalam variabel description.
                description = resultSet.getString(choiceNo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Menutup koneksi databse
            connection.closeConnection();
        }
        return description;
    }

    // Method inisialiasi yang berfungsi untuk mengisi soal dan jawaban kuis di setiap nomor
    private void initializeAllQuizDecriptionAnswers() {
        initializeSoalDescriptionAnswers("1a");
        initializeSoalDescriptionAnswers("1b");
        initializeSoalDescriptionAnswers("1c");
        initializeSoalDescriptionAnswers("1d");
        initializeSoalDescriptionAnswers("1e");
    }

    // Method untuk melakukan inisialasi dan jawaban kuis pada satu nomor
    private void initializeSoalDescriptionAnswers(String quizNo) {
        soal1Description.setText(getQuizDescription(quizNo));
        pilihan1Soal1.setText(getQuizChoices(quizNo, 1));
        pilihan1Soal2.setText(getQuizChoices(quizNo, 2));
        pilihan1Soal3.setText(getQuizChoices(quizNo, 3));
        pilihan1Soal4.setText(getQuizChoices(quizNo, 4));
    }


    @FXML public Text soal1Description;
    @FXML public Text soal2Description;
    @FXML public Text soal3Description;
    @FXML public Text soal4Description;
    @FXML public Text soal5Description;

    @FXML public ToggleGroup soal1;
    @FXML public RadioButton pilihan1Soal1;
    @FXML public RadioButton pilihan2Soal1;
    @FXML public RadioButton pilihan3Soal1;
    @FXML public RadioButton pilihan4Soal1;

    @FXML public ToggleGroup soal2;
    @FXML public RadioButton pilihan1Soal2;
    @FXML public RadioButton pilihan2Soal2;
    @FXML public RadioButton pilihan3Soal2;
    @FXML public RadioButton pilihan4Soal2;

    @FXML public ToggleGroup soal3;
    @FXML public RadioButton pilihan1Soal3;
    @FXML public RadioButton pilihan2Soal3;
    @FXML public RadioButton pilihan3Soal3;
    @FXML public RadioButton pilihan4Soal3;

    @FXML public ToggleGroup soal4;
    @FXML public RadioButton pilihan1Soal4;
    @FXML public RadioButton pilihan2Soal4;
    @FXML public RadioButton pilihan3Soal4;
    @FXML public RadioButton pilihan4Soal4;

    @FXML public ToggleGroup soal5;
    @FXML public RadioButton pilihan1Soal5;
    @FXML public RadioButton pilihan2Soal5;
    @FXML public RadioButton pilihan3Soal5;
    @FXML public RadioButton pilihan4Soal5;

}


