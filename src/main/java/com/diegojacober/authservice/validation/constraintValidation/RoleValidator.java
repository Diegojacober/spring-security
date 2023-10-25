package com.diegojacober.authservice.validation.constraintValidation;

import com.diegojacober.authservice.user.Role;
import com.diegojacober.authservice.validation.ValidRole;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RoleValidator implements ConstraintValidator<ValidRole, Role> {

    @Override
    public void initialize(ValidRole constraintAnnotation) {
    }

    @Override
    public boolean isValid(Role value, ConstraintValidatorContext context) {
        if (value == null) {
            return false; // Valor nulo não é válido
        }

        // Verifica se o valor está presente no enum Role
        for (Role role : Role.values()) {
            if (role.equals(value)) {
                return true; // Valor válido
            }
        }

        return false; // Valor inválido
    }
}


