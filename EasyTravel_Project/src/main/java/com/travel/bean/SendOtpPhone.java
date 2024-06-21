package com.travel.bean;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SendOtpPhone {
    @Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$", message = "Phone number should be digits")
	private String phone;

}
