package com.chess.ui;

import javax.swing.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import com.chess.service.AuthService;

@Component
public class ChessUI extends JFrame {

    private final ConfigurableApplicationContext context;
    private final AuthService authService;

    @Autowired
    public ChessUI(ConfigurableApplicationContext context, AuthService authService) {
        this.context = context;
        this.authService = authService;

        setTitle("Online Chess Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // The game panel is not added here directly. It’s shown after login.
        setLocationRelativeTo(null); // Center on screen
    }

    public void display() {
        // Show the central authentication screen
        AuthEntryDialog authDialog = new AuthEntryDialog(this, authService);
        authDialog.setVisible(true);

        // Once login is successful
        if (authDialog.isLoginSuccessful()) {
            showGameWindow();
        }
    }

    private void showGameWindow() {
        // You can now add and show the chess board or game UI here
        BoardPanel boardPanel = new BoardPanel(); // Ensure BoardPanel is implemented
        add(boardPanel);
        pack();
        setVisible(true);
    }
}
