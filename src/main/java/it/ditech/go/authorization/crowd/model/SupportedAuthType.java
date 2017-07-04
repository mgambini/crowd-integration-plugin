package it.ditech.go.authorization.crowd.model;

import com.google.gson.annotations.SerializedName;

public enum SupportedAuthType {
    @SerializedName("password")
    Password,
    @SerializedName("web")
    Web
}
