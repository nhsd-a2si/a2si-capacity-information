package com.nhsd.a2si.capacityinformation;

import static com.nhsd.a2si.capacityinformation.domain.CapacityInformation.STRING_DATE_FORMAT;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.nhsd.a2si.capacityinformation.domain.CapacityInformation;
import com.nhsd.a2si.validation.code.CapacityIndicatorValidationCode;

public class CapacityInformationValidator implements ConstraintValidator<CapacityInformationValid, CapacityInformation> 
{
	
	CapacityInformationValid civ;
	
	@Override
    public void initialize(CapacityInformationValid constraintAnnotation) 
	{
    }

	@Override
    public boolean isValid(CapacityInformation ci, 
                           ConstraintValidatorContext constraintValidatorContext) 
    {
        	
    	constraintValidatorContext.disableDefaultConstraintViolation();
    	
    	boolean valid = true;
    	
    	// Validate that the lastUpdated is supplied and that it is within timeToLiveSecs
    	valid = this.isServiceIdentifierValid(ci, constraintValidatorContext);
    	
    	if(valid)
    	{
    		valid = this.isServiceNameValid(ci, constraintValidatorContext) &
        	this.isLastUpdatedValid(ci, constraintValidatorContext) &
        	this.isWaitTimeValid(ci, constraintValidatorContext) &
        	this.isNumberOfPeopleValid(ci, constraintValidatorContext);
    	}
    		
    	return valid;
    }
    
    /**
     * Validate that the service identifier provided is not empty.
     * 
     * @param ci
     * @param constraintValidatorContext
     * 
     * @return true if service identifier is valid; false otherwise.
     */
    private boolean isServiceIdentifierValid(CapacityInformation ci, 
            ConstraintValidatorContext constraintValidatorContext)
    {
    	if(ci.getServiceId()==null || ci.getServiceId().isEmpty())
    	{
    		this.addValidationError(constraintValidatorContext,
    				"N/A",
    				CapacityIndicatorValidationCode.VAL0001);
    		
    		return false;
    	}
    	
    	return true;
    }
    
    /**
     * Validate that the service name provided is not empty.
     * 
     * @param ci
     * @param constraintValidatorContext
     * 
     * @return true if service name is valid; false otherwise.
     */
    private boolean isServiceNameValid(CapacityInformation ci, 
            ConstraintValidatorContext constraintValidatorContext)
    {
    	if(ci.getServiceName()==null || ci.getServiceName().isEmpty())
    	{
    		this.addValidationError(constraintValidatorContext,
    				ci.getServiceId(),
    				CapacityIndicatorValidationCode.VAL0002);
    		
    		return false;
    	}
    	
    	return true;
    }
    
    /**
     * Validate that the last updated time provided is not empty and is within the
     * defined time to live period of now.
     * 
     * @param ci
     * @param constraintValidatorContext
     * 
     * @return true if last updated time is valid; false otherwise.
     */
    private boolean isLastUpdatedValid(CapacityInformation ci, 
            ConstraintValidatorContext constraintValidatorContext)
    {
    	
    	int timeToLiveSecs = ci.getDurationWaitTimeValidSecs();
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
        
    	this.addValidationError(constraintValidatorContext,
				ci.getServiceId(),
				"VAL-0003",
				"Last updated must be within the last " + timeToLiveSecs + " seconds (" + timeToLiveSecs/60 + " minutes) and formatted as yyyy-MM-dd HH:mm:ss");
    	
        return false;
    }
    
    /**
     * Validate that the wait time provided is empty, not negative, and not greater than
     * 24 hours.
     * 
     * @param ci
     * @param constraintValidatorContext
     * 
     * @return true if wait time is valid; false otherwise.
     */
    private boolean isWaitTimeValid(CapacityInformation ci, 
            ConstraintValidatorContext constraintValidatorContext)
    {
    	boolean valid = (ci.getWaitingTimeMins() == null || ci.getWaitingTimeMins() >= 0);
    	
    	if (!valid)
    	{
    		this.addValidationError(constraintValidatorContext,
    				ci.getServiceId(),
    				CapacityIndicatorValidationCode.VAL0004);
    	}
    	else
    	{
    		if(ci.getWaitingTimeMins() != null && ci.getWaitingTimeMins() > 1440)
    		{
    			this.addValidationError(constraintValidatorContext,
        				ci.getServiceId(),
        				CapacityIndicatorValidationCode.VAL0005);
    			
    			valid = false;
    		}
    	}
    	
    	return valid;
    }
    
    /**
     * Validate that the number of people provided is empty or greater than zero.
     * 
     * @param ci
     * @param constraintValidatorContext
     * 
     * @return true if number of people is valid; false otherwise.
     */
    private boolean isNumberOfPeopleValid(CapacityInformation ci, 
            ConstraintValidatorContext constraintValidatorContext)
    {
    	boolean valid = (ci.getNumberOfPeopleWaiting() == null || ci.getNumberOfPeopleWaiting() >= 0);
    	
    	if (!valid)
    	{
    		this.addValidationError(constraintValidatorContext,
    				ci.getServiceId(),
    				CapacityIndicatorValidationCode.VAL0006);
    	}
    	
    	return valid;
    }
    
    /**
     * Method to add a validation message to the constraintValidatorContext.
     * 
     * @param constraintValidatorContext {@link ConstraintValidatorContext}
     * @param serviceIdentifier the identifier of the service
     * @param validationCode the code for this validation error
     * @param validationMessage the message for this validation error
     */
    private void addValidationError(ConstraintValidatorContext constraintValidatorContext,
    		String serviceIdentifier,
    		String validationCode,
    		String validationMessage)
    {
    	final String delimiter = "^";
    	
    	constraintValidatorContext.buildConstraintViolationWithTemplate(serviceIdentifier
    			+ delimiter
    			+ validationCode
    			+ delimiter
    			+ validationMessage).addConstraintViolation();
    }
    
    /**
     * Method to add a validation message to the constraintValidatorContext.
     * 
     * @param constraintValidatorContext {@link ConstraintValidatorContext}
     * @param serviceIdentifier the identifier of the service
     * @param capacityIndicatorValidationCode enumerated validation code and message.
     */
    private void addValidationError(ConstraintValidatorContext constraintValidatorContext,
    		String serviceIdentifier,
    		CapacityIndicatorValidationCode capacityIndicatorValidationCode)
    {
    	final String delimiter = "^";
    	
    	constraintValidatorContext.buildConstraintViolationWithTemplate(serviceIdentifier
    			+ delimiter
    			+ capacityIndicatorValidationCode.getValidationCode()
    			+ delimiter
    			+ capacityIndicatorValidationCode.getValidationMessage()).addConstraintViolation();
    }

}
