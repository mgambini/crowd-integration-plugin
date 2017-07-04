package it.ditech.go.authorization.crowd.executor;

import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.model.AuthConfig;
import it.ditech.go.authorization.crowd.model.User;

import static it.ditech.go.authorization.crowd.utils.Util.GSON;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class SearchUserExecutor implements RequestExecutor {
    public static final String SEARCH_TERM = "search_term";
    private final GoPluginApiRequest request;

    public SearchUserExecutor(GoPluginApiRequest request) {
        this.request = request;
    }

    @Override
    public GoPluginApiResponse execute() throws Exception {
        Map<String, String> requestParam = GSON.fromJson(request.requestBody(), Map.class);
        String searchTerm = requestParam.get(SEARCH_TERM);
        List<AuthConfig> authConfigs = AuthConfig.fromJSONList(request.requestBody());

        final Set<User> users = searchUsers(searchTerm, authConfigs);

        return new DefaultGoPluginApiResponse(200, GSON.toJson(users));
    }

    Set<User> searchUsers(String searchTerm, List<AuthConfig> authConfigs) {
        throw new RuntimeException("Implement me!");
    }
}
