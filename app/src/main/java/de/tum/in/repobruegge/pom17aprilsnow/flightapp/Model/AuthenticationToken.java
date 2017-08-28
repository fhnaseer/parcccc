package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model;

/**
 * Created by Vincent Bode on 02.07.2017.
 */

public class AuthenticationToken {
    private String token;

    public AuthenticationToken(String token) {
        this.token = token;
    }

    public AuthenticationToken() {
        this("INVALID");
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
