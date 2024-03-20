package com.devok.giggz.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.devok.giggz.service.auth.JwtAuthFilter;
import com.devok.giggz.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    JwtAuthFilter jwtAuthFilter;


    //    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeHttpRequests(authorize -> authorize
////                        .anyRequest().authenticated()
////                ).oauth2Login(Customizer.withDefaults());
////        return http.build();
////        http
////                .authorizeRequests()
////                .requestMatchers("/", "/login", "/oauth/**").permitAll()
////                .anyRequest().authenticated()
////                .and()
////                .formLogin()
////                .loginPage("/login")
////                .permitAll()
////                .and()
////                .oauth2Login()
////                .loginPage("/login")
////                .userInfoEndpoint()
////                .userService(oAuth2UserService)
////                .and()
////                .successHandler(new AuthenticationSuccessHandler() {
////                    @Override
////                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
////                                                        Authentication authentication) throws IOException {
////                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
////                        userService.processOAuthPostLogin(oauthUser.getEmail());
////                        response.sendRedirect("/events");
////                    }
////                });
//
//
//        //before jwt
////        return http.cors(Customizer.withDefaults())
////                .csrf(AbstractHttpConfigurer::disable)
////                .authorizeHttpRequests(auth -> {
////                    try {
////                        auth.requestMatchers("/**")
////                                .permitAll()
////                                .anyRequest().authenticated()
////                                .and().httpBasic().and()
////                                .addFilter(new JwtAuthenticationFilter(authenticationManager, jwtTokenProvider))
////                                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // Disable sessions
////                        ;
////                    } catch (Exception e) {
////                        throw new RuntimeException(e);
////                    }
////                })
////                .build();
//    }


//    @Bean
//    protected AuthenticationManager configure(AuthenticationManagerBuilder auth) throws Exception {
//        return auth.userDetailsService(userService).passwordEncoder(passwordEncoder()).and().build();
//    }

    //TODO check if this approach opens a security breach
    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }







    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http.cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> {
                    try {
                        auth.requestMatchers("/**")
                                .permitAll()
                                .anyRequest().authenticated()
                                .and().httpBasic()
                                .and()
                                .sessionManagement()
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                                .and()
                                .authenticationProvider(authenticationProvider())
                                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                        ;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .build();
//        return http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers( "/api/login", "/api/v1/refreshToken").permitAll()
//                .and()
//                .authorizeHttpRequests().requestMatchers("/**")
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider())
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
