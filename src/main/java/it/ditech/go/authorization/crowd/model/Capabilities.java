package it.ditech.go.authorization.crowd.model;

import static it.ditech.go.authorization.crowd.utils.Util.GSON;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capabilities {
    @Expose
    @SerializedName("supported_auth_type")
    private final SupportedAuthType supportedAuthType;

    @Expose
    @SerializedName("can_search")
    private final boolean canSearch;

    @Expose
    @SerializedName("can_authorize")
    private final boolean canAuthorize;

    public Capabilities(SupportedAuthType supportedAuthType, boolean canSearch, boolean canAuthorize) {
        this.supportedAuthType = supportedAuthType;
        this.canSearch = canSearch;
        this.canAuthorize = canAuthorize;
    }

    public static Capabilities fromJSON(String json) {
        return GSON.fromJson(json, Capabilities.class);
    }

    public SupportedAuthType getSupportedAuthType() {
        return supportedAuthType;
    }

    public boolean canSearch() {
        return canSearch;
    }

    public boolean canAuthorize() {
        return canSearch;
    }

    public String toJSON() {
        return GSON.toJson(this);
    }
}
