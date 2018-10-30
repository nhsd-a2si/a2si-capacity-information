package com.nhsd.a2si.capacityinformation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.nhsd.a2si.capacityinformation.domain.CapacityInformation.STRING_DATE_FORMAT;

public class WithinTheLast30MinutesValidator implements ConstraintValidator<WithinTheLast30Minutes, String> {

@Override
   public boolean isValid(String obj, ConstraintValidatorContext context) {
      if (obj == null || obj.length() == 0) return false;
      try {
         Date lastUpdated = new SimpleDateFormat(STRING_DATE_FORMAT).parse(obj);
         if (lastUpdated.getTime() <= new Date().getTime()) {
            if(new Date().getTime() - (30 * 60000) <= lastUpdated.getTime()) {
               return true;
            }
         }
      } catch (ParseException e) {
         return false;
      }
      return false;
   }
}
