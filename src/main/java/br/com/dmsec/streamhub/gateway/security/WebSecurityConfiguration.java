package br.com.dmsec.streamhub.gateway.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.util.matcher.PathPatternParserServerWebExchangeMatcher;


import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfiguration {


    @Order(1)
    @Bean
    SecurityWebFilterChain actuatorHttpSecurity(ServerHttpSecurity http) {
        System.out.println("actuatorHttpSecurity");
        http
               // .securityMatcher(new PathPatternParserServerWebExchangeMatcher("/actuator/**"))
                .headers(header -> header.hsts().disable())
                .authorizeExchange((exchanges) -> exchanges
                .pathMatchers("/actuator/**",  "/logout.html").permitAll()
                .anyExchange().permitAll());
        return http.build();
    }



    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http, ServerLogoutSuccessHandler handler) {
      http
          //.securityMatcher(new PathPatternParserServerWebExchangeMatcher("/abc/**"))
          .headers(header -> header.hsts().disable())
          .authorizeExchange((exchanges) -> exchanges
              .anyExchange().authenticated().and().oauth2Login().and().logout().logoutSuccessHandler(handler));
          //.oauth2Login();
          //.logout()
         // .logoutSuccessHandler(handler);

              // to redirect to oauth2 login page.


      return http.build();
    }



    @Bean
    public ServerLogoutSuccessHandler keycloakLogoutSuccessHandler(ReactiveClientRegistrationRepository repository) {

        OidcClientInitiatedServerLogoutSuccessHandler oidcLogoutSuccessHandler =
            new OidcClientInitiatedServerLogoutSuccessHandler(repository);

        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logout.html");

        return oidcLogoutSuccessHandler;
    }



    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        return NimbusReactiveJwtDecoder.withJwkSetUri("http://localhost:8080/realms/My-Realm/protocol/openid-connect/certs").build();
    }


    @Bean
    SecurityWebFilterChain apiHttpSecurity(ServerHttpSecurity http) {
        System.out.println("apiHttpSecurity");
        http
                .headers(header -> header.hsts().disable())
                .authorizeExchange((exchanges) -> exchanges
                        .anyExchange().authenticated())
                .oauth2ResourceServer(ServerHttpSecurity.OAuth2ResourceServerSpec::jwt)
                .csrf(ServerHttpSecurity.CsrfSpec::disable);

        return http.build();
    }

}
