package ru.andrei.tsystemsverificationwork.web.validators.impl;

import ru.andrei.tsystemsverificationwork.web.validators.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validation of e-mail
 */
public class ValidEmailImpl implements ConstraintValidator<ValidEmail, String> {

    /**
     * Minimal string length
     */
    private int min;

    @Override
    public void initialize(ValidEmail constraintAnnotation) {
        min = constraintAnnotation.min();
    }

    /**
     * Checks whether e-mail valid or not
     *
     * @param email   e-mail to be validated
     * @param context validation context
     * @return boolean value of result
     */
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
