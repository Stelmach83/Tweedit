package net.stelmaszak.tweedit.validator;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = MatchingPassValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MatchingPass {
    String[] value();

    String message() default "Passwords have to be matching.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
