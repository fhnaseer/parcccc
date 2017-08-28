package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network.FetchFlightsTask;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class AddFlightActivity extends AppCompatActivity {

    public static final String DESTINATION_FLIGHTS = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.destinationflights";
    public static final String RETURN_FLIGHTS = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.returnFlights";
    public static final String CURRENT_TRIP_ID = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.currenttrip";
    private Long tripId;

    private Toolbar toolbar;
    private Button searchButton;
    private EditText origen;
    private EditText destiny;
    private EditText departureTime;
    private EditText returnTime;
    private final View.OnClickListener searchButtonClicked =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    searchFlight();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        tripId = (Long) getIntent().getSerializableExtra(CURRENT_TRIP_ID);
        setUpToolbar();

        searchButton = (Button) findViewById(R.id.search_flights);
        origen = (EditText) findViewById(R.id.fromText);
        destiny = (EditText) findViewById(R.id.toText);
        departureTime = (EditText) findViewById(R.id.departText);
        returnTime = (EditText) findViewById(R.id.returnText);
        searchButton.setOnClickListener(searchButtonClicked);

    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.add_flight_activity_title));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return (super.onOptionsItemSelected(menuItem));
    }

    // Search available flights
    private void searchFlight() {
        try {

           //MUC - TXL , 2017-07-13
            String origenCity = origen.getText().toString().trim().toUpperCase(Locale.GERMAN);
            String destinyCity = destiny.getText().toString().trim().toUpperCase(Locale.GERMAN);
            String departureDate = departureTime.getText().toString().trim();
            String returnDate = returnTime.getText().toString().trim();

            ArrayList<Flight> flightsDeparture = null;
            ArrayList<Flight> flightsReturn = null;

            if (origenCity!=null || destinyCity!=null || departureDate!= null)
            {
                //Departure flights
                flightsDeparture  = (ArrayList<Flight>)  new FetchFlightsTask().execute(origenCity,destinyCity,departureDate).get();
            } else {
                 //flightsDeparture  = (ArrayList<Flight>) new FetchFlightsTask().fetchFlights(origenCity,destinyCity,departureDate);
                //Toast
                //Missing information
            }

            if (destinyCity != null && returnDate != null) {
                //Return date is selected
                flightsReturn  = (ArrayList<Flight>) new FetchFlightsTask().execute(destinyCity, origenCity, returnDate).get();
            }

            if (flightsReturn != null && flightsReturn !=null) {
                Intent intent =  new Intent(getApplicationContext(), AvailableFlightListActivity.class);
                intent.putExtra(DESTINATION_FLIGHTS,  flightsDeparture);
                intent.putExtra(RETURN_FLIGHTS,  flightsReturn);
                intent.putExtra(CURRENT_TRIP_ID, tripId);
                startActivity(intent);
            }

        }  catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
