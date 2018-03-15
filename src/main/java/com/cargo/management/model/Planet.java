package com.cargo.management.model;

/**
 * The Planet POJO
 */
public class Planet {
    private long id;
    private String name;
    private long distance;

    public Planet(long id, String name, long distance) {
        this.id = id;
        this.name = name;
        this.distance = distance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDistance() {
        return distance;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }
}
