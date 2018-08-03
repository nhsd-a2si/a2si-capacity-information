package com.nhsd.a2si.capacityinformation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import static com.nhsd.a2si.capacityinformation.domain.CapacityInformation.STRING_DATE_FORMAT;

public class BlankOrWithinTheLast30MinutesValidator implements ConstraintValidator<BlandOrWithinTheLast30Minutes, String> {

   @Override
   public boolean isValid(String obj, ConstraintValidatorContext context) {
      if (obj == null) return true;
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
