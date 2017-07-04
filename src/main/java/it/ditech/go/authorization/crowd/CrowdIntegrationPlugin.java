package it.ditech.go.authorization.crowd;

import static com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse.INTERNAL_ERROR;
import static it.ditech.go.authorization.crowd.Constants.PLUGIN_IDENTIFIER;

import com.thoughtworks.go.plugin.api.GoApplicationAccessor;
import com.thoughtworks.go.plugin.api.GoPlugin;
import com.thoughtworks.go.plugin.api.GoPluginIdentifier;
import com.thoughtworks.go.plugin.api.annotation.Extension;
import com.thoughtworks.go.plugin.api.annotation.Load;
import com.thoughtworks.go.plugin.api.exceptions.UnhandledRequestTypeException;
import com.thoughtworks.go.plugin.api.info.PluginContext;
import com.thoughtworks.go.plugin.api.logging.Logger;
import com.thoughtworks.go.plugin.api.request.GoPluginApiRequest;
import com.thoughtworks.go.plugin.api.response.DefaultGoPluginApiResponse;
import com.thoughtworks.go.plugin.api.response.GoPluginApiResponse;

import it.ditech.go.authorization.crowd.executor.AuthConfigValidateRequestExecutor;
import it.ditech.go.authorization.crowd.executor.GetAuthConfigMetadataExecutor;
import it.ditech.go.authorization.crowd.executor.GetAuthConfigViewExecutor;
import it.ditech.go.authorization.crowd.executor.GetCapabilitiesExecutor;
import it.ditech.go.authorization.crowd.executor.GetPluginIconExecutor;
import it.ditech.go.authorization.crowd.executor.NoSuchRequestHandler;
import it.ditech.go.authorization.crowd.executor.RequestFromServer;
import it.ditech.go.authorization.crowd.executor.SearchUserExecutor;
import it.ditech.go.authorization.crowd.executor.UserAuthenticationExecutor;
import it.ditech.go.authorization.crowd.executor.VerifyConnectionRequestExecutor;
import it.ditech.go.authorization.crowd.utils.Util;

@Extension
public class CrowdIntegrationPlugin implements GoPlugin {
   
	public static final Logger LOG =  Logger.getLoggerFor(CrowdIntegrationPlugin.class);
    private GoApplicationAccessor accessor;
    
    @Override
    public void initializeGoApplicationAccessor(GoApplicationAccessor accessor) {
        this.accessor = accessor;
    }
    
    @Load
    public void onLoad(PluginContext ctx) {
        LOG.info("Loading plugin " + Util.pluginId() + " version " + Util.pluginVersion());
    }

    @Override
    public GoPluginApiResponse handle(GoPluginApiRequest request) throws UnhandledRequestTypeException {
        try {
            switch (RequestFromServer.fromString(request.requestName())) {
                case REQUEST_GET_PLUGIN_ICON:
                	LOG.info("Request: "+RequestFromServer.REQUEST_GET_PLUGIN_ICON.requestName());
                    return new GetPluginIconExecutor().execute();
                case REQUEST_GET_CAPABILITIES:
                	LOG.info("Request: "+RequestFromServer.REQUEST_GET_CAPABILITIES.requestName());
                    return new GetCapabilitiesExecutor().execute();
                case REQUEST_GET_AUTH_CONFIG_METADATA:
                	LOG.info("Request: "+RequestFromServer.REQUEST_GET_AUTH_CONFIG_METADATA.requestName());
                    return new GetAuthConfigMetadataExecutor().execute();
                case REQUEST_AUTH_CONFIG_VIEW:
                	LOG.info("Request: "+RequestFromServer.REQUEST_AUTH_CONFIG_VIEW.requestName());
                	return new GetAuthConfigViewExecutor().execute();
                case REQUEST_VALIDATE_AUTH_CONFIG:
                	LOG.info("Request: "+RequestFromServer.REQUEST_VALIDATE_AUTH_CONFIG.requestName());
                    return new AuthConfigValidateRequestExecutor(request).execute();
                case REQUEST_VERIFY_CONNECTION:
                	LOG.info("Request: "+RequestFromServer.REQUEST_VERIFY_CONNECTION.requestName());
                    return new VerifyConnectionRequestExecutor(request).execute();
               /** case REQUEST_GET_ROLE_CONFIG_METADATA:
                	LOG.debug("Request: "+RequestFromServer.REQUEST_GET_ROLE_CONFIG_METADATA.requestName());
                    return new GetRoleConfigMetadataExecutor().execute();
                case REQUEST_ROLE_CONFIG_VIEW:
                	LOG.debug("Request: "+RequestFromServer.REQUEST_ROLE_CONFIG_VIEW.requestName());
                    return new GetRoleConfigViewExecutor().execute();
                case REQUEST_VALIDATE_ROLE_CONFIG:
                	LOG.debug("Request: "+RequestFromServer.REQUEST_VALIDATE_ROLE_CONFIG.requestName());
                    return new RoleConfigValidateRequestExecutor(request).execute(); **/
                case REQUEST_AUTHENTICATE_USER:
                	LOG.info("Request: "+RequestFromServer.REQUEST_AUTHENTICATE_USER.requestName());
                    return new UserAuthenticationExecutor(request, new Authenticator(), new Authorizer()).execute();
                case REQUEST_SEARCH_USERS:
                	LOG.info("Request: "+RequestFromServer.REQUEST_SEARCH_USERS.requestName());
                    return new SearchUserExecutor(request).execute();
                default:
                    throw new UnhandledRequestTypeException(request.requestName());
            }
        } catch (NoSuchRequestHandler e) {
            LOG.error(e.getMessage());
            return new DefaultGoPluginApiResponse(INTERNAL_ERROR, e.getMessage());
        } catch (Exception e) {
            LOG.error("Error while executing request " + request.requestName());
            return new DefaultGoPluginApiResponse(INTERNAL_ERROR, e.getMessage());
        }
    }

    @Override
    public GoPluginIdentifier pluginIdentifier() {
        return PLUGIN_IDENTIFIER;
    }

}
