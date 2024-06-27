/*package com.springboot.auth_service;



import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.springboot.auth_service.Controller.AuthController;
import com.springboot.auth_service.Entity.User;
import com.springboot.auth_service.Model.AuthenticationRequest;
import com.springboot.auth_service.Security.JwtUtil;
import com.springboot.auth_service.Service.MyUserDetailsService;
import com.springboot.auth_service.Service.OtpService;

@WebMvcTest(AuthController.class)
public class AuthControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AuthenticationManager authenticationManager;

	@MockBean
	private MyUserDetailsService userDetailsService;

	@MockBean
	private JwtUtil jwtUtil;

	@MockBean
	private OtpService otpService;

	@Test
	public void testRegister() throws Exception {

		User user = new User();

		user.setUsername("testuser");
		user.setPassword("password");
		user.setPhoneNumber("1234567890");
		
		
		MockMvc.perform(MockMvcRequestBuilders.post("/api/auth/register")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"username\": \"testuser\", \"password\": \"password\", \"phoneNumber\": \"1234567890\"}")
				.andExpect(status().isOk())
				.andExpect(content().string("Otp is sent to your phone"));
	}

	@Test
    public void testLogin() throws Exception {
        AuthenticationRequest request = new AuthenticationRequest("testuser", "password");
        UserDetails userDetails = Mockito.mock(UserDetails.class);

        Mockito.when(userDetailsService.loadUserByUsername("testuser")).thenReturn(userDetails);
        Mockito.when(jwtUtil.generateToken(userDetails)).thenReturn("dummy-jwt-token");
        Mockito.when(otpService.generateOtp()).thenReturn("123456");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"testuser\", \"password\": \"password\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("OTP sent to your phone"));
    }

    @Test
    public void testLoginWithInvalidCredentials() throws Exception {
        Mockito.doThrow(new BadCredentialsException("Invalid credentials"))
                .when(authenticationManager)
                .authenticate(Mockito.any());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"wronguser\", \"password\": \"wrongpassword\"}"))
                .andExpect(status().isUnauthorized());
    }

}
*/