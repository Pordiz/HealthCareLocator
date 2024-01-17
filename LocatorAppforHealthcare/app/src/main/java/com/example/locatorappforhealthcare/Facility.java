package com.example.locatorappforhealthcare;
import android.util.Log;

import java.util.List;
import java.util.Objects;
import com.google.android.gms.maps.model.LatLng;
public class Facility {
    private String name;
    private double latitude;
    private double longitude;
    private String address;
    private String phone;
    private String opening_hours;
    private String category;
    private String doctorType;
    private double distance;
    private List<String> services;


    public Facility(String name, double latitude, double longitude, String address, String phone, String opening_hours, String category, String doctorType,List<String> services) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
        this.phone = phone;
        this.opening_hours = opening_hours;
        this.category = category;
        this.doctorType = doctorType;
        this.services = services;

    }
    public List<String> getServices() {
        return services;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getDoctorType() {
        return doctorType;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getDistance() {
        return distance;
    }
    public String getAddressLine() {
        return address;
    }

    public String getPhoneNumber() {
        return phone;
    }


    public String getOpeningHours() {
        return opening_hours;
    }

    public String getFacilityCategory() {
        return category;
    }
    public LatLng getLatLng() {
        return new LatLng(latitude, longitude);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Facility facility = (Facility) o;
        return Double.compare(facility.getLatitude(), getLatitude()) == 0 &&
                Double.compare(facility.getLongitude(), getLongitude()) == 0 &&
                Objects.equals(getName(), facility.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLatitude(), getLongitude());
    }



}

