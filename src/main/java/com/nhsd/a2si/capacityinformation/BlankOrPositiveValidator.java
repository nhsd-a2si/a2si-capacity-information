package com.nhsd.a2si.capacityinformation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BlankOrPositiveValidator implements ConstraintValidator<BlankOrPositive, Integer> {
	
    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return (integer == null || integer >= 0);
    }
}
