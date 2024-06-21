package com.travel.bean;

import jakarta.validation.constraints.Email;
import lombok.Data;
@Data
public class SendOtpEmail {
    @Email(message = "Invalid email format")
	private String email;

}
