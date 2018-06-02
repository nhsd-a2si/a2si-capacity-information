package com.nhsd.a2si.capacityinformation.domain;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CapacityInformationTest {

    @Test
    public void testConstructor() {
        new CapacityInformation();
    }

    @Test
    public void testConstructorWithServiceIdAndMessage() {
        String serviceId = "serviceId";
        CapacityInformation capacityInformation = new CapacityInformation(serviceId, CapacityInformation.messageTemplate);
        assertNotNull(capacityInformation);
        assertEquals(serviceId, capacityInformation.getServiceId());
        assertEquals(CapacityInformation.messageTemplate, capacityInformation.getMessage());
    }

    @Test
    public void testConstructorWithOdsCodeAndLastUpdated() {
        String serviceId = "serviceId";
        String now = "2017-01-01 00:00:00";

        CapacityInformation capacityInformation = new CapacityInformation(serviceId, CapacityInformation.messageTemplate, now);
        assertNotNull(capacityInformation);
        assertEquals(serviceId, capacityInformation .getServiceId());
        assertEquals(CapacityInformation.messageTemplate, capacityInformation.getMessage());
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
    public void testMessage() {

        CapacityInformation capacityInformation = new CapacityInformation();

        capacityInformation.setMessage(CapacityInformation.messageTemplate);

        assertEquals(CapacityInformation.messageTemplate, capacityInformation.getMessage());
    }


    @Test
    public void testWaitingTimeMins() {

        CapacityInformation capacityInformation = new CapacityInformation();

        int wtm = 45;

        capacityInformation.setWaitingTimeMins(wtm);

        assertEquals(45, capacityInformation.getWaitingTimeMins());
    }


    @Test
    public void testPeopleWaitingToBeSeen() {

        CapacityInformation capacityInformation = new CapacityInformation();

        int peopleWaiting = 26;

        capacityInformation.setNumberOfPeopleWaiting(peopleWaiting);

        assertEquals(26, capacityInformation.getNumberOfPeopleWaiting());
    }


    @Test
    public void testLastUpdated() {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        CapacityInformation capacityInformation = new CapacityInformation();

        LocalDateTime lastUpdated = LocalDateTime.now();

        capacityInformation.setLastUpdated(dateTimeFormatter.format(lastUpdated));

        assertEquals(dateTimeFormatter.format(lastUpdated), capacityInformation.getLastUpdated());
    }

}
