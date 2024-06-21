package com.travel.bean;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmailDetailsDto {

	@Id
	private int id;
	private String ccMail;
	private String subject;
	private String text;
	private String emailMessageTemplateName;

}
