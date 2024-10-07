package ufpa.libertapp.security;

import java.util.Collection;

public record LoginResponseDTO(String token, Collection role) {

}
