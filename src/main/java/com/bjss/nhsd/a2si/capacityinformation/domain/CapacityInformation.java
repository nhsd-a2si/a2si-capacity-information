package com.bjss.nhsd.a2si.capacityinformation.domain;

import java.io.Serializable;
import java.util.Objects;

public class CapacityInformation implements Serializable {

    public static final String messageTemplate = "The estimated wait time is xxx hours but it could be " +
            "longer or shorter depending on the time arriving to the service";

    private String serviceId;
    private String message;
    private String lastUpdated;

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


    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CapacityInformation)) return false;
        CapacityInformation that = (CapacityInformation) o;
        return Objects.equals(getServiceId(), that.getServiceId()) &&
                Objects.equals(getMessage(), that.getMessage()) &&
                Objects.equals(getLastUpdated(), that.getLastUpdated());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getServiceId(), getMessage(), getLastUpdated());
    }

    @Override
    public String toString() {
        return "CapacityInformation{" +
                "serviceId='" + serviceId + '\'' +
                ", message='" + message + '\'' +
                ", lastUpdated='" + lastUpdated + '\'' +
                '}';
    }
}
