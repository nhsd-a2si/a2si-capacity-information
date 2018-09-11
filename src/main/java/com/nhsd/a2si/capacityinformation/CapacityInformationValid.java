package com.nhsd.a2si.capacityinformation;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;

@Documented
@Target(TYPE)
@Retention(RUNTIME)
@Constraint(validatedBy = { CapacityInformationValidator.class })

public @interface CapacityInformationValid {
	String message() default "CapacityInformation is invalid";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
