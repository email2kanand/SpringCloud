package com.kanand.ms.apigateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kanand.ms.apigateway.filter.AuthenticationFilter;

@Configuration
public class ApiGatewayConfiguration {
	
	@Bean
	public RouteLocator gatewayRouter(RouteLocatorBuilder builder,AuthenticationFilter authenticationFilter) {
		
//		.route(p -> p.path("/currency-exchange/**")
//				.uri("lb://currency-exchange-service"))
//		above is removing ureka service name and directly calls the rest apis in micro-services

//		.route(p -> p.path("/currency-conversion-new/**")
//				.filters(f -> f.rewritePath(
//						"/currency-conversion-new/(?<segment>.*)", 
//						"/currency-conversion-feign/${segment}"))
//				.uri("lb://currency-conversion-service"))
//      above approach helps giving totally different name to api 
		
		
		
		return builder.routes()
				.route(p -> p
						.path("/get")
						.filters(f -> f
								.addRequestHeader("MyHeader", "MyURI")
								.addRequestParameter("Param", "MyValue"))
						.uri("http://httpbin.org:80"))
				.route(p -> p.path("/currency-exchange/**")
						.filters(f-> f.filter(authenticationFilter.apply(new AuthenticationFilter.Config())))
						.uri("lb://currency-exchange-service"))
				.route(p -> p.path("/currency-conversion/**")
						.uri("lb://currency-conversion-service"))
				.route(p -> p.path("/auth/**")
						.uri("lb://identity-service"))
				.route(p -> p.path("/currency-conversion-feign/**")
						.uri("lb://currency-conversion-service"))
				.route(p -> p.path("/currency-conversion-new/**")
						.filters(f -> f.rewritePath(
								"/currency-conversion-new/(?<segment>.*)", 
								"/currency-conversion-feign/${segment}"))
						.uri("lb://currency-conversion-service"))
				.build();
	}

}