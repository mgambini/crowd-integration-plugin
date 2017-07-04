package it.ditech.go.authorization.crowd.executor;


import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.model.Configuration;

import java.util.List;
import java.util.Map;


public class AuthConfigValidateRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;

    public AuthConfigValidateRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    public GoPluginApiResponse execute() throws Exception {
        Map<String, String> properties = GSON.fromJson(request.requestBody(), Map.class);
        final List<Map<String, String>> validationResult = Configuration.validate(properties);
        return DefaultGoPluginApiResponse.success(GSON.toJson(validationResult));
    }
}
