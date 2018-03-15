package com.cargo.management.model;

import java.util.Arrays;
import java.util.Objects;

/**
 * The Character POJO
 */
public class Character {
    private long id;
    private String name;
    private String[] shipsType;

    public Character(Long id, String name, String[] shipsType) {
        this.id = id;
        this.name = name;
        this.shipsType = shipsType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getShipsType() {
        return shipsType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setShipsType(String[] shipsType) {
        this.shipsType = shipsType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return id == character.id &&
                Objects.equals(name, character.name) &&
                Arrays.equals(shipsType, character.shipsType);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(id, name);
        result = 31 * result + Arrays.hashCode(shipsType);
        return result;
    }

    @Override
    public String toString() {
        return "Character{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", shipsType='" + shipsType + '\'' +
            '}';
    }
}
