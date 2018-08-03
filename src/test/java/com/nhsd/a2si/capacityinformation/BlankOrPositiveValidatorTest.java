package com.nhsd.a2si.capacityinformation;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlankOrPositiveValidatorTest {

    @Test
    public void isValid_null_true() {
        assertTrue(new BlankOrPositiveValidator().isValid(null, null));
    }

    @Test
    public void isValid_positive_true() {
        assertTrue(new BlankOrPositiveValidator().isValid(1, null));
    }

    @Test
    public void isValid_zero_true() {
        assertTrue(new BlankOrPositiveValidator().isValid(0, null));
    }

    @Test
    public void isValid_negative_false() {
        assertFalse(new BlankOrPositiveValidator().isValid(-1, null));
    }


}