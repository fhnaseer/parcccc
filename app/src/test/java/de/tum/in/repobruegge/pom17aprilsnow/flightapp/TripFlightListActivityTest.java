package de.tum.in.repobruegge.pom17aprilsnow.flightapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.TripFlightListActivity;

public class TripFlightListActivityTest {
    private TripFlightListActivity tripFlightListActivity;

    @Before
    public void setup() {
        tripFlightListActivity = new TripFlightListActivity();
    }

    @Test
    public void addFlightsTest() {
        Flight flight = new Flight("Munich", "Lissabon", "MUC", "LIS", new Date(), new Date(), 1, "1", "YW1337", "YouWishAirlines");

        tripFlightListActivity.addFlights(flight);
        tripFlightListActivity.addFlights(flight, flight, flight);
        tripFlightListActivity.addFlights(flight, flight);
    }
}
