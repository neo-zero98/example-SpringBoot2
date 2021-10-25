package com.codmid.orderapi.validators;

import com.codmid.orderapi.entity.User;
import com.codmid.orderapi.exceptions.ValidateServiceException;

public class UserValidator {

	public static void signup(User user) {
		if(user.getUsername() == null || user.getUsername().trim().isEmpty()) {
			throw new ValidateServiceException("El nombre del usuario es requerido");
		}
		if(user.getUsername().length() > 30) {
			throw new ValidateServiceException("El nombre del usuario es muy largo (max 30)");
		}
		if(user.getPassword() == null || user.getPassword().isEmpty()) {
			throw new ValidateServiceException("El password es requerido");
		}
		if(user.getPassword().length() > 30) {
			throw new ValidateServiceException("El password es muy largo (max 30)");
		}
		
	}
}
