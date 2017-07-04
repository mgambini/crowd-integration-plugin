package it.ditech.go.authorization.crowd.executor;

import static com.thoughtworks.go.plugin.api.response.DefaultGoApiResponse.SUCCESS_RESPONSE_CODE;
import static it.ditech.go.authorization.crowd.CrowdIntegrationPlugin.LOG;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.Authenticator;
import it.ditech.go.authorization.crowd.Authorizer;
import it.ditech.go.authorization.crowd.model.AuthConfig;
import it.ditech.go.authorization.crowd.model.AuthenticationResponse;
import it.ditech.go.authorization.crowd.model.Credentials;
import it.ditech.go.authorization.crowd.model.RoleConfig;

public class UserAuthenticationExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();
    private final GoPluginApiRequest request;
    private final Authenticator authenticator;
    private final Authorizer authorizer;


    public UserAuthenticationExecutor(GoPluginApiRequest request, Authenticator authenticator, Authorizer authorizer) {
        this.request = request;
        this.authenticator = authenticator;
        this.authorizer = authorizer;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {  	
        Credentials credentials = Credentials.fromJSON(request.requestBody());
        LOG.debug("Trying authenticate user: "+credentials.getUsername());
        final List<AuthConfig> authConfigs = AuthConfig.fromJSONList(request.requestBody());
        final List<RoleConfig> roleConfigs = RoleConfig.fromJSONList(request.requestBody());

        AuthenticationResponse authenticationResponse = authenticator.authenticate(credentials, authConfigs);

        Map<String, Object> userMap = new HashMap<>();
        if (authenticationResponse != null) {
            userMap.put("user", authenticationResponse.getUser());
            userMap.put("roles", Collections.emptyList());
            //userMap.put("roles", authorizer.authorize(authenticationResponse.getUser(), authenticationResponse.getConfigUsedForAuthentication(), roleConfigs));
        }

        DefaultGoPluginApiResponse response = new DefaultGoPluginApiResponse(SUCCESS_RESPONSE_CODE, GSON.toJson(userMap));
        return response;
    }

}
