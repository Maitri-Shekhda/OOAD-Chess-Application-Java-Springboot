package com.chess;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import javax.swing.SwingUtilities;
import com.chess.ui.ChessUI;

@SpringBootApplication
public class ChessApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ChessApplication.class, args);
        
        // Launch the Swing UI in the EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            ChessUI chessUI = context.getBean(ChessUI.class);
            chessUI.display();
        });
    }
}
