package com.cargo.management.start;

import com.cargo.management.exception.DestinationException;
import com.cargo.management.model.Planet;
import com.cargo.management.model.Character;
import com.cargo.management.model.Ship;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.*;

import static com.cargo.management.controller.CargoManagementController.*;

public class StartApp {

    /**
     * @param ships
     * @return list of ships ordered
     */
    public static List<Ship> sortByTime(List<Ship> ships, Planet planet, Long cargoWeight) {
        List<Ship> list = ships;

        Comparator<Ship> comparator = Comparator.comparingLong(ship -> 2 * getTime(planet, ship) * howManyTrips(ship, cargoWeight) - getTime(planet, ship));

        Collections.sort(list, comparator);
        return ships;
    }

    public static void main(String[] args) throws IOException, ParseException {
        // write your code here
        ArrayList<Character> characters = readCharactersFromJsonFile();
        System.out.println("Characters available : ");
        for (Character c : characters) {
            System.out.println(c.getName());
        }
        System.out.println();
        System.out.println("Choose one character : ");
        Scanner scanner = new Scanner(System.in);
        String characterChoosen = scanner.nextLine();
        ArrayList<Ship> shipsAvailable = shipsAvailableForACharacter(characterChoosen);
        System.out.println("Insert cargo weight : ");
        Long cargoWeight = scanner.nextLong();
        System.out.println("Insert destination planet : ");
        String destination = scanner.next();

        Planet planet = null;
        //check if destination is valid
        try {
            planet = getPlanet(destination);
        } catch (DestinationException e) {
            System.out.println(e.getMessage());
        }

        sortByTime(shipsAvailable, planet, cargoWeight);
        for (Ship ship : shipsAvailable) {
            long time = 2 * getTime(planet, ship) * howManyTrips(ship, cargoWeight) - getTime(planet, ship);
            System.out.println(
                    characterChoosen + " can transport a cargo of " + cargoWeight + " kg to " + planet.getName() + " in " + time + " hours "
                            + " , using " + ship.getName() + " in  " + howManyTrips(ship, cargoWeight) + " trips.");

        }
    }
}
