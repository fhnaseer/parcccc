package parccc.presentation.activities;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class ShowTripActivity extends AppCompatActivity {

    private Cursor tripCursor;
    private String tripName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Long tripId = (Long) getIntent().getSerializableExtra(de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities.TripListActivity.CURRENT_TRIP_ID);

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
    }

}
