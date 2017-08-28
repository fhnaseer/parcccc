package parccc.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.FlightConverter;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.adapters.FlightArrayAdapter;

public class TripFlightListActivity extends AppCompatActivity {

	private final String HOCKEYAPP_ID = "7843d65e4b1340eaa939979d57fb9bb8";
    public static final String CURRENT_TRIP_ID = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.currenttrip";
    public static final String CURRENT_FLIGHT_ID = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.currentflight";
    public static final String TRIP_CITIES = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.cities";
    private List<Flight> flights;
    private Cursor tripCursor;
    private Cursor flightsCursor;
    private Long tripId;
    private String tripName;
    private Toolbar toolbar;


    public TripFlightListActivity() {
        flights = new LinkedList<>();
    }

    public void addFlights(Flight... flights) {
        this.flights.addAll(Arrays.asList(flights));
    }

    public void addFlights(ArrayList<Flight> flights) {
        for (int i = 0; i < flights.size(); i++) {
            this.flights.add(flights.get(i));
        }
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TripFlightListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_flight_list);
        tripId = (Long) getIntent().getSerializableExtra(de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.TripListActivity.CURRENT_TRIP_ID);
        setUpToolbar();

        //TODO: Real Flights
        flightsCursor = getFlightsCursor();
        addFlights(FlightConverter.loadManyFromDB(flightsCursor));

        // Mock Flights
        // addFlights(
        //         new Flight("Munich", "Lisbon", "MUC", "LIS", new Date(), new Date(), 0, "Terminal 1", "YW0042", "YouWishAirlines"),
        //         new Flight("Lisbon", "Munich", "LIS", "MUC", new Date(), new Date(), 1, "Terminal A", "YW1337", "YouWishAirlines")
        //         new Flight("Munich", "Lisbon", "MUC", "LIS", new Date(), new Date(), 0, "Terminal 1", "YW0042", "YouWishAirlines"),
        //         new Flight("Lisbon", "Paris", "LIS", "ORY", new Date(), new Date(), 0, "Terminal C", "YC1237", "AirFrance"),
        //         new Flight("Paris", "Munich", "ORY", "MUC", new Date(), new Date(), 0, "Terminal A", "Y00SD7", "AirEuropa")
        // );

        ArrayAdapter<Flight> arrayAdapter = new FlightArrayAdapter(this, flights);
        ListView listView = (ListView) findViewById(R.id.list_flights);

        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Flight flight = flights.get(position);
                Intent intent = new Intent(TripFlightListActivity.this, de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.FlightDetailsActivity.class);
                intent.putExtra(CURRENT_FLIGHT_ID, flight);
                startActivity(intent);
            }
        });

        tripCursor = getContentResolver().query(
                DatabaseDescription.Trip.buildTripUri(tripId),
                null,                        // The columns to return for each row
                null,                        // Selection criteria
                null,                        // Selection criteria
                null);
        if (tripCursor.getCount() > 0) {
            tripCursor.moveToFirst();
            tripName = tripCursor.getString(tripCursor.getColumnIndex(DatabaseDescription.Trip.COLUMN_NAME));
        }


        ((TextView) findViewById(R.id.name_text_view)).setText(tripName);

        // Button add a flight
        findViewById(R.id.button_add_flight).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.AddFlightActivity.class);
                intent.putExtra(CURRENT_TRIP_ID, tripId);
                startActivity(intent);
            }
        });

        //Button show map trip
        findViewById(R.id.button_show_trip_on_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.TripMapActivity.class);
                intent.putExtra(TRIP_CITIES, getCityNames());
                startActivity(intent);
            }
        });

    }

    private ArrayList<String> getCityNames(){
        ArrayList<String> cities = new ArrayList<>();
        for(Flight flight: flights) {
            cities.add(flight.getOriginName());
            cities.add(flight.getDestinationName());
        }
        return  cities;
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.flight_detail_activity_title));
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

    private Cursor getFlightsCursor() {
        return getContentResolver().query(
                DatabaseDescription.Flight.CONTENT_URI,
                null,                        // The columns to return for each row
                "trip_id=?",                        // Selection criteria
                new String[] { tripId.toString() },                        // Selection criteria
                null);
    }

}
