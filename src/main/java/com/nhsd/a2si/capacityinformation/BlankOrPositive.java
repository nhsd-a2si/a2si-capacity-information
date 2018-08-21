package com.nhsd.a2si.capacityinformation;

import javax.validation.Constraint;
import javax.validation.ConstraintTarget;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BlankOrPositiveValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface BlankOrPositive {
    String message() default "The value can be null or positive";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
