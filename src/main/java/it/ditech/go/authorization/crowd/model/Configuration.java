package it.ditech.go.authorization.crowd.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.ditech.go.authorization.crowd.annotation.MetadataHelper;
import it.ditech.go.authorization.crowd.annotation.ProfileField;

import static it.ditech.go.authorization.crowd.utils.Util.GSON;

import java.util.List;
import java.util.Map;

public class Configuration {

    //@Expose
    @SerializedName("ExampleField")
    @ProfileField(key = "ExampleField", required = false, secure = false)
    private String exampleField;

    public static Configuration fromJSON(String json) {
        return GSON.fromJson(json, Configuration.class);
    }

    public String getExampleField() {
        return exampleField;
    }

    public static List<Map<String, String>> validate(Map<String, String> properties) {
        final List<Map<String, String>> validationResult = MetadataHelper.validate(Configuration.class, properties);
        return validationResult;
    }
}
