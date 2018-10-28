package net.stelmaszak.tweedit.validator;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class MatchingPassValidator implements ConstraintValidator<MatchingPass, Object> {

    private String[] passwords;

    @Override
    public void initialize(MatchingPass constraintAnnotation) {
        this.passwords = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {

        BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(o);
        return doTheyMatch(wrapper);
    }

    private boolean doTheyMatch(BeanWrapper wrapper) {
        return wrapper.getPropertyValue(passwords[0]).equals(wrapper.getPropertyValue(passwords[1]));
    }


}
