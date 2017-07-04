package it.ditech.go.authorization.crowd;

import java.util.List;

import com.atlassian.crowd.model.user.User;

import it.ditech.go.authorization.crowd.model.AuthConfig;
import it.ditech.go.authorization.crowd.model.AuthenticationResponse;
import it.ditech.go.authorization.crowd.model.Credentials;
import it.ditech.go.authorization.crowd.provider.CrowdAuthenticationProvider;
import static it.ditech.go.authorization.crowd.CrowdIntegrationPlugin.LOG;


public class Authenticator {
	
	private CrowdAuthenticationProvider authProvider;

	
	public Authenticator(){
		LOG.debug("Configuring Crowd Authentication provider...");
    	this.setAuthProvider(new CrowdAuthenticationProvider());
    	LOG.debug("...Crowd Authentication provider configuration done.");
	}
	
    public AuthenticationResponse authenticate(Credentials credentials, List<AuthConfig> authConfigs) {;
    	AuthenticationResponse authenticationResponse = new AuthenticationResponse(null, null);
    	try {
    		LOG.debug("Invoking Crowd with user: "+credentials.getUsername());
    		User crowdUser = getAuthProvider().authenticateUser(credentials.getUsername(), credentials.getPassword());
    		LOG.info("Authentication successful fo user: "+credentials.getUsername());
    		it.ditech.go.authorization.crowd.model.User goUser = new it.ditech.go.authorization.crowd.model.User(crowdUser.getName(), crowdUser.getDisplayName(), crowdUser.getEmailAddress());
    		authenticationResponse = new AuthenticationResponse(goUser, null); 		
    	} catch (Exception e) {
    		LOG.error("Login attempt failed for user "+ credentials.getUsername() +" :"+e.getMessage());       
        } finally {
        	return authenticationResponse;
        }
    }

	public CrowdAuthenticationProvider getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(CrowdAuthenticationProvider authProvider) {
		this.authProvider = authProvider;
	}

}