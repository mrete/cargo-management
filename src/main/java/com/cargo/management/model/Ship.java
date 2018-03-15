package com.cargo.management.model;

/**
 * The Ship POJO
 */
public class Ship {
    private long id;
    private String name;
    private long speed;
    private String type;
    private long maxCargoWeight;

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

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getMaxCargoWeight() {
        return maxCargoWeight;
    }

    public void setMaxCargoWeight(long maxCargoWeight) {
        this.maxCargoWeight = maxCargoWeight;
    }

    public Ship(long id, String name, long speed, String type, long maxCargoWeight) {
        this.id = id;
        this.name = name;
        this.speed = speed;
        this.type = type;
        this.maxCargoWeight = maxCargoWeight;
    }

    @Override
    public String toString() {
        return "Ship{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", speed=" + speed +
            ", type='" + type + '\'' +
            ", maxCargoWeight=" + maxCargoWeight +
            '}';
    }
}
