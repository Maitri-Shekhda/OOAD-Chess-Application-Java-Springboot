package com.chess.ui;

import com.chess.model.Player;
import com.chess.service.AuthService;

import javax.swing.*;
import java.awt.*;
import java.util.Optional;

public class LoginDialog extends JDialog {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private boolean loginSuccessful = false;

    private final AuthService authService;

    public LoginDialog(JFrame parent, AuthService authService) {
        super(parent, "Login", true);
        this.authService = authService;

        setLayout(new BorderLayout());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(parent);

        // UI Components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        JButton cancelButton = new JButton("Cancel");

        panel.add(loginButton);
        panel.add(cancelButton);

        add(panel, BorderLayout.CENTER);

        // Button listeners
        loginButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());

            Optional<Player> playerOpt = authService.authenticatePlayer(username, password);

            if (playerOpt.isPresent()) {
                JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                loginSuccessful = true;
                dispose(); // close the dialog
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            dispose(); // just close
        });
    }

    public boolean isLoginSuccessful() {
        return loginSuccessful;
    }
}
