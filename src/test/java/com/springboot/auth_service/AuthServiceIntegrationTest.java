/*package com.springboot.auth_service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.springboot.auth_service.Entity.User;
import com.springboot.auth_service.Model.AuthenticationRequest;
import com.springboot.auth_service.Model.AuthenticationResponse;
import com.springboot.auth_service.Repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthServiceIntegrationTest {
	
	
	@LocalServerPort
	private int port;
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	private RestTemplate restTemplate = new RestTemplate();
	
	@Test
	public void testRegisterAndLogin() {
		
		String baseUrl = "http://localhost:" + port + "/api/auth";
		
		
		User user = new User();
        user.setUsername("testuser");
        user.setPassword("password");
        user.setPhoneNumber("1234567890");
        
        
        HttpHeaders headers = new HttpHeaders();
        
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        
        HttpEntity<User> request = new HttpEntity<>(user, headers);
        
        ResponseEntity<String> response = restTemplate.postForEntity(baseUrl + "register", request, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("OTP sent to your phone");
        
        
     // Authenticate the user
        AuthenticationRequest authRequest = new AuthenticationRequest("testuser", "password");
        
        
        HttpEntity<AuthenticationRequest> authRequestEntity = new HttpEntity<>(authRequest, headers);
        ResponseEntity<String> authResponse = restTemplate.postForEntity(baseUrl + "/login", authRequestEntity, String.class);

        assertThat(authResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(authResponse.getBody()).isEqualTo("OTP sent to your phone");

        
        
        String otp = "123456";
        ResponseEntity<AuthenticationResponse> verifyOtpResponse = restTemplate.postForEntity(
                baseUrl + "/verify-otp?username=testuser&otp=" + otp, null, AuthenticationResponse.class);

        assertThat(verifyOtpResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(verifyOtpResponse.getBody().getJwt()).isNotNull();
    }
	




	}

*/