package com.nhsd.a2si.capacityinformation.domain;

import java.io.Serializable;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nhsd.a2si.capacityinformation.BlankOrPositive;
import com.nhsd.a2si.capacityinformation.CapacityInformationValid;
import com.nhsd.a2si.capacityinformation.WithinTheLast30Minutes;

import javax.validation.constraints.*;

@CapacityInformationValid
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

    @NotEmpty(message = "Service's name is mandatory")
    private String serviceName;

    @WithinTheLast30Minutes()
    private String lastUpdated;

    @BlankOrPositive(message = "The value can be blank or positive")
    @Max(message = "'waitingTimeMins' has an upper limit of 24 hours", value = 1440)
    private Integer waitingTimeMins;

    @BlankOrPositive(message = "The value can be blank or positive")
    private Integer numberOfPeopleWaiting;


    public CapacityInformation() {
    }

    public CapacityInformation(String serviceId) {
        this.serviceId = serviceId;
    }

    public CapacityInformation(String serviceId, String lastUpdated) {
        this.serviceId = serviceId;
        this.lastUpdated = lastUpdated;
    }
    
    public CapacityInformation(String serviceId, int waitingTimeMins, String lastUpdated) {
        this.serviceId = serviceId;
        this.waitingTimeMins = waitingTimeMins;
        this.lastUpdated = lastUpdated;
    }

    public CapacityInformation(String serviceId, int waitingTimeMins, int numberOfPeopleWaiting) {
        this.serviceId = serviceId;
        this.waitingTimeMins = waitingTimeMins;
        this.numberOfPeopleWaiting = numberOfPeopleWaiting;
    }

    public CapacityInformation(String serviceId, int waitingTimeMins, int numberOfPeopleWaiting, String lastUpdated) {
        this.serviceId = serviceId;
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

    public Integer getWaitingTimeMins() {
        return waitingTimeMins;
    }

    public void setWaitingTimeMins(Integer waitingTimeMins) {
        this.waitingTimeMins = waitingTimeMins;
    }

    public Integer getNumberOfPeopleWaiting() {
        return numberOfPeopleWaiting;
    }

    public void setNumberOfPeopleWaiting(Integer peopleWaiting) {
        this.numberOfPeopleWaiting = peopleWaiting;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
/*
    public int getTimeToLiveSecs() {
    	return 604800;
    }
*/    
    public int getDurationWaitTimeValidSecs() {
    	return 1800;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapacityInformation)) return false;
        CapacityInformation that = (CapacityInformation) o;
        return Objects.equals(getServiceId(), that.getServiceId()) &&
                Objects.equals(getServiceName(), that.getServiceName()) &&
                Objects.equals(getWaitingTimeMins(), that.getWaitingTimeMins()) &&
                Objects.equals(getNumberOfPeopleWaiting(), that.getNumberOfPeopleWaiting()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getLastUpdated(), that.getLastUpdated());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getServiceId(), getServiceName(), getMessage(), getWaitingTimeMins(), getNumberOfPeopleWaiting(), getLastUpdated());
    }

    @Override
    public String toString() {
        return "CapacityInformation{" +
                "serviceId='" + serviceId + '\'' +
                "serviceName='" + serviceName + '\'' +
                ", waitingTimeMins='" + waitingTimeMins + '\'' +
                ", numberOfPeopleWaiting='" + numberOfPeopleWaiting + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
