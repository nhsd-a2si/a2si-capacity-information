package com.nhsd.a2si.capacityinformation.domain;

import javax.validation.constraints.NotEmpty;

public final class ServiceIdentifier {

    @NotEmpty(message = "Service identifier is mandatory")
    private String id;

    public ServiceIdentifier() {} // zero args constructor is needed by framework

    public ServiceIdentifier(String id){
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceIdentifier that = (ServiceIdentifier) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ServiceIdentifier{" +
                "id='" + id + '\'' +
                '}';
    }
}
