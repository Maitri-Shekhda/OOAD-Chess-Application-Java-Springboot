package com.chess.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.chess.service.AuthService;

public class AuthEntryDialog extends JDialog {

    private boolean loginSuccessful = false;
    private AuthService authService;

    public AuthEntryDialog(JFrame parent, AuthService authService) {
        super(parent, "Welcome to Chess", true);
        this.authService = authService;

        setLayout(new BorderLayout());
        setSize(300, 200);
        setLocationRelativeTo(parent);

        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");
        JButton forgotBtn = new JButton("Forgot Password");

        buttonPanel.add(loginBtn);
        buttonPanel.add(registerBtn);
        buttonPanel.add(forgotBtn);
        add(buttonPanel, BorderLayout.CENTER);

        loginBtn.addActionListener(e -> {
            LoginDialog loginDialog = new LoginDialog((JFrame) getParent(), authService);
            loginDialog.setVisible(true);
            if (loginDialog.isLoginSuccessful()) {
                loginSuccessful = true;
                dispose();
            }
        });

        registerBtn.addActionListener(e -> {
            RegisterDialog registerDialog = new RegisterDialog((JFrame) getParent(), authService);
            registerDialog.setVisible(true);
        });

        forgotBtn.addActionListener(e -> {
            ForgotPasswordDialog forgotDialog = new ForgotPasswordDialog((JFrame) getParent(), authService);
            forgotDialog.setVisible(true);
        });
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
