package ru.andrei.tsystemsverificationwork.web.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ru.andrei.tsystemsverificationwork.web.validators.impl.ValidEmailImpl.class)
public @interface ValidEmail {

    String message() default "This is not a valid e-mail address";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 5;
}
