package ar.edu.unq.desapp.grupoj.backenddesappapi.model;

public class AuthenticationResponse {
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    private final String jwt;

    public String getJwt() {
        return jwt;
    }
}