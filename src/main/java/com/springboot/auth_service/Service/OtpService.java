package com.springboot.auth_service.Service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OtpService {
	
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	private final Random random = new Random();
	
	
	public String generateOtp(){
		int otp = 100000 + random.nextInt(900000);
		
		return String.valueOf(otp);
	}
	
	
	public void sendOtp(String phoneNumber, String otp){
		kafkaTemplate.send("otp_topic", phoneNumber, otp);
	}

}
