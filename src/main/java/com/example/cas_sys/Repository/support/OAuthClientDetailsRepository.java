package com.example.cas_sys.Repository.support;

import com.example.cas_sys.Model.OAuthClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.List;


public interface OAuthClientDetailsRepository {

	OAuthClientDetails loadClientByClientId(String clientId);
	
	 void addClientDetails(ClientDetails clientDetails);
	
	 long updateClientDetails(ClientDetails clientDetails);
	
	 long updateClientSecret(String clientId, String clientSecret);
	
	long removeClientDetails(String clientId);
	
	List<ClientDetails> listClientDetails();
}
