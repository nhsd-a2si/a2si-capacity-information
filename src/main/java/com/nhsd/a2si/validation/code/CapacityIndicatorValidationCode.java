package com.nhsd.a2si.validation.code;

/**
 * Enumeration defining the validation codes and messages for the capacity indicators which are 
 * supplied by providers of the data via the capacity service POST capacity indicators API.
 * 
 * @author jonathanpearce
 *
 */
public enum CapacityIndicatorValidationCode 
{
	VAL0001 ("VAL-0001", "Service Identifier for at least one service is null and must be supplied"),
	VAL0002 ("VAL-0002", "Service Name is mandatory and must be supplied"),
	VAL0003 ("VAL-0003", "Last updated must be within the last 1800 seconds (30 minutes) and formatted as yyyy-MM-dd HH:mm:ss"),
	VAL0004 ("VAL-0004", "Wait time is negative"),
	VAL0005 ("VAL-0005", "Wait Time is over 24 hours"),
	VAL0006 ("VAL-0006", "Number of people waiting is negative");
	
	private String validationCode;
	private String validationMessage;
	
	CapacityIndicatorValidationCode(String validationCode, String validationMessage)
	{
		this.validationCode = validationCode;
		this.validationMessage = validationMessage;
	}
	
	public String getValidationCode() {
		return validationCode;
	}

	public void setValidationCode(String validationCode) {
		this.validationCode = validationCode;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

}
