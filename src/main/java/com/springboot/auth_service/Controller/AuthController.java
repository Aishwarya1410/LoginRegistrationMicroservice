package com.springboot.auth_service.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.auth_service.Entity.User;
import com.springboot.auth_service.Model.AuthenticationRequest;
import com.springboot.auth_service.Model.AuthenticationResponse;
import com.springboot.auth_service.Repository.UserRepository;
import com.springboot.auth_service.Security.JwtUtil;
import com.springboot.auth_service.Service.MyUserDetailsService;
import com.springboot.auth_service.Service.OtpService;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OtpService otpService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		userRepository.save(user);
		return ResponseEntity.ok("User registred successfully");
	}

	@PostMapping("/login")
	public ResponseEntity<String> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(401).body("Incorrect username or password");
		} catch (Exception e) {
			return ResponseEntity.status(500).body("An error occurred during authentication");
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails.getUsername());
		
		String otp = otpService.generateOtp();
		otpService.sendOtp(authenticationRequest.getUsername(), otp);
		
		return ResponseEntity.ok("OTP sent to your phone");

		//return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}

	@PostMapping("/verify-otp")
    public AuthenticationResponse verifyOtp(@RequestParam String username, @RequestParam String otp) {
        // Implement OTP verification logic here
        // If OTP is correct, generate and return JWT
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String jwt = jwtUtil.generateToken(username);
        return new AuthenticationResponse(jwt);
    }

}
