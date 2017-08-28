package de.tum.in.repobruegge.pom17aprilsnow.flightapp;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;
import java.util.Date;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FlightTest {
    private Flight flight;

    @Before
    public void setup() {
        flight = new Flight("Munich", "Lissabon", "MUC", "LIS", new Date(), new Date(), 1, "1", "YW1337", "YouWishAirlines");
    }

    @Test
    public void numberOfStopsTest() {
        Flight flight2;
        flight2 = new Flight("Munich", "Lissabon", "MUC", "LIS", new Date(), new Date(), 1, "1", "YW1337", "YouWishAirlines");
        assertEquals("1 stop correct", flight2.getNumberOfStopsFormatted(), "1 stop");
        flight2 = new Flight("Munich", "Lissabon", "MUC", "LIS", new Date(), new Date(), 2, "1", "YW1337", "YouWishAirlines");
        assertEquals("2 stops correct", flight2.getNumberOfStopsFormatted(), "2 stops");
        flight2 = new Flight("Munich", "Lissabon", "MUC", "LIS", new Date(), new Date(), 0, "1", "YW1337", "YouWishAirlines");
        assertEquals("non-stop correct", flight2.getNumberOfStopsFormatted(), "non-stop");
    }

    @Test
    public void fieldTest() {
        assertTrue("Flight serializable", flight instanceof Serializable);
        assertEquals("Flight origin", "Munich", flight.getOriginName());
        assertEquals("Flight destination", "Lissabon", flight.getDestinationName());
    }

}
