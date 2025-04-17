package com.chess.ui;

import com.chess.service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ForgotPasswordDialog extends JDialog {
    private JTextField emailField;
    private final AuthService authService;

    public ForgotPasswordDialog(JFrame parent, AuthService authService) {
        super(parent, "Forgot Password", true);
        this.authService = authService;

        setLayout(new GridLayout(2, 2));
        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        JButton resetButton = new JButton("Send Reset Link");
        resetButton.addActionListener(this::resetAction);
        add(resetButton);

        setSize(300, 120);
        setLocationRelativeTo(parent);
    }

    private void resetAction(ActionEvent e) {
        if (authService.sendPasswordResetLink(emailField.getText())) {
            JOptionPane.showMessageDialog(this, "Reset link sent (simulated)");
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Email not found");
        }
    }
}
