package com.nhsd.a2si.capacityinformation;

import static com.nhsd.a2si.capacityinformation.domain.CapacityInformation.STRING_DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nhsd.a2si.capacityinformation.domain.CapacityInformation;

public class CapacityInformationValidator implements ConstraintValidator<CapacityInformationValid, CapacityInformation> {
	
	CapacityInformationValid civ;
	
	@Override
    public void initialize(CapacityInformationValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(CapacityInformation ci, 
                           ConstraintValidatorContext constraintValidatorContext) {
    	
    	// Validate that the lastUpdated is supplied and that it is within timeToLiveSecs
    	int timeToLiveSecs = ci.getTimeToLiveSecs();
    	if (ci.getLastUpdated() != null && ci.getLastUpdated().trim().length() > 0) {

    		try {
    			Date lastUpdated = new SimpleDateFormat(STRING_DATE_FORMAT).parse(ci.getLastUpdated());
    			if (lastUpdated.getTime() <= new Date().getTime()) {
    				if (new Date().getTime() - (timeToLiveSecs * 1000) <= lastUpdated.getTime()
    				 && lastUpdated.getTime() < new Date().getTime()) {
    					return true;
    				}
    			}
    		} catch (ParseException e) {
    		}
    	}
        
        constraintValidatorContext.disableDefaultConstraintViolation();
        constraintValidatorContext.buildConstraintViolationWithTemplate("Last updated must be within the last " + timeToLiveSecs + " seconds (" + timeToLiveSecs/60 + " minutes) and formatted as yyyy-MM-dd HH:mm:ss.").addConstraintViolation();
        
        return false;

    }

}
