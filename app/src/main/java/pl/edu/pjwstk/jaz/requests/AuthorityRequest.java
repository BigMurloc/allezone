package pl.edu.pjwstk.jaz.requests;

public class AuthorityRequest {

    private String username;

    private String authority;

    public AuthorityRequest(String username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    public String getUsername() {
        return username;
    }

    public String getAuthority() {
        return authority;
    }
}
