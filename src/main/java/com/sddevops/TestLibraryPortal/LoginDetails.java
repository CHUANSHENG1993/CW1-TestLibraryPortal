package com.sddevops.TestLibraryPortal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginDetails implements ActionListener {
    private JFrame frame;
    private JLabel usernameLabel, passwordLabel, messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginDetails() {
        frame = new JFrame("Student Login");
        frame.setLayout(new GridLayout(3, 2));

        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        messageLabel = new JLabel();

        usernameField = new JTextField();
        passwordField = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);

        frame.add(usernameLabel);
        frame.add(usernameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(messageLabel);
        frame.add(loginButton);

        frame.setSize(300, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        if (validateCredentials(username, password)) {
            messageLabel.setText("Login successful!");
        } else {
            messageLabel.setText("Invalid username or password.");
        }
    }

    private boolean validateCredentials(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("credentials.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if (credentials.length == 2 && credentials[0].equals(username) && credentials[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginDetails());
    }
}
