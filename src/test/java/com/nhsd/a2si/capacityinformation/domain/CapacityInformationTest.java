package com.nhsd.a2si.capacityinformation.domain;

import org.junit.*;

import com.nhsd.a2si.capacityinformation.CapacityInformationValidator;
import com.nhsd.a2si.validation.code.CapacityIndicatorValidationCode;

import javax.validation.ClockProvider;
import javax.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;

public class CapacityInformationTest {
    
	private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	private CapacityInformation capacityInformation = new CapacityInformation();
	private CapacityInformationValidator validator = new CapacityInformationValidator();
	
	private List<String> validationErrors = new ArrayList<>();
    
    private ConstraintValidatorContext cContext = new ConstraintValidatorContext() 
    { 
        public ConstraintViolationBuilder buildConstraintViolationWithTemplate(String msgTemplate) 
        { 
            return new ConstraintViolationBuilder() 
            { 

                public ConstraintValidatorContext addConstraintViolation() 
                { 
                	validationErrors.add(msgTemplate);
                    return null;
                }

				@Override
				public NodeBuilderCustomizableContext addPropertyNode(String name) {
					return null;
				}

				@Override
				public LeafNodeBuilderCustomizableContext addBeanNode() {
					return null;
				}

				@Override
				public ContainerElementNodeBuilderCustomizableContext addContainerElementNode(String name,
						Class<?> containerType, Integer typeArgumentIndex) {
					return null;
				}

				@Override
				public NodeBuilderDefinedContext addParameterNode(int index) {
					return null;
				}

				@Override
				public NodeBuilderDefinedContext addNode(String name) {
					return null;
				} 
            }; 
        }

		@Override
		public ClockProvider getClockProvider() {
			return null;
		}

		@Override
		public <T> T unwrap(Class<T> type) {
			return null;
		}

		@Override
		public void disableDefaultConstraintViolation() {
			
		}

		@Override
		public String getDefaultConstraintMessageTemplate() {
			return null;
		} 
    };
    
    @Before
    public void before()
    {	
    	// Initialise capacity information with default values
    	this.capacityInformation.setServiceId("Service_1");
    	this.capacityInformation.setServiceName("Service Name 1");
    	this.capacityInformation.setWaitingTimeMins(20);
    	this.capacityInformation.setNumberOfPeopleWaiting(10);
        this.capacityInformation.setLastUpdated(this.dateTimeFormatter.format(LocalDateTime.now()));
    }
    
    @After
    public void after()
    {
    	// Empty validation error list
    	validationErrors.clear();
    }

    @Test
    public void testConstructor() {
        new CapacityInformation();
    }

    @Test
    public void testConstructorWithServiceIdAndMessage() {
        String serviceId = "serviceId";
        CapacityInformation capacityInformation = new CapacityInformation(serviceId);
        assertNotNull(capacityInformation);
        assertEquals(serviceId, capacityInformation.getServiceId());
    }

    @Test
    public void testConstructorWithOdsCodeAndLastUpdated() {
        String serviceId = "serviceId";
        String now = "2017-01-01 00:00:00";

        CapacityInformation capacityInformation = new CapacityInformation(serviceId, now);
        assertNotNull(capacityInformation);
        assertEquals(serviceId, capacityInformation .getServiceId());
        assertEquals(now, capacityInformation.getLastUpdated());
    }

    @Test
    public void testServiceId() {

        CapacityInformation capacityInformation = new CapacityInformation();

        String serviceId = "serviceId";

        capacityInformation.setServiceId(serviceId);

        assertEquals(serviceId, capacityInformation.getServiceId());
    }

    @Test
    public void testWaitingTimeMins() {

        CapacityInformation capacityInformation = new CapacityInformation();

        int wtm = 45;

        capacityInformation.setWaitingTimeMins(wtm);

        assertNotNull(capacityInformation.getWaitingTimeMins());
        assertEquals(45, capacityInformation.getWaitingTimeMins().intValue());
    }


    @Test
    public void testPeopleWaitingToBeSeen() {

        CapacityInformation capacityInformation = new CapacityInformation();

        int peopleWaiting = 26;

        capacityInformation.setNumberOfPeopleWaiting(peopleWaiting);

        assertEquals(26, capacityInformation.getNumberOfPeopleWaiting().intValue());
    }


    @Test
    public void testLastUpdated() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        CapacityInformation capacityInformation = new CapacityInformation();

        LocalDateTime lastUpdated = LocalDateTime.now();

        capacityInformation.setLastUpdated(dateTimeFormatter.format(lastUpdated));

        assertEquals(dateTimeFormatter.format(lastUpdated), capacityInformation.getLastUpdated());
    }

    @Test
    public void valiateThat_serviceIdentifierIsMandatory_null() {
    	
    	this.capacityInformation.setServiceId(null);
 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(1, this.validationErrors.size());
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0001.getValidationCode()));
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0001.getValidationMessage()));
    }

    @Test
    public void valiateThat_serviceIdentifierIsMandatory_EmptyString() {
    	
    	this.capacityInformation.setServiceId("");
    	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(1, this.validationErrors.size());
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0001.getValidationCode()));
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0001.getValidationMessage()));
    }
    
    @Test
    public void valiateThat_serviceNameIsMandatory_EmptyString() {
    	
    	this.capacityInformation.setServiceName("");
    	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(1, this.validationErrors.size());
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0002.getValidationCode()));
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0002.getValidationMessage()));
    }
    
    @Test
    public void valiateThat_serviceNameIsMandatory_null() {
    	
    	this.capacityInformation.setServiceName(null);
 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(1, this.validationErrors.size());
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0002.getValidationCode()));
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0002.getValidationMessage()));
    }

    @Test
    public void validCapacityInformationRecord() {
    	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(0, this.validationErrors.size());
        
    }

    @Test
    public void valiateThat_waitingTimeMins_null() {
    	
    	this.capacityInformation.setWaitingTimeMins(null);
    	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(0, this.validationErrors.size());
    }

    @Test
    public void valiateThat_waitingTimeMins_NegativeNumber() {
    	
    	this.capacityInformation.setWaitingTimeMins(-4);
    	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(1, this.validationErrors.size());
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0004.getValidationCode()));
        assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0004.getValidationMessage()));
    }

    @Test
    public void valiateThat_waitingTimeMins_Zero() {
    	this.capacityInformation.setWaitingTimeMins(0);
   	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(0, this.validationErrors.size());
    }

    @Test
    public void valiateThat_waitingTimeMins_mustBeLessThan24Hours_over() {
	    
    	this.capacityInformation.setWaitingTimeMins(60*25);
		 
		this.validator.isValid(this.capacityInformation, this.cContext);
		
	    assertEquals(1, this.validationErrors.size());
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0005.getValidationCode()));
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0005.getValidationMessage()));
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPositive_blank() {
    	
    	this.capacityInformation.setNumberOfPeopleWaiting(null);
   	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(0, this.validationErrors.size());
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPositive_zero() {
        
    	this.capacityInformation.setNumberOfPeopleWaiting(0);
      	 
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
        assertEquals(0, this.validationErrors.size());
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPisitive_negative() {
        
    	this.capacityInformation.setNumberOfPeopleWaiting(-10);
		 
		this.validator.isValid(this.capacityInformation, this.cContext);
		
	    assertEquals(1, this.validationErrors.size());
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0006.getValidationCode()));
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0006.getValidationMessage()));
    }

    @Test
    public void valiateThat_lastUpdated_lastUpdated_null() {
    	
    	this.capacityInformation.setLastUpdated(null);
		 
		this.validator.isValid(this.capacityInformation, this.cContext);
		
	    assertEquals(1, this.validationErrors.size());
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0003.getValidationCode()));
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0003.getValidationMessage()));
    }

    @Test
    public void valiateThat_lastUpdated_over30Minutes() {
    	
    	this.capacityInformation.setLastUpdated(timeOverThirtyMinsOld());
		 
		this.validator.isValid(this.capacityInformation, this.cContext);
		
	    assertEquals(1, this.validationErrors.size());
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0003.getValidationCode()));
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0003.getValidationMessage()));
    }

    @Test
    public void valiateThat_lastUpdated_inTheFuture() {
    	
    	this.capacityInformation.setLastUpdated(timeInTheFuture());
		 
		this.validator.isValid(this.capacityInformation, this.cContext);
		
	    assertEquals(1, this.validationErrors.size());
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0003.getValidationCode()));
	    assertTrue(validationErrors.get(0).contains(CapacityIndicatorValidationCode.VAL0003.getValidationMessage()));
    }
    
    @Test
    public void validateMultipleErrorsRaised() {
    	
    	this.capacityInformation.setLastUpdated(timeInTheFuture());
    	this.capacityInformation.setServiceName(null);
    	this.capacityInformation.setWaitingTimeMins(-34);
    	this.capacityInformation.setNumberOfPeopleWaiting(-9);
    	
    	this.validator.isValid(this.capacityInformation, this.cContext);
    	
    	assertEquals(4, this.validationErrors.size());
    	
    	String errors = new String();
    	for (String validationError : validationErrors)
    	{
    		errors = errors.concat(validationError);
    	}
    	
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0002.getValidationCode()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0002.getValidationMessage()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0003.getValidationCode()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0003.getValidationMessage()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0004.getValidationCode()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0004.getValidationMessage()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0006.getValidationCode()));
	    assertTrue(errors.contains(CapacityIndicatorValidationCode.VAL0006.getValidationMessage()));
    }
    
    private String timeOverThirtyMinsOld()
    {
    	return this.dateTimeFormatter.format(LocalDateTime.now().minusMinutes(31));
    }
    
    private String timeInTheFuture()
    {
    	return this.dateTimeFormatter.format(LocalDateTime.now().plusMinutes(2));
    }

}
