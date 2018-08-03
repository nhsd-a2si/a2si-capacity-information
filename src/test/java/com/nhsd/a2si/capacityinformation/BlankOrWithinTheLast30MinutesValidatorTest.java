package com.nhsd.a2si.capacityinformation;

import com.nhsd.a2si.capacityinformation.domain.CapacityInformation;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.nhsd.a2si.capacityinformation.domain.CapacityInformation.STRING_DATE_FORMAT;
import static org.junit.Assert.*;

public class BlankOrWithinTheLast30MinutesValidatorTest {

    @Test
    public void isValid_null_true() {
        assertTrue(new BlankOrWithinTheLast30MinutesValidator().isValid(null, null));
    }

    /* +0 minutes */
    @Test
    public void isValid_now_true() {
        assertTrue(new BlankOrWithinTheLast30MinutesValidator().isValid(new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date()), null));
    }


    /* +0.10 minutes */
    @Test
    public void isValid_10SecondsLater_false() {
        assertFalse(new BlankOrWithinTheLast30MinutesValidator().isValid(new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date(6000 + new Date().getTime())), null));
    }

    /* -30.01 minutes */
    @Test
    public void isValid_JustOver30MinuteBefore_false() {
        assertFalse(new BlankOrWithinTheLast30MinutesValidator().isValid(new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date(new Date().getTime() - 1_800_600)), null));
    }

    /* -29.97 minutes */
    @Test
    public void isValid_JustUnderMinuteBefore_true() {
        assertTrue(new BlankOrWithinTheLast30MinutesValidator().isValid(new SimpleDateFormat(STRING_DATE_FORMAT).format(new Date(new Date().getTime() - 1_798_200)), null));
    }

}