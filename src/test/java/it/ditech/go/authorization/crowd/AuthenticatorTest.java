package it.ditech.go.authorization.crowd;

import org.junit.Test;

import it.ditech.go.authorization.crowd.Authenticator;

public class AuthenticatorTest {

    @Test
    public void shouldAuthenticate() throws Exception {
        new Authenticator().authenticate(null, null);
    }
}