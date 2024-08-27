package com.example.contactmessage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class ContactMessageUpdateRequest {

    @NotNull(message = "Please enter name")
    @Size(min=3,max=16, message = "Your names character should be between 3 and 16")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = " Enter valid characters")
    private String name;


    @Size(min=5,max=20, message = "Your email should be between 5 and 20")
    @Email(message = "Enter valid email")
    private String email;



    @Size(min=5,max=20, message = "Your subject should be between 5 and 20")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = " Enter valid characters")
    private String subject;


    @Size(min=5,max=100, message = "Your message should be between 5 and 100")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = " Enter valid characters")
    private String message;
}
