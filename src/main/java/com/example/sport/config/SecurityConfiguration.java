package com.example.sport.config;

import com.example.sport.services.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//https://www.youtube.com/watch?v=9J-b6OlPy24&list=LL&index=1&t=2220s

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private MyUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(registry->{
//                    ca sa poot testa anumite functionalitati am permis accesul pentru anumite fisiere/pagini (", /friends/**")
//                    registry.requestMatchers("/", "/register/**", "/css/**", "/images/**", "/friends/**", "/training/**", "/dashboard", "/maps", "/food").permitAll();
                    registry.requestMatchers("/css/**", "/images/**", "/user/index", "/maps1/**", "/training1/**", "/food1/**", "/dashboard1/**", "/friends1/**", "/food-details1").permitAll();
// TREBUIE DECOMENTAT CA SA MEARGA SECURITATEA
                    registry.requestMatchers("/user/**");
                    registry.anyRequest().authenticated();
// TREBUIE COMENTAT CA SA MEARGA SECURITATEA
//                    registry.anyRequest().permitAll();
                })
//TREBUIE DECOMENTAT CA SA MEARGA SECURITATEA
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer
                            .loginPage("/login")
                            //ca sa ma redirectioneze direct pe /user/index
                            .defaultSuccessUrl("/user/index", true)
                            .permitAll();
                })
                //fara asta
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailService;
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
