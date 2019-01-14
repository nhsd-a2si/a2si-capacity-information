package com.nhsd.a2si.capacityinformation.domain;

import java.io.Serializable;
import java.util.Objects;

import com.nhsd.a2si.capacityinformation.CapacityInformationValid;

@CapacityInformationValid
public class CapacityInformation implements Serializable {

	private static final long serialVersionUID = 741723721807676390L;
	
	public static final String messageTemplate = 
    		"The estimated wait time is xxx but it could be " +
        "longer or shorter depending on the time arriving at the service";
    public static final String messageTemplateNoWait = 
    		"There is currently no waiting time but this could change " + 
    		"depending the time arriving at the service";

    public static final String STRING_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private String serviceId;

    private String serviceName;

    // Note: This field is validated in the CapacityInformationValidator class because we needed to
    // obtain the wait time validity period from an environment variable which is retrieved from an
    // implementation class extending this one in the capacity service project.
    private String lastUpdated;

    private Integer waitingTimeMins;

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
