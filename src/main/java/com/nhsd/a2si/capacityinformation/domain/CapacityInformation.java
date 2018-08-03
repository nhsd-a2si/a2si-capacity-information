package com.nhsd.a2si.capacityinformation.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhsd.a2si.capacityinformation.BlankOrPositive;
import com.nhsd.a2si.capacityinformation.BlandOrWithinTheLast30Minutes;

import javax.validation.constraints.*;

public class CapacityInformation implements Serializable {

    public static final String messageTemplate = 
    		"The estimated wait time is xxx but it could be " +
        "longer or shorter depending on the time arriving at the service";
    public static final String messageTemplateNoWait = 
    		"There is currently no waiting time but this could change " + 
    		"depending the time arriving at the service";

    public static final String STRING_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @NotEmpty(message = "Service identifier is mandatory")
    private String serviceId;

    @JsonIgnore
    @NotEmpty(message = "The message field is mandatory")
    private String message;

    @BlandOrWithinTheLast30Minutes
    private String lastUpdated;

    @NotNull(message = "Wait time in minutes is mandatory")
    @Positive(message = "Wait time in minutes must be a positive number")
    @Max(message = "Wait time has an upper limit of 24 hours", value = 1440)
    private Integer waitingTimeMins;

    @BlankOrPositive(message = "The value can be blank or positive")
    private int numberOfPeopleWaiting;

    public CapacityInformation() {
    }

    public CapacityInformation(String serviceId, String message) {
        this.serviceId = serviceId;
        this.message = message;
    }

    public CapacityInformation(String serviceId, String message, String lastUpdated) {
        this.serviceId = serviceId;
        this.message = message;
        this.lastUpdated = lastUpdated;
    }
    
    public CapacityInformation(String serviceId, int waitingTimeMins, String lastUpdated) {
        this.serviceId = serviceId;
        this.waitingTimeMins = waitingTimeMins;
        this.lastUpdated = lastUpdated;
    }

    public CapacityInformation(String serviceId, String message, int waitingTimeMins, int numberOfPeopleWaiting) {
        this.serviceId = serviceId;
        this.message = message;
        this.waitingTimeMins = waitingTimeMins;
        this.numberOfPeopleWaiting = numberOfPeopleWaiting;
    }

    public CapacityInformation(String serviceId, String message, int waitingTimeMins, int numberOfPeopleWaiting, String lastUpdated) {
        this.serviceId = serviceId;
        this.message = message;
        this.waitingTimeMins = waitingTimeMins;
        this.numberOfPeopleWaiting = numberOfPeopleWaiting;
        this.lastUpdated = lastUpdated;
    }


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMessage() {
        return getMessageFromWaitingTime();
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getWaitingTimeMins() {
        return waitingTimeMins;
    }

    public void setWaitingTimeMins(Integer waitingTimeMins) {
        this.waitingTimeMins = waitingTimeMins;
    }

    public int getNumberOfPeopleWaiting() {
        return numberOfPeopleWaiting;
    }

    public void setNumberOfPeopleWaiting(int peopleWaiting) {
        this.numberOfPeopleWaiting = peopleWaiting;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    private String getMessageFromWaitingTime() {
    	String sMessage = "";
        if (this.waitingTimeMins != null) {
        	if (this.waitingTimeMins > 0) {
	            int iWaitingTimeHours = this.waitingTimeMins / 60;
	            int iWaitingTimeMinutes = this.waitingTimeMins % 60;
	            
	            String formattedWaitingTime = iWaitingTimeMinutes + " min";
	            if (iWaitingTimeHours > 0) {
	            		formattedWaitingTime = iWaitingTimeHours + " hr " + formattedWaitingTime;
	            }
	
	            sMessage = CapacityInformation.messageTemplate.replace("xxx", formattedWaitingTime);
	        } else if (this.waitingTimeMins == 0) {
	        	sMessage = CapacityInformation.messageTemplateNoWait;
	        }
    	}
        return sMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapacityInformation)) return false;
        CapacityInformation that = (CapacityInformation) o;
        return Objects.equals(getServiceId(), that.getServiceId()) &&
                Objects.equals(getWaitingTimeMins(), that.getWaitingTimeMins()) &&
                Objects.equals(getNumberOfPeopleWaiting(), that.getNumberOfPeopleWaiting()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getLastUpdated(), that.getLastUpdated());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getServiceId(), getMessage(), getWaitingTimeMins(), getNumberOfPeopleWaiting(), getLastUpdated());
    }

    @Override
    public String toString() {
        return "CapacityInformation{" +
                "serviceId='" + serviceId + '\'' +
                ", waitingTimeMins='" + waitingTimeMins + '\'' +
                ", numberOfPeopleWaiting='" + numberOfPeopleWaiting + '\'' +
                ", message='" + message + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
