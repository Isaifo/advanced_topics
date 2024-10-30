package com.aula_23_10.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonDTO {
    
   @Size(min = 2,max=100)
   @NotEmpty 
    private String name;

    @Size(min= 2, max = 80)
    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Size(min= 3, max = 30)
    private String password;

    
}
