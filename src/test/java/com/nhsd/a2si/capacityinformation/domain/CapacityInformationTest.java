package com.nhsd.a2si.capacityinformation.domain;

import org.junit.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Set;

import static com.nhsd.a2si.capacityinformation.domain.CapacityInformation.STRING_DATE_FORMAT;
import static junit.framework.TestCase.*;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CapacityInformationTest {

    private static ValidatorFactory factory;
    private static Validator validator;

    @BeforeClass
    public static void beforeClass() {
        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @AfterClass
    public static void afterClass(){
        factory.close();
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
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "serviceId", null);
        assertEquals(1, violations.size());
        assertEquals("Service identifier is mandatory", violations.iterator().next().getMessage());
    }

    @Test
    public void valiateThat_serviceIdentifierIsMandatory_EmptyString() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "serviceId", "");
        assertEquals(1, violations.size());
        assertThat(violations.iterator().next().getMessage(), endsWith("is mandatory"));
    }

    @Test
    public void valiateThat_serviceIdentifierIsMandatory_Given() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "serviceId", "123");
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_waitingTimeMins_null() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "waitingTimeMins", null);
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_waitingTimeMins_NegativeNumber() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "waitingTimeMins", -1);
        assertEquals(1, violations.size());
        assertThat(violations.iterator().next().getMessage(), endsWith("The value can be blank or positive"));
    }

    @Test
    public void valiateThat_waitingTimeMins_NegativeNumber_Zero() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "waitingTimeMins", 0);
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_waitingTimeMins_NegativeNumber_Positive() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "waitingTimeMins", 1);
        assertEquals(0, violations.size());
    }



    @Test
    public void valiateThat_waitingTimeMins_mustBeLessThan24Hours_over() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "waitingTimeMins", 1441);
        assertEquals(1, violations.size());
        assertThat(violations.iterator().next().getMessage(), endsWith("24 hours"));
    }

    @Test
    public void valiateThat_waitingTimeMins_mustBeLessThan24Hours_24hours() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "waitingTimeMins", 1440);
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPisitive_blank() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "numberOfPeopleWaiting", null);
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPisitive_zero() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "numberOfPeopleWaiting", 0);
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPisitive_positive() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "numberOfPeopleWaiting", 1);
        assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_numberOfPeopleWaiting_mustBeBlankOrPisitive_negative() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "numberOfPeopleWaiting", -1);
        assertEquals(1, violations.size());
        assertEquals("The value can be blank or positive", violations.iterator().next().getMessage());
    }

    @Ignore
    @Test
    public void valiateThat_lastUpdated_lastUpdated_null() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "lastUpdated", null);
        assertEquals(0, violations.size());
    }


    @Test
    public void valiateThat_lastUpdated_lastUpdated_now() {
            Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "lastUpdated", new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date().getTime()));
            assertEquals(0, violations.size());
    }

    @Test
    public void valiateThat_lastUpdated_within30Minutes() {
        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "lastUpdated", new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date().getTime()  - 1_798_200));
        assertEquals(0, violations.size());
    }

//    @Test
//    public void valiateThat_lastUpdated_over30Minutes() {
//        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "lastUpdated", new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date().getTime()  - 1_800_600));
//        assertEquals(1, violations.size());
//    }

//    @Test
//    public void valiateThat_lastUpdated_inTheFuture() {
//        Set<ConstraintViolation<CapacityInformation>> violations = validator.validateValue(CapacityInformation.class, "lastUpdated", new SimpleDateFormat(STRING_DATE_FORMAT).format(6000 + new Date().getTime()));
//        assertEquals(1, violations.size());
//    }

}
