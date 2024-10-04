package ufpa.libertapp.user;

public enum UserRole {
    ADMIN("admin"),
    VITIMA("vitima"),
    EMPRESA("empresa"),
    ORGAO("orgao");

    private String role;

     UserRole(String role){
        this.role = role;
    }
    public String getRole(){
         return role;
    }
}
