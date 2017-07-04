package it.ditech.go.authorization.crowd.executor;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.model.RoleConfiguration;

import java.util.List;
import java.util.Map;

public class RoleConfigValidateRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;

    public RoleConfigValidateRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {
        Map<String, String> properties = GSON.fromJson(request.requestBody(), Map.class);

        final List<Map<String, String>> validationResult = RoleConfiguration.validate(properties);
        return DefaultGoPluginApiResponse.success(GSON.toJson(validationResult));
    }
}
