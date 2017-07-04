package it.ditech.go.authorization.crowd.executor;

import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.model.Capabilities;
import it.ditech.go.authorization.crowd.model.SupportedAuthType;

import static com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse.SUCCESS_RESPONSE_CODE;

public class GetCapabilitiesExecutor {

    public GoPluginApiResponse execute() {
        Capabilities capabilities = getCapabilities();
        return new DefaultGoPluginApiResponse(SUCCESS_RESPONSE_CODE, capabilities.toJSON());
    }

    Capabilities getCapabilities() {
    	return new Capabilities(SupportedAuthType.Password, Boolean.FALSE, Boolean.FALSE);
    }
}
