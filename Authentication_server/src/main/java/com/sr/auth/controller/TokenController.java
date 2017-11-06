package com.sr.auth.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.sr.auth.domain.*;
import com.sr.auth.repository.*;


@Controller
public class TokenController {

    @Resource(name = "tokenServices")
    ConsumerTokenServices tokenServices;

    @Resource(name = "tokenStore")
    TokenStore tokenStore;
    @Autowired
    private UserRepository userRepository;
    
    //Controller POC Endpoints for permit
    @RequestMapping(method = RequestMethod.GET, value = "/greeting")
    @ResponseStatus(HttpStatus.OK)
	public String  greeting() {
//    	Role role = new Role("ADMIN");
//    	Set<Role> roles = new HashSet<Role>();
//    	roles.add(role);
//    	
//    	User user = new User("jonny","123",roles);
//    	userRepository.save(user);
//    	//User user2 = userRepository.findByUsername("john");
//       // System.out.println(user2.getRoles());
        return "welcome";
        	}
    
    
  //Controller POC Endpoints for permit
    @RequestMapping(method = RequestMethod.GET, value = "/secured/greeting")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
	public String securedgreeting() {
		return "welcome to security";
	}
    

//    @RequestMapping(method = RequestMethod.POST, value = "/oauth/token/revokeById/{tokenId}")
//    @ResponseBody
//    public void revokeToken(HttpServletRequest request, @PathVariable String tokenId) {
//        tokenServices.revokeToken(tokenId);
//    }
//
//    @RequestMapping(method = RequestMethod.GET, value = "/tokens")
//    @ResponseBody
//    public List<String> getTokens() {
//        List<String> tokenValues = new ArrayList<String>();
//        Collection<OAuth2AccessToken> tokens = tokenStore.findTokensByClientId("fooClientIdPassword");
//        if (tokens != null) {
//            for (OAuth2AccessToken token : tokens) {
//                tokenValues.add(token.getValue());
//            }
//        }
//        return tokenValues;
//    }
    

    //curl fooClientIdPassword:secret@localhost:8080/oauth/token -d "password=123&username=john&grant_type=password"

    //curl fooClientIdPassword:secret@localhost:8080/oauth/token -d "grant_type=refresh_token&refresh_token="

//    @RequestMapping(method = RequestMethod.POST, value = "/tokens/revokeRefreshToken/{tokenId:.*}")
//    @ResponseBody
//    public String revokeRefreshToken(@PathVariable String tokenId) {
//        if (tokenStore instanceof JdbcTokenStore) {
//            ((JdbcTokenStore) tokenStore).removeRefreshToken(tokenId);
//        }
//        return tokenId;
//    }

}