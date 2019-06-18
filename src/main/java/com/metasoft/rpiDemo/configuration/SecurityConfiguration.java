package com.metasoft.rpiDemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomAuthenticationProvider authProvider;


    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests()
                .antMatchers( "/" ).permitAll()

                //ADMIN
               // .antMatchers( "admin/home" ).hasAuthority( "ADMIN" )

                .antMatchers( "/login" ).permitAll()
                .antMatchers( "/loginAPI" ).permitAll()
                .antMatchers( "/admin/home" ).permitAll()

                //User

                .antMatchers( "/admin/userlistAPI" ).permitAll()
                .antMatchers( "/admin/addUserAPI" ).permitAll()
                .antMatchers( "/admin/userDeleteAPI" ).permitAll()
                .antMatchers( "/admin/enrollUserEnvironmentAPI" ).permitAll()
                .antMatchers( "/admin/updateUserAPI" ).permitAll()

                //Role

                .antMatchers( "/admin/role" ).permitAll()
                .antMatchers( "/admin/rolesListAPI" ).permitAll()
                .antMatchers( "/admin/addRoleAPI" ).permitAll()
                .antMatchers( "/admin/roleDeleteAPI" ).permitAll()
                .antMatchers( "/admin/updateRoleAPI" ).permitAll()

                //Key

                .antMatchers( "/admin/key" ).permitAll()
                .antMatchers( "/admin/availableKeyAPI" ).permitAll()
                .antMatchers( "/admin/keysListAPI" ).permitAll()
                .antMatchers( "/admin/keyDeleteAPI" ).permitAll()
                .antMatchers( "/admin/updateKeyAPI" ).permitAll()
                .antMatchers( "/admin/addKeyAPI" ).permitAll()


                .antMatchers( "/admin/system" ).permitAll()


                .and().csrf().disable().formLogin()
                .loginPage("/login").failureUrl("/login?error=true")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and().exceptionHandling()
                .accessDeniedPage("/access-denied");
                /*.antMatchers( "/admin/**" ).hasAuthority( "ADMIN" ).anyRequest()*/


    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers( "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**" );
    }
}
