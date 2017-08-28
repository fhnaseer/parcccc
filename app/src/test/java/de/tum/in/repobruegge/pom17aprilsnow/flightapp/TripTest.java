package de.tum.in.repobruegge.pom17aprilsnow.flightapp;

import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Trip;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Vincent Bode on 05.07.2017.
 */

public class TripTest {
    private Trip trip;

    @Before
    public void setup() {
        trip = new Trip("MyTrip1");
    }

    @Test
    public void testTripName() {
        assertTrue("Trip is serializable", trip instanceof Serializable);
        assertEquals("Trip name saved correctly", "MyTrip1", trip.getName());
    }

}
