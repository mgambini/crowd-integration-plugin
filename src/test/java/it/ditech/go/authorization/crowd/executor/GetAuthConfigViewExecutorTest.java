package it.ditech.go.authorization.crowd.executor;

import it.ditech.go.authorization.crowd.annotation.MetadataHelper;
import it.ditech.go.authorization.crowd.annotation.ProfileMetadata;
import it.ditech.go.authorization.crowd.executor.GetAuthConfigViewExecutor;
import it.ditech.go.authorization.crowd.model.Configuration;
import it.ditech.go.authorization.crowd.utils.Util;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class GetAuthConfigViewExecutorTest {

    @Test
    public void shouldRenderTheTemplateInJSON() throws Exception {
        GoPluginApiResponse response = new GetAuthConfigViewExecutor().execute();
        assertThat(response.responseCode(), is(200));
        Map<String, String> hashSet = new Gson().fromJson(response.responseBody(), HashMap.class);
        assertThat(hashSet, hasEntry("template", Util.readResource("/auth-config.template.html")));
    }

    @Test
    public void allFieldsShouldBePresentInView() throws Exception {
        String template = Util.readResource("/auth-config.template.html");

        for (ProfileMetadata field : MetadataHelper.getMetadata(Configuration.class)) {
            assertThat(template, containsString("ng-model=\"" + field.getKey() + "\""));
            assertThat(template, containsString("<span class=\"form_error form-error\" ng-class=\"{'is-visible': GOINPUTNAME[" +
                    field.getKey() + "].$error.server}\" ng-show=\"GOINPUTNAME[" +
                    field.getKey() + "].$error.server\">{{GOINPUTNAME[" +
                    field.getKey() + "].$error.server}}</span>"));
        }
    }
}