package com.example.locatorappforhealthcare;

public class FacilityDistance implements Comparable<FacilityDistance> {
    public Facility facility;
    private double distance;

    public FacilityDistance(Facility facility, double distance) {
        this.facility = facility;
        this.distance = distance;
    }

    public Facility getFacility() {
        return facility;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public int compareTo(FacilityDistance other) {
        return Double.compare(this.distance, other.distance);
    }
}
