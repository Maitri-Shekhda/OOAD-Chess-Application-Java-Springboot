package com.chess.ui;

import com.chess.service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class RegisterDialog extends JDialog {
    private JTextField usernameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private final AuthService authService;

    public RegisterDialog(JFrame parent, AuthService authService) {
        super(parent, "Register", true);
        this.authService = authService;

        setLayout(new GridLayout(4, 2));
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);
        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);
        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(this::registerAction);
        add(registerButton);

        setSize(350, 200);
        setLocationRelativeTo(parent);
    }

    private void registerAction(ActionEvent e) {
        try {
            authService.registerPlayer(
                usernameField.getText(),
                emailField.getText(),
                new String(passwordField.getPassword())
            );
            JOptionPane.showMessageDialog(this, "Registration successful");
            dispose();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
