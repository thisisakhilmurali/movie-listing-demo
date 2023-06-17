package com.ust.client.controller;
import java.util.List;

import com.ust.client.domain.JwtRequest;
import com.ust.client.domain.JwtResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ust.client.feignInterface.FeignInterface;


@RestController
@RequestMapping("/auth")
public class ClientController {
	
	@Autowired
	FeignInterface interface1;

	@PostMapping("/request")
	public ResponseEntity<JwtResponse> viewData(@RequestBody JwtRequest jwtRequest) {
		return interface1.postEndpoint(jwtRequest);
	}
	
	
}
