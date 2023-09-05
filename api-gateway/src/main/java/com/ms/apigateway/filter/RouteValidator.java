package com.ms.apigateway.filter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	public static final  List<String>  openApiEndPoints = Arrays.asList(
			"/auth/register",
			"/auth/token",
			"/eureka"
			);
	
    public Predicate<ServerHttpRequest> isSecured = request -> openApiEndPoints
    		.stream()
    		.noneMatch(uri  -> request.getURI().getPath().contains(uri));


}
