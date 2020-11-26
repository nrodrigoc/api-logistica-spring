package com.nrodrigoc.logisticsapp.validation;

import com.nrodrigoc.logisticsapp.validation.contraintvalidation.NotEmptySetValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptySetValidation.class)
public @interface NotEmptySet {

    String message() default "A lista não pode ser vazia.";

    //Métodos padrão para annotations de validação
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
