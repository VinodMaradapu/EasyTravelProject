package com.travel.bean;

import lombok.Data;
@Data
public class ForgotPasswordRequestDTO {

	private String enterNewPassword;
	private String confirmNewPassword;
}
