package it.ditech.go.authorization.crowd.executor;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.executor.GetPluginIconExecutor;
import it.ditech.go.authorization.crowd.utils.Util;

import org.apache.commons.codec.binary.Base64;
import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class GetPluginIconExecutorTest {

    @Test
    public void shouldReturnsPluginIcon() throws Exception {
        GoPluginApiResponse response = new GetPluginIconExecutor().execute();
        HashMap<String, String> hashMap = new Gson().fromJson(response.responseBody(), HashMap.class);
        assertThat(hashMap.size(), is(2));
        assertThat(hashMap.get("content_type"), is("image/png"));
        assertThat(Util.readResourceBytes("/plugin-icon.png"), is(Base64.decodeBase64(hashMap.get("data"))));
    }
}