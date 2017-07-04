package it.ditech.go.authorization.crowd.executor;

import it.ditech.go.authorization.crowd.annotation.MetadataHelper;
import it.ditech.go.authorization.crowd.executor.GetRoleConfigMetadataExecutor;
import it.ditech.go.authorization.crowd.model.RoleConfiguration;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class GetRoleConfigMetadataExecutorTest {

    @Test
    public void shouldSerializeAllFields() throws Exception {
        GoPluginApiResponse response = new GetRoleConfigMetadataExecutor().execute();
        List list = new Gson().fromJson(response.responseBody(), List.class);
        assertEquals(list.size(), MetadataHelper.getMetadata(RoleConfiguration.class).size());
    }

    @Test
    public void assertJsonStructure() throws Exception {
        GoPluginApiResponse response = new GetRoleConfigMetadataExecutor().execute();

        assertThat(response.responseCode(), is(200));
        String expectedJSON = "[\n" +
                "  {\n" +
                "    \"key\": \"ExampleField\",\n" +
                "    \"metadata\": {\n" +
                "      \"required\": false,\n" +
                "      \"secure\": false\n" +
                "    }\n" +
                "  }\n" +
                "]";

        JSONAssert.assertEquals(expectedJSON, response.responseBody(), true);
    }
}