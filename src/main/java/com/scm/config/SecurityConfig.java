package com.scm.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.service.serviceimpl.CustomSecurityUserDetails;

/**
 * @Sachi9892 -- Security config class
 */

@Configuration
public class SecurityConfig  {
    
    @Autowired
    private CustomSecurityUserDetails userDetailsService;

    /**
     * @implNote = In Memory User -
     * 
     *           In Spring Security, an "in-memory" user is a user whose login
     *           information (like username, password, and roles) is stored directly
     *           in the application's code instead of being saved in a database or
     *           other external system. This method is often used for development or
     *           testing because it's easy and quick to set up. NOT FOR THE
     *           PRODUCTION LEVEL
     * 
     * 
     * @Bean
     *       public UserDetailsService userDetailsService() {
     * 
     *       UserDetails user1 =
     *       User.withUsername("admin").password("123").roles("ADMIN").build();
     * 
     *       var inMemoryUser = new InMemoryUserDetailsManager(user1);
     * 
     *       return inMemoryUser;
     *       }
     */


    //Step 1. Make user class USERDETAILS class and implement all the methods
    //Step 2. Create Service class to implement the USERDETAILSSERVICE to get user by username
    //Step 3. 

    /**
     * @Sachi9892 -- Here we are encoding the user details saved in database -
     *            for a production-level application, using an external user store
     *            like a database and encoding passwords is a best practice..
     *            for this we need three things DaoAutentication , PaswordEncoder , 
     *            UserDetails for which we will implement the UserDetails interface
     *            By class which is our USER class.
     */

    @Bean
    public AuthenticationProvider authenticationProvider() {
        
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        
        //user details object
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);

        // When saving a user, you encode their password before storing it in the
        // database: Here, the password encoder ensures that the user's password is
        // stored securely in an encoded (hashed) format in the database.

        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;

    }


    /**
     * 
     * @throws Exception 
     * @Sachi9892 - Since our all routes are blocked now , as we don't have any
     *                   data in database so we need to make some route as public
     * 
     * @implNote Security Filter Chain -
     *           
     *           In Spring Security, a SecurityFilterChain defines a chain of
     *           filters that applies to specific HTTP requests. It is used to
     *           configure security settings such as authentication, authorization,
     *           and other security-related aspects for these requests.
     */
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        //This method will public only specified uris
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/user/**").authenticated();
            auth.anyRequest().permitAll();
        });


        //We need to configure LogIn form , but for now we are using by default configuration
        http.formLogin(Customizer.withDefaults());

        //To configure forms
        http.formLogin(form -> {

            form.loginPage("/login");
            form.loginProcessingUrl("/authenticate");
            form.successForwardUrl("/user/profile");
            // form.failureForwardUrl("/login?error=true");
            // form.defaultSuccessUrl("/home");
            form.usernameParameter("email");
            form.passwordParameter("password");

        });

        return  http.build();

    }




    //Encrypt password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
    }



}
