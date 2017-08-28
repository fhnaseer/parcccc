// DatabaseDescription.java
// Describes the table name and column names for this app's database,
// and other information required by the ContentProvider

package parccc.Model.database;

/**
 * Created by dmitriinechaev on 31/06/17.
 */

import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns; // provides _ID column

public class DatabaseDescription {
    public static final String TRIP_AUTHORITY =
            "de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.trips";

    // base URI used to interact with the TripContentProvider
    private static final Uri BASE_TRIP_CONTENT_URI =
            Uri.parse("content://" + TRIP_AUTHORITY);

    public static final String FLIGHT_AUTHORITY =
            "de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.flights";

    // base URI used to interact with the TripContentProvider
    private static final Uri BASE_FLIGHT_CONTENT_URI =
            Uri.parse("content://" + FLIGHT_AUTHORITY);

    // nested class defines contents of the trips table
    public static final class Trip implements BaseColumns {
        public static final String TABLE_NAME = "trips"; // table's name

        // Uri for the contacts table
        public static final Uri CONTENT_URI =
                BASE_TRIP_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names for trips table's columns
        public static final String COLUMN_ID   = "_id";
        public static final String COLUMN_NAME = "name";

        // creates a Uri for a specific trip
        public static Uri buildTripUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }

    // nested class defines contents of the flights table
    public static final class Flight implements BaseColumns {
        public static final String TABLE_NAME = "flights"; // table's name

        // Uri for the flights table
        public static final Uri CONTENT_URI =
                BASE_FLIGHT_CONTENT_URI.buildUpon().appendPath(TABLE_NAME).build();

        // column names for flights table's columns
        public static final String COLUMN_TRIP_ID                  = "trip_id";
        public static final String COLUMN_FLIGHT_NUMBER            = "flight_number";
        public static final String COLUMN_DEPARTURE_TIME           = "departure_time";
        public static final String COLUMN_ARRIVAL_TIME             = "arrival_time";
        public static final String COLUMN_ORIGIN_GATE              = "origin_gate";
        public static final String COLUMN_ORIGIN_TERMINAL          = "origin_terminal";
        public static final String COLUMN_AIRLINE_NAME             = "airline_name";
        public static final String COLUMN_ORIGIN_NAME              = "origin_name";
        public static final String COLUMN_ORIGIN_ABBREVIATION      = "origin_abbreviation";
        public static final String COLUMN_DESTINATION_NAME         = "destination_name";
        public static final String COLUMN_DESTINATION_ABBREVIATION = "destination_abbreviation";
        public static final String COLUMN_NUMBER_OF_STOPS          = "number_of_stops";

        // creates a Uri for a specific flight
        public static Uri buildFlightUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
