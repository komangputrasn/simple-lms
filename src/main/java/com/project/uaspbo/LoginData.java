package com.project.uaspbo;

// class ini berfungsi untuk menampung username yang sedang login
public class LoginData {
    private static String currentStudentUsername;

    // Set username yang sedang login
    public static void setCurrentUsername(String username) {
        currentStudentUsername = username;
        System.out.println("Username changed: " + username);
    }

    // Mengembalikan username yang sedang login
    public static String getCurrentUsername() {
        System.out.println("Username retrieved: " + currentStudentUsername);
        return currentStudentUsername;
    }
}
