package net.stelmaszak.tweedit.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = UniqueUserValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUser {
    //    String value();

    String message() default "This username is taken.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
