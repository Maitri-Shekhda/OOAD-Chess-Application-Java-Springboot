package com.chess.service;

import com.chess.model.Player;
import com.chess.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Player registerPlayer(String username, String email, String password) {
        if (playerRepository.existsByUsername(username)) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (playerRepository.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already exists");
        }

        Player player = new Player(username, passwordEncoder.encode(password), email);
        return playerRepository.save(player);
    }

    public Optional<Player> authenticatePlayer(String username, String password) {
        Optional<Player> playerOpt = playerRepository.findByUsername(username);

        if (playerOpt.isPresent()) {
            Player player = playerOpt.get();
            if (passwordEncoder.matches(password, player.getPassword())) {
                return Optional.of(player);
            }
        }
        return Optional.empty();
    }

    public boolean sendPasswordResetLink(String email) {
        Optional<Player> playerOpt = playerRepository.findByEmail(email);
        return playerOpt.isPresent(); // Simulate sending email
    }
}
