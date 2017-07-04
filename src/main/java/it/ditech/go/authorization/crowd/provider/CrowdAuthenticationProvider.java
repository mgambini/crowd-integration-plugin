package it.ditech.go.authorization.crowd.provider;

import static it.ditech.go.authorization.crowd.Constants.CROWD_PROPS_FILENAME;
import static it.ditech.go.authorization.crowd.CrowdIntegrationPlugin.LOG;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.atlassian.crowd.exception.ApplicationPermissionException;
import com.atlassian.crowd.exception.ExpiredCredentialException;
import com.atlassian.crowd.exception.InactiveAccountException;
import com.atlassian.crowd.exception.InvalidAuthenticationException;
import com.atlassian.crowd.exception.OperationFailedException;
import com.atlassian.crowd.exception.UserNotFoundException;
import com.atlassian.crowd.integration.rest.service.factory.RestCrowdClientFactory;
import com.atlassian.crowd.model.group.Group;
import com.atlassian.crowd.model.user.User;
import com.atlassian.crowd.service.client.ClientPropertiesImpl;
import com.atlassian.crowd.service.client.CrowdClient;

/**
 * Uses Atlassian Crowd client authenticator
 * @author mgambini
 *
 */
public class CrowdAuthenticationProvider {

    private CrowdClient crowdClient;
    
    public CrowdAuthenticationProvider(){
    	LOG.debug("Instantiate CrowdClient...");
    	this.setCrowdClient(this.createCrowdClient());
    	LOG.debug("...CrowdClient instantiated.");
    }

    /**
     * Load environment properties file
     * @param filePath path-tofile
     * @return 
     */
    private String getFileContents(String filePath) {
        try {
            return IOUtils.toString(getClass().getResourceAsStream(filePath));
        } catch (Exception e) {
        	LOG.error("Crowd properties file not found: "+e.getMessage());
            throw new RuntimeException(e);
        }
    }
    
    private ClientPropertiesImpl initClientProperties() {
        Properties props = new Properties();      
        try {
        	LOG.debug("Loading crowd.properties..."); 
			props.load(new StringReader(getFileContents("/"+CROWD_PROPS_FILENAME)));    
			LOG.debug("Configuration found for application: "+props.getProperty("application.name"));
        } catch (IOException e) {
        	LOG.error("Error getting crowd init properties: "+e.getMessage());
			throw new RuntimeException("Error getting crowd init properties"+e.getMessage());
		}       
        LOG.debug("Initializing Crowd properties....");
        ClientPropertiesImpl clientProps = ClientPropertiesImpl.newInstanceFromProperties(props);
        LOG.debug("...Crowd properties initialized");
        return clientProps;
    }
    
    /**
     * Authenticate user with provided username and password
     * @param usn
     * @param pwd
     * @return
     * @throws UserNotFoundException
     * @throws InactiveAccountException
     * @throws ExpiredCredentialException
     * @throws ApplicationPermissionException
     * @throws InvalidAuthenticationException
     * @throws OperationFailedException
     */
    public User authenticateUser(String usn, String pwd) throws UserNotFoundException, 
    		InactiveAccountException, 
    		ExpiredCredentialException, 
    		ApplicationPermissionException, 
    		InvalidAuthenticationException, 
    		OperationFailedException{
    	
		return this.getCrowdClient().authenticateUser(usn, pwd);
    }

    /**
     * Request groups for provided user
     * @param user
     * @return
     * @throws Exception
     */
    public List<Group> getGroupsForUser(User user) throws Exception {
        List<Group> groupsForUser = null;
        LOG.info("Requesting groups for: "+user.getName());
        if (user != null) {          
            groupsForUser = crowdClient.getGroupsForNestedUser(user.getName(), 0, -1);
            LOG.info("Groups for user " + user.getName() + " obtained.");                         
        }
        return groupsForUser;
    }
    
    private CrowdClient createCrowdClient() {   	
    	LOG.info("Creating CrowdClient...");
    	CrowdClient crowdClient = new RestCrowdClientFactory().newInstance(initClientProperties());  	
    	LOG.info("...CrowdClient created.");
    	return crowdClient;
    }

	public CrowdClient getCrowdClient() {
		return crowdClient;
	}

	public void setCrowdClient(CrowdClient crowdClient) {
		this.crowdClient = crowdClient;
	}
}