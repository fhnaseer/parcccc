package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription.Trip;

/**
 * Created by dmitriinechaev on 31/06/17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "FlightApp.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TRIPS_TABLE_STATEMENT =
            "CREATE TABLE " + Trip.TABLE_NAME + "(" +
                    Trip._ID +         " integer primary key, " +
                    Trip.COLUMN_NAME + " TEXT)";

    private static final String CREATE_FLIGHTS_TABLE_STATEMENT =
            "CREATE TABLE " + Flight.TABLE_NAME + "(" +
                    Flight._ID                             + " integer primary key, " +
                    Flight.COLUMN_TRIP_ID                  + " integer references " + Trip.TABLE_NAME + ", " +
                    Flight.COLUMN_FLIGHT_NUMBER            + " text not null, " +
                    Flight.COLUMN_DEPARTURE_TIME           + " text not null, " +
                    Flight.COLUMN_ARRIVAL_TIME             + " text not null, " +
                    Flight.COLUMN_ORIGIN_GATE              + " text not null, " +
                    Flight.COLUMN_ORIGIN_TERMINAL          + " text not null, " +
                    Flight.COLUMN_AIRLINE_NAME             + " text not null, " +
                    Flight.COLUMN_ORIGIN_NAME              + " text not null, " +
                    Flight.COLUMN_ORIGIN_ABBREVIATION      + " text not null, " +
                    Flight.COLUMN_DESTINATION_NAME         + " text not null, " +
                    Flight.COLUMN_DESTINATION_ABBREVIATION + " text not null, " +
                    Flight.COLUMN_NUMBER_OF_STOPS          + " text not null);";

    // constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // creates the tables when the database is created
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TRIPS_TABLE_STATEMENT);
        db.execSQL(CREATE_FLIGHTS_TABLE_STATEMENT);
    }

    // normally defines how to upgrade the database when the schema changes
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) { }


}
