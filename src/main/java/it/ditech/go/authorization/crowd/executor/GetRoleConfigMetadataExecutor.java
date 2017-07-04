package it.ditech.go.authorization.crowd.executor;

import it.ditech.go.authorization.crowd.annotation.MetadataHelper;
import it.ditech.go.authorization.crowd.annotation.ProfileMetadata;
import it.ditech.go.authorization.crowd.model.RoleConfiguration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import java.util.List;

public class GetRoleConfigMetadataExecutor implements RequestExecutor {

    private static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

    public GoPluginApiResponse execute() throws Exception {
        final List<ProfileMetadata> metadata = MetadataHelper.getMetadata(RoleConfiguration.class);
        return new DefaultGoPluginApiResponse(200, GSON.toJson(metadata));
    }

}
