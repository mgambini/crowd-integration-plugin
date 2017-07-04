package it.ditech.go.authorization.crowd.executor;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.utils.Util;
import static it.ditech.go.authorization.crowd.Constants.SVG_CONTENT_TYPE;
import static it.ditech.go.authorization.crowd.Constants.IMG_PATH;
import static it.ditech.go.authorization.crowd.Constants.CROWD_ICON_NAME;

import org.apache.commons.codec.binary.Base64;

public class GetPluginIconExecutor implements RequestExecutor {
    private static final Gson GSON = new Gson();

    @Override
    public GoPluginApiResponse execute() throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("content_type", getContentType());
        jsonObject.addProperty("data", Base64.encodeBase64String(Util.readResourceBytes(getIcon())));
        DefaultGoPluginApiResponse defaultGoPluginApiResponse = new DefaultGoPluginApiResponse(200, GSON.toJson(jsonObject));
        return defaultGoPluginApiResponse;

    }

    private String getContentType() {
        return SVG_CONTENT_TYPE;
    }

    private String getIcon() {
        return IMG_PATH+"/"+CROWD_ICON_NAME;
    }
}
