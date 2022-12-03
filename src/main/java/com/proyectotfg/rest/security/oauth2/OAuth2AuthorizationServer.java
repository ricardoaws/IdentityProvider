package com.proyectotfg.rest.security.oauth2;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.sql.DataSource;

@Configuration
// Anotación que sirve para habilitar el servidor OAuth
@EnableAuthorizationServer
// Utilizamos esta notación para realizar la injección por constructorgit
@RequiredArgsConstructor
public class OAuth2AuthorizationServer extends AuthorizationServerConfigurerAdapter{

	private final PasswordEncoder passwordEncoder;
	private final AuthenticationManager authenticationManager;
	private final UserDetailsService userDetailsService;
	// Inyectamos DataSource para poder almacenar los usuarios y los Token en base de datos
	private final DataSource dataSource;

	//Estas variables las cargamos desde elfichero application.properties

	//Variable de indentificador del cliente
	@Value("${oauth2.client-id}")
	private String clientId;
	
	@Value("${oauth2.client-secret}")
	private String clientSecret;
	//Variable de URL de vuelta a nuestra aplicacion
	@Value("${oauth2.redirect-uri}")
	private String redirectUri;
	//Variable de validez del token de acceso
	@Value("${oauth2.access-token-validity-seconds}")
	private int accessTokenValiditySeconds;
	//Variable de validez del token de refresco
	@Value("${oauth2.access-token-validity-seconds}")
	private int refreshTokenValiditySeconds;
	
	

	// Constante para el response_type de "authorization_code"
	private static final String CODE_GRANT_TYPE = "authorization_code";
	// Constante para el response_type  de "implicit"
	private static final String IMPLICIT_GRANT_TYPE = "implicit";
	// Constante para el response_type  de "password"
	private static final String PASS_GRANT_TYPE = "password";
	// Constante para el "refresh_token"
	private static final String REFRESH_TOKEN_GRANT_TYPE = "refresh_token";
	
	
	//En esta clase se definen varios elemento de seguridad
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			// Cualquiera puede tener acceso al servicio de tokenKeyAccess
			.tokenKeyAccess("permitAll()")
			// Solo los usuarios autenticados tendrán acceso al servicio checkTokenAccess
			.checkTokenAccess("isAuthenticated()")
			// Permitimos la autenticación de formulario de clientes
			.allowFormAuthenticationForClients();

	}

	// Este método es usado para el registro de clientes
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients
			//Indicamos que el registro se realiza en una base de datos
			.jdbc(dataSource)
			//Se registra el identificador del cliente de la variable clientID
			.withClient(clientId)
			//Se registra el secreto de la variable clientSecret y la encriptamos
			.secret(passwordEncoder.encode(clientSecret))
			// Se incluyen los GranTypes o tipos de flujo de autenticación, definidos en las constantes de arriba
			.authorizedGrantTypes(PASS_GRANT_TYPE, REFRESH_TOKEN_GRANT_TYPE, IMPLICIT_GRANT_TYPE, CODE_GRANT_TYPE)
			// Serán clientes de solo lectura
			.authorities("READ_ONLY_CLIENT")
			// Se define un scope generico de lectura
			.scopes("read")
			// Se define el identificador de recursos
			.resourceIds("oauth2-resource")
			// Se indica la URL de redirección definida en las variables de arriba
			.redirectUris(redirectUri)
			// Se indica la validez del token de acceso
			.accessTokenValiditySeconds(accessTokenValiditySeconds)
		 	// Se indica la validez del token de refresco
			.refreshTokenValiditySeconds(refreshTokenValiditySeconds);
			
	
	}

	// Se define la configuración de seguridad de los endpoints
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			// Indicamos que el autenticationManager es el que hemos inyectado al principio del código
			.authenticationManager(authenticationManager)
			// Indicamos que el userDetailService es el que hemos inyectado al principio del código
			.userDetailsService(userDetailsService)
			// Almacenamos los tokens en la base de datos
			.tokenStore(tokenStore())
			// Convertimos los access_token en JWT
			.accessTokenConverter(accessTokenConverter());
	}

	// Creamos un bean que será el objeto que utilicemos para almacenar los tokens
	@Bean
	public TokenStore tokenStore() {

		return new JdbcTokenStore(dataSource);
	}

	// Creamos un Bean para convertir los access token en JWT
	@Bean
	public AccessTokenConverter accessTokenConverter() {

		return new JwtAccessTokenConverter();
	}

	
	
	
}
