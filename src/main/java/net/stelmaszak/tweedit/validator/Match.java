package net.stelmaszak.tweedit.validator;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Match {

    String field();

    String message() default "";
}