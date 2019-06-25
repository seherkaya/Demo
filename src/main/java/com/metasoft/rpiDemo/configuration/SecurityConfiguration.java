package com.metasoft.rpiDemo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {



    @Autowired
    private DataSource dataSource;

    @Value("${spring.queries.users-query}")
    private String usersQuery;

    @Value("${spring.queries.roles-query}")
    private String rolesQuery;

    @Autowired
    private AuthenticationProviderConfig authProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
//        auth.
//                jdbcAuthentication()
//                .usersByUsernameQuery(usersQuery)
//                .authoritiesByUsernameQuery(rolesQuery)
//                .dataSource(dataSource);


        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                //ADMIN

               // .antMatchers( "admin/home" ).hasAuthority( "ADMIN" )

                .antMatchers( "/login" ).permitAll()
                .antMatchers( "/me" ).hasAnyAuthority("ADMIN")

                .antMatchers( "/loginAPI" ).permitAll()
                .antMatchers( "/admin/home" ).hasAuthority("ADMIN")

                //User

                .antMatchers( "/admin/userlistAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/addUserAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/userDeleteAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/enrollUserKeyAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/updateUserAPI" ).hasAuthority("ADMIN")

                //Role

                .antMatchers( "/admin/role" ).permitAll()
                .antMatchers( "/admin/rolesListAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/addRoleAPI" ).permitAll()
                .antMatchers( "/admin/roleDeleteAPI" ).permitAll()
                .antMatchers( "/admin/updateRoleAPI" ).permitAll()

                //Key

                .antMatchers( "/admin/key" ).permitAll()
                .antMatchers( "/admin/availableKeyAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/keysListAPI" ).hasAuthority("ADMIN")
                .antMatchers( "/admin/keyDeleteAPI" ).permitAll()
                .antMatchers( "/admin/updateKeyAPI" ).permitAll()
                .antMatchers( "/admin/addKeyAPI" ).permitAll()


                .antMatchers( "/admin/system" ).permitAll()

                .antMatchers("/admin/**").hasAuthority("ADMIN").anyRequest().authenticated()

                .and()
                .formLogin().loginPage( "/login" )
                .and()
                .httpBasic()
                .and()
                .logout()
                .clearAuthentication(true)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint( HttpStatus.FORBIDDEN))
                .and()
                .sessionManagement()
                .invalidSessionUrl("/login")
                .sessionAuthenticationErrorUrl("/login")
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry());

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers( "/resources/**", "/static/**", "/css/**", "/js/**", "/images/**" );
    }

    @Bean
    SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}
