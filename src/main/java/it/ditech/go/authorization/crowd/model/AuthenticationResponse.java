package it.ditech.go.authorization.crowd.model;

public class AuthenticationResponse {
    private User user;
    private AuthConfig configUsedForAuthentication;

    public AuthenticationResponse(User user, AuthConfig configUsedForAuthentication) {
        this.user = user;
        this.configUsedForAuthentication = configUsedForAuthentication;
    }

    public User getUser() {
        return user;
    }

    public AuthConfig getConfigUsedForAuthentication() {
        return configUsedForAuthentication;
    }
}
