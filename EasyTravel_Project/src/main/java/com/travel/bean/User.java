package com.travel.bean;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "users") 
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Email(message = "Invalid email format")
    private String email;
	
    @Pattern(regexp = "^(?:(?:\\+|0{0,2})91(\\s*[\\-]\\s*)?|[0]?)?[789]\\d{9}$", message = "Phone number should be 10 digits")
    private String phone;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;
    
    @NotBlank(message = "address cannot be blank")
    private String address;

    @NotNull(message = "Date of birth cannot be null")
    private Date dateOfBirth; 
    
    @NotBlank(message = "Password cannot be blank")
    private String password;
    
    private String photo;
    
    @ManyToOne
    @JoinColumn(name = "roleId")
    private Role role;
}
