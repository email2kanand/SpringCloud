package com.ms.apigateway.filter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.ms.apigateway.util.JwtUtil;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	@Autowired
	private RouteValidator routeValidator;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	
	
	public AuthenticationFilter() {
		super(Config.class);
	}
	public static class Config {

	}

	@Override
	public GatewayFilter apply(Config config) {
		return ((exchange,chain) ->{
			
			if(routeValidator.isSecured.test(exchange.getRequest())) {
				if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
				 throw new RuntimeException("missing authorisation header");	
				}
       		String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
			 if(authHeader!= null && authHeader.startsWith("Bearer ")) {
				 authHeader = authHeader.substring(7);
			 }	
			 try {
				//restTemplate.getForObject("http://identity-service/auth/validate?token="+authHeader, String.class);
				 // above can be security breach so better to validate hear itself
				 jwtUtil.validateToken(authHeader);
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException("Unauthorized Access to application");
			}
			}
			
			return chain.filter(exchange);
		});
	}
}
