package com.diegojacober.authservice.auth;

import com.diegojacober.authservice.user.Role;
import com.diegojacober.authservice.validation.ValidRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {

    @NotBlank(message = "firstname is required.")
    private String firstname;

    @NotBlank(message = "lastname is required.")
    private String lastname;

    @NotBlank(message = "email is required.")
    @Email(message = "O campo deve ser um email válido")
    private String email;

    @NotBlank(message = "password is required.")
    private String password;

    @ValidRole(message = "Defina as roles do usuário")
    private Role role;
}
