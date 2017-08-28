package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.FlightConverter;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.adapters.FlightArrayAdapter;

public class AvailableFlightListActivity extends AppCompatActivity {
    private Long tripId;
    private Flight selectedFlight;

    private Cursor destinationsCursor;
    private Cursor returnCursor;
    private SimpleCursorAdapter tripsCursorAdapter;
    private List<Flight> destinityFlights;
    private List<Flight> returnFlights;
    private Toolbar toolbar;
    private Button addToTripButton;
    private LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_flight_list);
        tripId = (Long) getIntent().getSerializableExtra(AddFlightActivity.CURRENT_TRIP_ID);
        setUpToolbar();

        linearLayout = (LinearLayout) findViewById(R.id.available_flights_list_layout);

        addToTripButton = (Button) findViewById(R.id.button_add_to_trip);
        addToTripButton.setOnClickListener(addToTripButtonClicked);

        destinityFlights = (List<Flight>) getIntent().getSerializableExtra(AddFlightActivity.DESTINATION_FLIGHTS);
        returnFlights = (List<Flight>) getIntent().getSerializableExtra(AddFlightActivity.RETURN_FLIGHTS);



        //Origen - Destination flights
        ArrayAdapter<Flight> arrayDestintyAdapter = new FlightArrayAdapter(this, destinityFlights);
        ListView listViewDestinations = (ListView) findViewById(R.id.list_destiny_trips);

        listViewDestinations.setAdapter(arrayDestintyAdapter);
        listViewDestinations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Get the Cursor
                // Move to the selected contact
                selectedFlight = destinityFlights.get(position);
                // Get the _ID value
            }
        });

        //Destination flights - Origen
        ArrayAdapter<Flight> arrayReturnAdapter = new FlightArrayAdapter(this, returnFlights);
        ListView listViewReturn = (ListView) findViewById(R.id.list_return_trips);

        listViewReturn.setAdapter(arrayReturnAdapter);
        listViewReturn.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedFlight = returnFlights.get(position);
            }
        });
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

    private final View.OnClickListener addToTripButtonClicked =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //TODO
                    // Add the selected flights to the trip
                    // if selectedFlight is not null
                    // save it in the db
                    if (selectedFlight != null) {
                        if (saveFlight(selectedFlight)) {
                            Snackbar.make(linearLayout,
                                    R.string.flight_saved, Snackbar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(linearLayout,
                                    R.string.flight_not_saved, Snackbar.LENGTH_LONG).show();
                        }
                    }

                    // and go back to the trip screen
                    Intent intent = new Intent(getApplicationContext(),TripFlightListActivity.class);
                    intent.putExtra(TripListActivity.CURRENT_TRIP_ID, tripId);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                    // nope, can't make it work – just show a notification that a flight has been saved
                    // and go back manually
                }
            };

    // saves flight information to the database
    private boolean saveFlight(Flight selectedFlight) {
        // create ContentValues object containing contact's key-value pairs
        ContentValues dumpedFlight = FlightConverter.dumpOneToDB(selectedFlight);
        dumpedFlight.put(DatabaseDescription.Flight.COLUMN_TRIP_ID, tripId.toString());

        // use Activity's ContentResolver to invoke
        // insert on the FlightContentProvider
        Uri newContactUri = getContentResolver().insert(DatabaseDescription.Flight.CONTENT_URI, dumpedFlight);

        if (newContactUri != null) {
            //the flight is saved
            return true;
        }
        else {
            return false;
        }
    }
}

