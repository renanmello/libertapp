package ufpa.libertapp.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("admin"),
    VITIMA("vitima"),
    EMPRESA("empresa"),
    ORGAO("orgao");

    private String role;

    UserRole(String role) {
        this.role = role;
    }
}
