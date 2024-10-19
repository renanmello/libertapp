package ufpa.libertapp.security;

import ufpa.libertapp.user.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {}
