package parccc.Model;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;

import parccc.Model.database.DatabaseDescription;
import parccc.Util.Utils;

/**
 * Created by dmitriinechaev on 25/07/17.
 */

public class FlightConverter {

    public static Flight loadOneFromDB(HashMap<String, String> persistentFlight) {
        Flight flight = new Flight();

        flight.setFlightNumber(persistentFlight.get(DatabaseDescription.Flight.COLUMN_FLIGHT_NUMBER));
        flight.setOriginTerminal(persistentFlight.get(DatabaseDescription.Flight.COLUMN_ORIGIN_TERMINAL));

        flight.setStartDate(Utils.parseDBDate(persistentFlight.get(DatabaseDescription.Flight.COLUMN_DEPARTURE_TIME)));
        flight.setEndDate(Utils.parseDBDate(persistentFlight.get(DatabaseDescription.Flight.COLUMN_ARRIVAL_TIME)));

        flight.setAirlineName(persistentFlight.get(DatabaseDescription.Flight.COLUMN_AIRLINE_NAME));
        flight.setNumberOfStops(Integer.parseInt(persistentFlight.get(DatabaseDescription.Flight.COLUMN_NUMBER_OF_STOPS)));

        flight.setOriginAbbreviation(persistentFlight.get(DatabaseDescription.Flight.COLUMN_ORIGIN_ABBREVIATION));
        flight.setOriginName(persistentFlight.get(DatabaseDescription.Flight.COLUMN_ORIGIN_NAME));

        flight.setDestinationAbbreviation(persistentFlight.get(DatabaseDescription.Flight.COLUMN_DESTINATION_ABBREVIATION));
        flight.setDestinationName(persistentFlight.get(DatabaseDescription.Flight.COLUMN_DESTINATION_NAME));

        return flight;
    }

    public static ArrayList<Flight> loadManyFromDB(Cursor flightCursor) {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        if (flightCursor.moveToFirst()) {
            do {
                HashMap<String, String> map = new HashMap<String, String>();
                for (int i = 0; i < flightCursor.getColumnCount(); i++) {
                    map.put(flightCursor.getColumnName(i), flightCursor.getString(i));
                }
                flights.add(loadOneFromDB(map));
            } while (flightCursor.moveToNext());
        }
        return flights;
    }

    public static ContentValues dumpOneToDB(Flight flight) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DatabaseDescription.Flight.COLUMN_FLIGHT_NUMBER, flight.getFlightNumber());
        contentValues.put(DatabaseDescription.Flight.COLUMN_DEPARTURE_TIME, flight.getStartDate().toString());
        contentValues.put(DatabaseDescription.Flight.COLUMN_ARRIVAL_TIME, flight.getEndDate().toString());
        contentValues.put(DatabaseDescription.Flight.COLUMN_ORIGIN_GATE, "");
        contentValues.put(DatabaseDescription.Flight.COLUMN_ORIGIN_TERMINAL, flight.getOriginTerminal());
        contentValues.put(DatabaseDescription.Flight.COLUMN_AIRLINE_NAME, flight.getAirlineName());
        contentValues.put(DatabaseDescription.Flight.COLUMN_ORIGIN_NAME, flight.getOriginName());
        contentValues.put(DatabaseDescription.Flight.COLUMN_ORIGIN_ABBREVIATION, flight.getOriginAbbreviation());
        contentValues.put(DatabaseDescription.Flight.COLUMN_DESTINATION_NAME, flight.getDestinationName());
        contentValues.put(DatabaseDescription.Flight.COLUMN_DESTINATION_ABBREVIATION, flight.getDestinationAbbreviation());
        contentValues.put(DatabaseDescription.Flight.COLUMN_NUMBER_OF_STOPS, flight.getNumberOfStops());

        return contentValues;
    }
}
