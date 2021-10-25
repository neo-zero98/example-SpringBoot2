package com.codmid.orderapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.codmid.orderapi.converters.UserConverter;
import com.codmid.orderapi.dtos.LoginRequestDTO;
import com.codmid.orderapi.dtos.LoginResponseDTO;
import com.codmid.orderapi.dtos.SignupRequesDTO;
import com.codmid.orderapi.dtos.UserDTO;
import com.codmid.orderapi.entity.User;
import com.codmid.orderapi.services.UserService;
import com.codmid.orderapi.utils.WrapperResponse;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserConverter userConverter;
	
	@PostMapping(value = "/signup")
	public ResponseEntity<WrapperResponse<UserDTO>> signup(@RequestBody SignupRequesDTO request){
		User user = this.userService.createUser(this.userConverter.signup(request));
		return new WrapperResponse<>(true, "success", this.userConverter.fromEntity(user))
				.createResponse();
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<WrapperResponse<LoginResponseDTO>> login(@RequestBody LoginRequestDTO request){
		LoginResponseDTO response = this.userService.login(request);
		return new WrapperResponse<>(true,"success",response)
				.createResponse();
	}
}
