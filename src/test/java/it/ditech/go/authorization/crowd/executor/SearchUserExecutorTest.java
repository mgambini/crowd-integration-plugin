package it.ditech.go.authorization.crowd.executor;

import org.junit.Test;

import it.ditech.go.authorization.crowd.executor.SearchUserExecutor;

public class SearchUserExecutorTest {

    @Test
    public void shouldSearch() throws Exception {
        new SearchUserExecutor(null).execute();
    }
}