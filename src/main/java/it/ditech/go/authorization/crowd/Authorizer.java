package it.ditech.go.authorization.crowd;

import java.util.List;
import java.util.Set;

import it.ditech.go.authorization.crowd.model.AuthConfig;
import it.ditech.go.authorization.crowd.model.RoleConfig;
import it.ditech.go.authorization.crowd.model.User;

public class Authorizer {
    public Set<String> authorize(User user, AuthConfig authConfig, List<RoleConfig> roleConfigs) {
        throw new RuntimeException("Implement me!");
    }
}
