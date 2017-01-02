/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ari.prasetiyo.chat.service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/*
Request :
default : curl localhost:8080/oauth/token -X POST -d "grant_type=password&scope=read&username=ari&password=ari" -u clientapp:123456
curl localhost:8080/openyoureye/oauth/token -X POST -d "grant_type=password&scope=read&username=ari&password=ari" -u client_storage:Victornya123

curl localhost:8080/oauth/token -X POST -d "grant_type=refresh_token&refresh_token=9b981b61-872b-4266-a0cb-d5f2ec594fed" -u clientapp:123456

Response :
{
  "access_token" : "19becb49-368e-4edf-828c-59ee6915d1c3",
  "token_type" : "bearer",
  "refresh_token" : "d5fb4a26-38ae-4889-8bb1-ee3853a18d81",
  "expires_in" : 43199,
  "scope" : "read"
}

curl -H "Authorization: bearer 9b981b61-872b-4266-a0cb-d5f2ec594fed" localhost:8080/api/testConnection

 */
@Configuration
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class configurationOauth2 extends WebSecurityConfigurerAdapter {

    //private static final String RESOURCE_ID = "restservice";
    //for configure user and password login
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth
                .jdbcAuthentication()
                .dataSource(dS)
                .usersByUsernameQuery(sqlLogin)
                .authoritiesByUsernameQuery(sqlRole);
         */

        //for using apps connect
        auth
                .inMemoryAuthentication()
                
                .withUser("ari").password("ari").roles("ADMIN", "USER")
                .and()
                .withUser("ira").password("ira").roles("USER");
    }

    @Configuration
    @EnableResourceServer
    protected static class ResourceServerConfiguration extends
            ResourceServerConfigurerAdapter {

        @Override
        public void configure(ResourceServerSecurityConfigurer resources) {
            // @formatter:off
            resources
                    .resourceId("test");
            int a = 0;
            // @formatter:on
        }

        @Override
        public void configure(HttpSecurity http) throws Exception {
            // @formatter:off
            /*
            http
                    .authorizeRequests()
                    .antMatchers("/api/*", "/api/**").hasRole("USER")
                    .anyRequest().authenticated();
            // @formatter:on
             */
            // @formatter:off
            http
                    // Just for laughs, apply OAuth protection to only 2 resources
                    .authorizeRequests().antMatchers("/api", "/api/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
                    .anyRequest().access("#oauth2.hasScope('read')");
        }
    }

    @Configuration
    @EnableAuthorizationServer
    protected static class AuthorizationServerConfiguration extends
            AuthorizationServerConfigurerAdapter {

        private final TokenStore tokenStore = new InMemoryTokenStore();

        @Autowired
        private AuthenticationManager authenticationManager;

        @Override
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authenticationManager)
                    .pathMapping("/oauth/token", "/openyoureye/oauth/token");
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
            clients
                    
                    .inMemory()
                    /*
                    client_id
                    clientapp
                    */
                    .withClient("client_storage")
                    //.scopes("read", "write", "trust")
                    .scopes("read")
                    //.accessTokenValiditySeconds(60)
                    //password and refresh_token
                    //.authorizedGrantTypes("password", "refresh_token")
                    .authorizedGrantTypes("password")
                    .authorities("ADMIN")
                    .resourceIds("test")
       
                    .accessTokenValiditySeconds(60 * 5)
                    //clientapp:victornya@localhost
                    .secret("Victornya123");
        }

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            //oauthServer.realm("sparklr2/client");
            oauthServer.allowFormAuthenticationForClients();
        }
        
       

    }
}
