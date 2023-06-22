package com.project.uaspbo.mentorview;

import com.project.uaspbo.DBConnection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

import java.sql.PreparedStatement;

public class AOKQuiz1Controller {
    private void setSaveChangesButtonOnAction() {
        updateSoalDescription("1a", deskripsiSoal1.getText());
        updateSoalDescription("1b", deskripsiSoal2.getText());
        updateSoalDescription("1c", deskripsiSoal3.getText());
        updateSoalDescription("1d", deskripsiSoal4.getText());
        updateSoalDescription("1e", deskripsiSoal5.getText());

        updateSoal("1a", jawaban1Soal1.getText(), "a1");
        updateSoal("1a", jawaban2Soal1.getText(), "a2");
        updateSoal("1a", jawaban3Soal1.getText(), "a3");
        updateSoal("1a", jawaban4Soal1.getText(), "a4");

        updateSoal("1b", jawaban1Soal2.getText(), "a1");
        updateSoal("1b", jawaban2Soal2.getText(), "a2");
        updateSoal("1b", jawaban3Soal2.getText(), "a3");
        updateSoal("1b", jawaban4Soal2.getText(), "a4");

        updateSoal("1c", jawaban1Soal3.getText(), "ad");
        updateSoal("1c", jawaban2Soal3.getText(), "a2");
        updateSoal("1c", jawaban3Soal3.getText(), "a3");
        updateSoal("1c", jawaban4Soal3.getText(), "a4");

        updateSoal("1d", jawaban1Soal4.getText(), "a1");
        updateSoal("1d", jawaban2Soal4.getText(), "a2");
        updateSoal("1d", jawaban3Soal4.getText(), "a3");
        updateSoal("1d", jawaban4Soal4.getText(), "a4");

        updateSoal("1e", jawaban1Soal5.getText(), "a1");
        updateSoal("1e", jawaban2Soal5.getText(), "a2");
        updateSoal("1e", jawaban3Soal5.getText(), "a3");
        updateSoal("1e", jawaban4Soal5.getText(), "a4");

        saveChangesButton.setText("Changes saved");
        saveChangesButton.setStyle("-fx-background-color: #1E2023");
        saveChangesButton.setDisable(true);
    }

    private void updateSoalDescription(String kodeSoal, String text) {
        DBConnection connection = new DBConnection();
        String updateQuery = "UPDATE aok_quiz_data SET description = ? WHERE quiz_no = ?";
        try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(updateQuery)) {
            psUpdate.setString(1, text);
            psUpdate.setString(2, kodeSoal);
            psUpdate.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.closeConnection();
    }

    private void updateSoal(String kodeSoal, String text, String opsi) {
        DBConnection connection = new DBConnection();
        String updateQuery = "UPDATE aok_quiz_data SET ? = ? WHERE quiz_no = ?";
        try (PreparedStatement psUpdate = connection.getConnection().prepareStatement(updateQuery)) {
            psUpdate.setString(1, opsi);
            psUpdate.setString(2, text);
            psUpdate.setString(2, kodeSoal);
            psUpdate.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        connection.closeConnection();
    }

    @FXML private Button saveChangesButton;
    @FXML private TextField deskripsiSoal1;
    @FXML private TextField deskripsiSoal2;
    @FXML private TextField deskripsiSoal3;
    @FXML private TextField deskripsiSoal4;
    @FXML private TextField deskripsiSoal5;

    @FXML private TextField jawaban1Soal1;
    @FXML private TextField jawaban2Soal1;
    @FXML private TextField jawaban3Soal1;
    @FXML private TextField jawaban4Soal1;

    @FXML private TextField jawaban1Soal2;
    @FXML private TextField jawaban2Soal2;
    @FXML private TextField jawaban3Soal2;
    @FXML private TextField jawaban4Soal2;

    @FXML private TextField jawaban1Soal3;
    @FXML private TextField jawaban2Soal3;
    @FXML private TextField jawaban3Soal3;
    @FXML private TextField jawaban4Soal3;

    @FXML private TextField jawaban1Soal4;
    @FXML private TextField jawaban2Soal4;
    @FXML private TextField jawaban3Soal4;
    @FXML private TextField jawaban4Soal4;

    @FXML private TextField jawaban1Soal5;
    @FXML private TextField jawaban2Soal5;
    @FXML private TextField jawaban3Soal5;
    @FXML private TextField jawaban4Soal5;
}
