package com.nhsd.a2si.capacityinformation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = BlankOrWithinTheLast30MinutesValidator.class)
@Target({METHOD, FIELD})
@Retention(RUNTIME)
public @interface BlankOrWithinTheLast30Minutes {
    String message() default "Last updated must be within the last 30 minutes and formatted as yyyy-MM-dd HH:mm:ss or null.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
