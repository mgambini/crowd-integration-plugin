package it.ditech.go.authorization.crowd;

import org.junit.Test;

import it.ditech.go.authorization.crowd.Authorizer;

public class AuthorizerTest {
    @Test
    public void shouldAuthorize() throws Exception {
        new Authorizer().authorize(null, null, null);
    }
}