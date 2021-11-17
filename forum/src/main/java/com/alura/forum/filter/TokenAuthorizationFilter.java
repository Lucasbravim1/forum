package com.alura.forum.filter;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alura.forum.model.User;
import com.alura.forum.repository.UserRepository;
import com.alura.forum.service.TokenService;

public class TokenAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TokenService tokenService;

	public TokenAuthorizationFilter(TokenService tokenService, UserRepository usuarioRepository) {
		this.tokenService = tokenService;
		this.userRepository = usuarioRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = getToken(request);
		boolean validateToken = tokenService.validateToken(token);

		if (validateToken) {
			authenticateUser(token);
		}
		filterChain.doFilter(request, response);

	}

	public String getToken(HttpServletRequest request) {

		String header = request.getHeader("Authorization");

		if (header == null || !(header.substring(0, 6).equals("Bearer")) || header.isEmpty()) {
			return null;
		} else {
			String token = header.substring(7, header.length());
			return token;
		}

	}

	public void authenticateUser(String token) {

		Long userID = tokenService.getUserID(token);
		Optional<User> user = userRepository.findById(userID);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(), null,
				user.get().getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

}
