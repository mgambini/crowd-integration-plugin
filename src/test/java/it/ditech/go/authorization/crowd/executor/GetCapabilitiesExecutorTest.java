package it.ditech.go.authorization.crowd.executor;

import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.executor.GetCapabilitiesExecutor;

import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

public class GetCapabilitiesExecutorTest {

    @Test
    public void shouldReturnsPluginCapabilities() throws Exception {
        GoPluginApiResponse response = new GetCapabilitiesExecutor().execute();

        String expectedJSON = "{\n" +
                "    \"supported_auth_type\":\"password\",\n" +
                "    \"can_search\":true,\n" +
                "    \"can_authorize\":true\n" +
                "}";

        JSONAssert.assertEquals(expectedJSON, response.responseBody(), true);
    }
}
