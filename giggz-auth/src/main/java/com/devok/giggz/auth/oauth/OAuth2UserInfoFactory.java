package com.devok.giggz.auth.oauth;

import java.util.Map;


import com.devok.giggz.auth.exception.OAuth2AuthenticationProcessingException;

import static com.devok.giggz.service.enums.AuthProvider.google;


public class OAuth2UserInfoFactory {

    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(google.toString())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        throw new OAuth2AuthenticationProcessingException("Sorry! Login with " + registrationId + " is not supported yet.");
    }
}
