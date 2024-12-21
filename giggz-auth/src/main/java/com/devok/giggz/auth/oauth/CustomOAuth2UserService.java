package com.devok.giggz.auth.oauth;

import java.util.Optional;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.devok.giggz.auth.exception.OAuth2AuthenticationProcessingException;
import com.devok.giggz.service.UserService;
import com.devok.giggz.service.enums.AuthProvider;
import com.devok.giggz.service.dto.UserDTO;
import com.devok.giggz.service.auth.UserPrincipal;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    public CustomOAuth2UserService(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<UserDTO> userOptional = userService.findByEmail(oAuth2UserInfo.getEmail());
        UserDTO userDTO;
        if(userOptional.isPresent()) {
            userDTO = userOptional.get();
            if(!userDTO.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))) {
                throw new OAuth2AuthenticationProcessingException("Looks like you're signed up with " +
                        userDTO.getProvider() + " account. Please use your " + userDTO.getProvider() +
                        " account to login.");
            }
            userDTO = updateExistingUser(userDTO, oAuth2UserInfo);
        } else {
            userDTO = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
        }

        return UserPrincipal.create(userDTO, oAuth2User.getAttributes());
    }

    private UserDTO registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo) {
        UserDTO userDTO = new UserDTO();
        userDTO.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        userDTO.setProviderId(oAuth2UserInfo.getId());
        userDTO.setUsername(oAuth2UserInfo.getName());
        userDTO.setFirstName(oAuth2UserInfo.getFirstName());
        userDTO.setLastName(oAuth2UserInfo.getLastName());
        userDTO.setEmail(oAuth2UserInfo.getEmail());
        userDTO.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userService.upsertOAuthUser(userDTO);
    }

    //Warning - be careful to not override for example the attendedEvents or the favoriteComedians
    private UserDTO updateExistingUser(UserDTO existingUserDTO, OAuth2UserInfo oAuth2UserInfo) {
        existingUserDTO.setUsername(oAuth2UserInfo.getName());
        existingUserDTO.setFirstName(oAuth2UserInfo.getFirstName());
        existingUserDTO.setLastName(oAuth2UserInfo.getLastName());
        existingUserDTO.setImageUrl(oAuth2UserInfo.getImageUrl());
        return userService.upsertOAuthUser(existingUserDTO);
    }
}
