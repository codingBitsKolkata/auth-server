//package com.orastays.authserver.security;
//
//import java.security.KeyPair;
//import java.util.Arrays;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancer;
//import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
//import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
//import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
//import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//import org.springframework.web.servlet.view.RedirectView;
//
//@EnableAuthorizationServer
//@Configuration
//public class OAuth2Config extends AuthorizationServerConfigurerAdapter {
//
//	private String clientId = "web-app";
//	private String clientSecret = "secret";
//
//	@Autowired
//	@Qualifier("authenticationManagerBean")
//	private AuthenticationManager authenticationManager;
//
//	@Bean
//	public JwtAccessTokenConverter accessTokenConverter() {
//		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//		KeyPair keyPair = new KeyStoreKeyFactory(new ClassPathResource("keystore.jks"), "foobar".toCharArray())
//				.getKeyPair("test");
//		converter.setKeyPair(keyPair);
//		return converter;
//	}
//
//	@Bean
//	public TokenEnhancer tokenEnhancer() {
//		return new CustomTokenEnhancer();
//	}
//
//	@Bean
//	public JwtTokenStore tokenStore() {
//		return new JwtTokenStore(accessTokenConverter());
//	}
//
//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
//		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(tokenEnhancer(), accessTokenConverter()));
//
//		endpoints.tokenStore(tokenStore()).tokenEnhancer(tokenEnhancerChain)
//				.authenticationManager(authenticationManager);
//
//		endpoints.addInterceptor(new HandlerInterceptorAdapter() {
//			@Override
//			public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//					ModelAndView modelAndView) throws Exception {
//				if (modelAndView != null && modelAndView.getView() instanceof RedirectView) {
//					RedirectView redirect = (RedirectView) modelAndView.getView();
//					String url = redirect.getUrl();
//					if (url.contains("token=") || url.contains("error=")) {
//						HttpSession session = request.getSession(false);
//						if (session != null) {
//							session.invalidate();
//						}
//					}
//				}
//			}
//		});
//	}
//
//	@Override
//	public void configure(final AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//		oauthServer.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
//	}
//
//	@Override
//	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//		clients.inMemory().withClient(clientId).secret(clientSecret).scopes("read", "write")
//				.authorizedGrantTypes("password", "refresh_token", "implicit").accessTokenValiditySeconds(20000000)
//				.refreshTokenValiditySeconds(20000000);
//
//	}
//}
