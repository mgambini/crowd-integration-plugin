package it.ditech.go.authorization.crowd.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import static it.ditech.go.authorization.crowd.utils.Util.GSON;

import java.lang.reflect.Type;
import java.util.List;

public class RoleConfig {
    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("auth_config_id")
    private String authConfigId;

    @Expose
    @SerializedName("configuration")
    private RoleConfiguration roleConfiguration;

    public static List<RoleConfig> fromJSONList(String requestBody) {
        Type type = new TypeToken<List<RoleConfig>>() {
        }.getType();

        JsonObject jsonObject = GSON.fromJson(requestBody, JsonObject.class);
        return GSON.fromJson(jsonObject.get("role_configs").toString(), type);
    }

    public String getName() {
        return name;
    }

    public RoleConfiguration getRoleConfiguration() {
        return roleConfiguration;
    }

    public String getAuthConfigId() {
        return authConfigId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleConfig that = (RoleConfig) o;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return roleConfiguration != null ? roleConfiguration.equals(that.roleConfiguration) : that.roleConfiguration == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (roleConfiguration != null ? roleConfiguration.hashCode() : 0);
        return result;
    }

    public static RoleConfig fromJSON(String json) {
        return GSON.fromJson(json, RoleConfig.class);
    }
}
