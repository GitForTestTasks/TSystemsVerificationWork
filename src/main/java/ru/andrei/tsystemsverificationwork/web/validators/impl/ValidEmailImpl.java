package ru.andrei.tsystemsverificationwork.web.validators.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ru.andrei.tsystemsverificationwork.web.validators.ValidEmail;

public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

    private int min;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email.length() < min) {
            return false;
        }

        return email.matches(
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
    }

}
