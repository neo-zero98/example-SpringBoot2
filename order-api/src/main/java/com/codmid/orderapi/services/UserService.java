package com.codmid.orderapi.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.codmid.orderapi.converters.UserConverter;
import com.codmid.orderapi.dtos.LoginRequestDTO;
import com.codmid.orderapi.dtos.LoginResponseDTO;
import com.codmid.orderapi.entity.User;
import com.codmid.orderapi.exceptions.GeneralServiceException;
import com.codmid.orderapi.exceptions.NoDataFoundException;
import com.codmid.orderapi.exceptions.ValidateServiceException;
import com.codmid.orderapi.repository.UserRepository;
import com.codmid.orderapi.validators.UserValidator;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	@Value("${jwt.password}")
	private String jwtSecret;

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private UserConverter userConverter;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public User createUser(User user) {
		try {
			UserValidator.signup(user);
			User existUser = this.userRepo.findByUsername(user.getUsername())
					.orElse(null);
			if(existUser != null) throw new ValidateServiceException("El usuario ya existe");
			user.setPassword(this.passwordEncoder.encode(user.getPassword()));
			return this.userRepo.save(user);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public LoginResponseDTO login(LoginRequestDTO request) {
		try {
			User user = this.userRepo.findByUsername(request.getUsername())
					.orElseThrow(() -> new ValidateServiceException("El usuario o la contraseña no existe"));
			if(!this.passwordEncoder.matches(request.getPassword(), user.getPassword())) throw new ValidateServiceException("El usuario o la contraseña no existe");
			//if(!user.getPassword().equals(request.getPassword())) throw new ValidateServiceException("El usuario o la contraseña no existe");
			String token = createToken(user);
			return new LoginResponseDTO(this.userConverter.fromEntity(user), token);
		} catch (ValidateServiceException | NoDataFoundException e) {
			log.info(e.getMessage(), e);
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new GeneralServiceException(e.getMessage(), e);
		}
	}
	
	public String createToken(User user) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + (1000*60*60));
		
		return Jwts.builder()
				.setSubject(user.getUsername())
				.setIssuedAt(now)
				.setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, this.jwtSecret)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(token);
			return true;
		} catch (UnsupportedJwtException e) {
			log.error("Exception thrown when receiving a JWT in a particular format/configuration that does not match the format expected");
		} catch (MalformedJwtException e) {
			log.error("Exception indicating that a JWT was not correctly constructed and should be rejected");
		} catch (SignatureException e) {
			log.error("Exception indicating that either calculating a signature or verifying an existing signature of a JWT failed");
		}catch (ExpiredJwtException e) {
			log.error("Exception indicating that a JWT was accepted after it expired and must be rejected");
		}
		return false;
	}
	
	public String getUsernameFromToken(String jwt) {
		try {
			return Jwts.parser().setSigningKey(this.jwtSecret).parseClaimsJws(jwt).getBody().getSubject();	
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new ValidateServiceException("Invalid Token");
		}
		
	}
}
