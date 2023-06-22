package com.project.uaspbo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

// Method utama aplikasi
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Memuat file FXML untuk halaman login
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Menyetel judul dari aplikasi
        stage.setTitle("Learning Management System!");

        // Agar aplikasi tidak bisa resizable
        stage.setResizable(false);
        stage.setScene(scene);

        // Menampilkan aplikasi
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}