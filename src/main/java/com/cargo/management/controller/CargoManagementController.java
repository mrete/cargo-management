package com.cargo.management.controller;

import com.cargo.management.exception.DestinationException;
import com.cargo.management.model.Planet;
import com.cargo.management.model.Ship;
import com.cargo.management.model.Character;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CargoManagementController {
    private static final String PATH_TO_CHARACTERS = "C:\\Users\\Mihaela\\Downloads\\cargo-management\\cargo-management\\characters.json";
    private static final String PATH_TO_PLANETS = "C:\\Users\\Mihaela\\Downloads\\cargo-management\\cargo-management\\planets.json";
    private static final String PATH_TO_SHIPS = "C:\\Users\\Mihaela\\Downloads\\cargo-management\\cargo-management\\ships.json";

    /**
     *
     * @return List which contains the characters from json file.
     * @throws IOException if the I/O operation failed.
     * @throws ParseException if json has bad format.
     */
    public static ArrayList<Character> readCharactersFromJsonFile() throws IOException, ParseException {
        ArrayList<Character> result = new ArrayList();

        JSONParser parser = new JSONParser();
        JSONArray a =
                (JSONArray) parser.parse(new FileReader(PATH_TO_CHARACTERS));
        for (Object o : a) {
            JSONObject character = (JSONObject) o;

            Long id = (Long) character.get("id");

            String name = (String) character.get("name");

            JSONArray shipsType = (JSONArray) character.get("shipsType");
            String[] s = new String[shipsType.size()];
            int i = 0;
            for (Object c : shipsType) {
                s[i] = (String) c;
                i++;
            }
            result.add(new Character(id, name, s));

        }
        return result;
    }

    /**
     *
     * @return List which contains the planets from json file.
     * @throws IOException if the I/O operation failed.
     * @throws ParseException if json has bad format.
     */
    public static ArrayList<Planet> readPlanetsFromJsonFile() throws IOException, ParseException {
        ArrayList<Planet> result = new ArrayList();

        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(PATH_TO_PLANETS));
        for (Object o : a) {
            JSONObject planet = (JSONObject) o;

            Long id = (Long) planet.get("id");

            String name = (String) planet.get("name");

            Long distance = (Long) planet.get("distance");

            result.add(new Planet(id, name, distance));

        }
        return result;
    }

    /**
     *
     * @return List which contains the ships from json file.
     * @throws IOException if the I/O operation failed.
     * @throws ParseException if json has bad format.
     */
    public static ArrayList<Ship> readShipsFromJsonFile() throws IOException, ParseException {
        ArrayList<Ship> result = new ArrayList();

        JSONParser parser = new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader(PATH_TO_SHIPS));
        for (Object o : a) {
            JSONObject ship = (JSONObject) o;

            Long id = (Long) ship.get("id");
            String name = (String) ship.get("name");
            Long speed = (Long) ship.get("speed");
            String type = (String) ship.get("type");
            Long maxCargoWeight = (Long) ship.get("maxCargoWeight");

            result.add(new Ship(id, name, speed, type, maxCargoWeight));

        }
        return result;
    }

    /**
     * TODO
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static ArrayList<Ship> shipsAvailableForACharacter(String characterChoosen) throws IOException, ParseException {
        ArrayList<Ship> result = new ArrayList();
        ArrayList<Character> characters = readCharactersFromJsonFile();
        ArrayList<Ship> ships = readShipsFromJsonFile();

        for (Character character : characters) {
            if (character.getName().equals(characterChoosen)) {
                for (String shipType : character.getShipsType()) {
                    for (Ship ship : ships) {
                        if (shipType.equals(ship.getType())) {
                            result.add(ship);
                        }
                    }
                }
            }
        }
        return result;
    }

    /**
     *
     * @param ship
     * @param cargoWeight
     * @return the number of trips a ship should do
     */
    public static int howManyTrips(Ship ship, long cargoWeight) {
        int trips = 1;
        long maxCargoWeight = ship.getMaxCargoWeight();
        while (cargoWeight > maxCargoWeight) {
            trips++;
            cargoWeight = cargoWeight - maxCargoWeight;
        }

        return trips;

    }

    /**
     *
     * @param destination typed by user
     * @return returns a planet with specific destination
     * @throws IOException if the I/O operation failed.
     * @throws ParseException if json has bad format.
     */
    public static Planet getPlanet(String destination) throws DestinationException, IOException, ParseException {
        ArrayList<Planet> planets = readPlanetsFromJsonFile();
        for (Planet planet : planets) {
            if (planet.getName().equals(destination)) {
                return planet;
            }
        }
        throw new DestinationException("Destination doesn't exist!");
    }

    /**
     * @return returns time needed for one trip TODO
     */
    public static Long getTime(Planet planet, Ship ship) {
        return planet.getDistance() / ship.getSpeed();
    }
}
