package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class TripListActivity extends NavigationItemActivity
        implements AdapterView.OnItemClickListener {

    public static final String CURRENT_TRIP_ID = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.currentflight";

    private Cursor tripsCursor;
    private SimpleCursorAdapter tripsCursorAdapter;

    public static final int UPDATE_TRIP_LIST_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listView = (ListView) findViewById(R.id.list_trips);

        tripsCursor = getTripsCursor();

        tripsCursorAdapter = getTripsCursorAdapter(tripsCursor);
        listView.setAdapter(tripsCursorAdapter);
        listView.setOnItemClickListener(this);

        Button create_new_trip_button = (Button) findViewById(R.id.button_create_new_trip);
        create_new_trip_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(TripListActivity.this, CreateTripActivity.class);
                // start create trip activity in such a way that finishing it can be intercepted
                startActivityForResult(intent, TripListActivity.UPDATE_TRIP_LIST_ACTIVITY);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case UPDATE_TRIP_LIST_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    // TODO: REPLACE WITH swapCursor for loaders
                    tripsCursorAdapter.changeCursor(getTripsCursor());
                }
                break;
        }
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_trip_list;
    }

    @Override
    public void onItemClick(
            AdapterView<?> parent, View item, int position, long rowID) {
        // Get the Cursor
        Cursor cursor = tripsCursorAdapter.getCursor();
        // Move to the selected contact
        cursor.moveToPosition(position);
        // Get the _ID value
        Long selectedTripId = cursor.getLong(cursor.getColumnIndex(DatabaseDescription.Trip.COLUMN_ID));
        Intent intent = new Intent(TripListActivity.this, TripFlightListActivity.class);
        intent.putExtra(CURRENT_TRIP_ID, selectedTripId);
        startActivity(intent);
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, TripListActivity.class);
    }

    private Cursor getTripsCursor() {
        return getContentResolver().query(
                DatabaseDescription.Trip.CONTENT_URI,
                null,                        // The columns to return for each row
                null,                        // Selection criteria
                null,                        // Selection criteria
                null);
    }

    private SimpleCursorAdapter getTripsCursorAdapter(Cursor tripsCursor) {
        // Defines a list of columns to retrieve from the Cursor and load into an output row
        String[] tripListColumns = { DatabaseDescription.Trip.COLUMN_NAME };

        // Defines a list of View IDs that will receive the Cursor columns for each row
        int[] tripListItems = { R.id.label_trip_list_name };

        return new SimpleCursorAdapter(
                getApplicationContext(),
                R.layout.layout_trip_list_row,
                tripsCursor,
                tripListColumns,
                tripListItems,
                0
        );
    }

}
