package com.codmid.orderapi.converters;

import com.codmid.orderapi.dtos.SignupRequesDTO;
import com.codmid.orderapi.dtos.UserDTO;
import com.codmid.orderapi.entity.User;

public class UserConverter extends AbstractConverter<User, UserDTO>{

	@Override
	public UserDTO fromEntity(User entity) {
		if(entity == null) return null;
		return UserDTO.builder()
				.id(entity.getId())
				.username(entity.getUsername())
				.build();
	}

	@Override
	public User fromDTO(UserDTO dto) {
		if(dto == null) return null;
		return User.builder()
				.id(dto.getId())
				.username(dto.getUsername())
				.build();
	}
	
	public User signup(SignupRequesDTO dto) {
		return User.builder()
				.password(dto.getPassword())
				.username(dto.getUsername())
				.build();
	}
	

}
