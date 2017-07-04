package it.ditech.go.authorization.crowd.executor;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.validation.ValidationResult;

import it.ditech.go.authorization.crowd.model.Configuration;

public class VerifyConnectionRequestExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;

    public VerifyConnectionRequestExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() {
        Configuration configuration = Configuration.fromJSON(request.requestBody());
        ValidationResult result = validate(configuration);
        //return DefaultGoPluginApiResponse.success(GSON.toJson(result.getErrors()));
        return DefaultGoPluginApiResponse.success(GSON.toJson(null));
    }

    public ValidationResult validate(Configuration configuration) {
    	return new ValidationResult();
    }
}
