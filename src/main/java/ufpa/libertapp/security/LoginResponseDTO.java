package ufpa.libertapp.security;

import org.springframework.security.core.GrantedAuthority;
import java.util.Collection;

public record LoginResponseDTO(String token, Collection<? extends GrantedAuthority> role, Long id) {}
