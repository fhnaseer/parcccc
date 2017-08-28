package de.tum.in.repobruegge.pom17aprilsnow.flightapp;

import org.junit.Before;
import org.junit.Test;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription;

import static junit.framework.Assert.assertEquals;

public class DatabaseTest {

    @Before
    public void setup() {
    }

    @Test
    public void sqlQueriesTest() {
        assertEquals("flights", DatabaseDescription.Flight.TABLE_NAME);
        assertEquals("trips", DatabaseDescription.Trip.TABLE_NAME);
        assertEquals("de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.trips", DatabaseDescription.TRIP_AUTHORITY);
        assertEquals("de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.flights", DatabaseDescription.FLIGHT_AUTHORITY);
    }
}
