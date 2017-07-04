package it.ditech.go.authorization.crowd.executor;

import org.junit.Test;

import it.ditech.go.authorization.crowd.Authenticator;
import it.ditech.go.authorization.crowd.Authorizer;
import it.ditech.go.authorization.crowd.executor.UserAuthenticationExecutor;

public class UserAuthenticationExecutorTest {
    @Test
    public void shouldAuthenticate() throws Exception {
        new UserAuthenticationExecutor(null, new Authenticator(), new Authorizer()).execute();
    }
}