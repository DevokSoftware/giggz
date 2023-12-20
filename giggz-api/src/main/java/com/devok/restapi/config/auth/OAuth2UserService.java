package com.devok.restapi.config.auth;//package com.devok.auth;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.devok.service.repository.UserRepository;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class OAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
//        Oauth2UserInfoDto userInfoDto = Oauth2UserInfoDto
//                .builder()
//                .name(oAuth2User.getAttributes().get("name").toString())
//                .id(oAuth2User.getAttributes().get("sub").toString())
//                .email(oAuth2User.getAttributes().get("email").toString())
//                .build();
//
//        Optional<User> userOptional = userRepository.findByUsername(userInfoDto.getEmail());
//
//        User user = userOptional
//                .map(existingUser -> updateExistingUser(existingUser, userInfoDto))
//                .orElseGet(() -> registerNewUser(oAuth2UserRequest, userInfoDto));
//        return UserPrincipal.create(user, oAuth2User.getAttributes());
        return null;
    }

//    private User registerNewUser(OAuth2UserRequest oAuth2UserRequest, Oauth2UserInfoDto userInfoDto) {
//        User user = new User();
//        user.setProvider(oAuth2UserRequest.getClientRegistration().getRegistrationId());
//        user.setName(userInfoDto.getName());
//        user.setUsername(userInfoDto.getEmail());
//        user.setId(UUID.randomUUID());
//        return userRepository.save(user);
//    }

//    private User updateExistingUser(User existingUser, Oauth2UserInfoDto userInfoDto) {
//        existingUser.setName(userInfoDto.getName());
//        existingUser.setPicture(userInfoDto.getPicture());
//        return userRepository.save(existingUser);
//    }

}