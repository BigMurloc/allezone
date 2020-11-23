package pl.edu.pjwstk.jaz.user;

import java.util.Set;

public enum Role {

    ADMIN("admin","view-readiness");
    private final String[] authorities;


    Role(String... authorities){
        this.authorities = authorities;
    }
}
