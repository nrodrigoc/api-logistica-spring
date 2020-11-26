package app.spring.caminhoneiro.rest.validation.constraintvalidation;

import app.spring.caminhoneiro.rest.validation.NotEmptySet;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

public class NotEmptySetValidation implements ConstraintValidator<NotEmptySet, Set> {
    @Override
    public void initialize(NotEmptySet constraintAnnotation) {

    }

    @Override
    public boolean isValid(Set set, ConstraintValidatorContext context) {
        return set != null && !set.isEmpty();
    }
}
