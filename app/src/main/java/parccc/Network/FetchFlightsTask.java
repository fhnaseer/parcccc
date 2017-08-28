package parccc.Network;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.engine.Engine;
import org.restlet.ext.json.JsonConverter;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.resource.ClientResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils;

import static de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils.parseDate;

/**
 * POM17 AprilSnow FlightApp
 * Created by yesik on 29.06.17.
 */

public class FetchFlightsTask extends AsyncTask<String, String, List<Flight>> {

    private static final boolean MOCKED = false;

    @Override
    protected List<Flight> doInBackground(String... param) {
        try {
            return fetchFlights(param);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @NonNull
    public List<Flight> fetchFlights(String... param) throws JSONException, IOException {

        Engine.getInstance().getRegisteredConverters().add(new JsonConverter());
        List<Flight> flights = new ArrayList<>();

        if (MOCKED) {
            flights.add(new Flight(param[0]+"lala", param[1]+"tutu", param[0], param[1], parseDate(param[2]), parseDate(param[2]), 0, "Terminal 1", "YW0042", "MOCK-AIR"));
//            flights.add(new Flight(param[1]+"tutu", param[0]+"lala", param[1], param[0], new Date(), new Date(), 1, "Terminal F", "YW1337", "MOCK-AIR"));
            return flights;
        }

        JSONObject requestObject = new JSONObject();
        JSONObject requestSliceObject = new JSONObject();

        requestSliceObject.put("origin", param[0]);
        requestSliceObject.put("destination", param[1]);
        requestSliceObject.put("date", param[2]);
        requestSliceObject.put("maxStops", 0);

        requestObject.put("passengers", new JSONObject("{\n" +
                "      \"adultCount\": 1,\n" +
                "      \"infantInLapCount\": 0,\n" +
                "      \"infantInSeatCount\": 0,\n" +
                "      \"childCount\": 0,\n" +
                "      \"seniorCount\": 0\n" +
                "    }"));

        requestObject.put("solutions", 20);
        requestObject.put("refundable", false);
        requestObject.put("slice", new JSONArray().put(requestSliceObject));
        String rawAnswer = new ClientResource(Endpoints.FLIGHTS_ENDPOINT).post(new JsonRepresentation(new JSONObject().put("request", requestObject))).getText();

        JSONObject reader = new JSONObject(rawAnswer);
        JSONObject trips = reader.getJSONObject("trips");
        JSONArray tripArray = trips.getJSONArray("tripOption");

        for (int tripIndex = 0; tripIndex < tripArray.length(); tripIndex++) {
            JSONObject tripObject = tripArray.getJSONObject(tripIndex);
            JSONArray sliceArray = tripObject.getJSONArray("slice");
            for (int sliceIndex = 0; sliceIndex < sliceArray.length(); sliceIndex++) {
                JSONObject sliceObject = sliceArray.getJSONObject(sliceIndex);
                JSONArray segmentArray = sliceObject.getJSONArray("segment");
                for (int segmentIndex = 0; segmentIndex < segmentArray.length(); segmentIndex++) {
                    Flight flight = new Flight();

                    JSONObject segmentObject = segmentArray.getJSONObject(segmentIndex);

                    //Get Flight Number
                    JSONObject flightNumberObject = segmentObject.getJSONObject("flight");
                    flight.setFlightNumber(flightNumberObject.getString("carrier") + flightNumberObject.getString("number"));

                    JSONArray legArray = segmentObject.getJSONArray("leg");
                    for (int legIndex = 0; legIndex < legArray.length(); legIndex++) {

                        JSONObject legObject = legArray.getJSONObject(legIndex);
                        if (legIndex > 0) {
                            throw new RuntimeException("Not implemented");
                        }

                        flight.setStartDate(Utils.parseTime(legObject.getString("departureTime")));
                        flight.setEndDate(Utils.parseTime(legObject.getString("arrivalTime")));

                        flight.setAirlineName(getAirlineName(flightNumberObject.getString("carrier"), trips));

                        flight.setOriginAbbreviation(legObject.getString("origin"));
                        flight.setDestinationAbbreviation(legObject.getString("destination"));

                        flight.setNumberOfStops(0);

                        flight.setOriginName(getCityName(flight.getOriginAbbreviation(), trips));
                        flight.setDestinationName(getCityName(flight.getDestinationAbbreviation(), trips));

                        flight.setOriginTerminal(legObject.getString("originTerminal"));

                        flights.add(flight);
                    }
                }
            }
        }

        return flights;
    }

    private String getCityName(String airportId, JSONObject trips) throws JSONException {
        String cityIdentifier = getCityIdentifierForAirportIdentifier(airportId, trips);
        return extractFromDataFields(cityIdentifier, trips, "city", "name", "code");
    }

    private String getCityIdentifierForAirportIdentifier(String originAbbreviation, JSONObject trips) throws JSONException {
        return extractFromDataFields(originAbbreviation, trips, "airport", "city", "code");
    }

    private String getAirlineName(String carrier, JSONObject trips) throws JSONException {
        return extractFromDataFields(carrier, trips, "carrier", "name", "code");
    }

    private String extractFromDataFields(String searchValue, JSONObject trips, String dataArrayField, String resultField, String comparisonField) throws JSONException {
        JSONArray dataArray = trips.getJSONObject("data").getJSONArray(dataArrayField);
        for (int i = 0; i < dataArray.length(); i++) {
            JSONObject carrierInfoObject = dataArray.getJSONObject(i);
            if (carrierInfoObject.getString(comparisonField).equalsIgnoreCase(searchValue)) {
                return carrierInfoObject.getString(resultField);
            }
        }
        return "Unknown";
    }

}
