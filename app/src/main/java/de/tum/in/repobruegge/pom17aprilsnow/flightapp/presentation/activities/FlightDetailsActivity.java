package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Weather;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network.TimeZoneTask;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network.WeatherTask;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.Interfaces.WeatherTaskDelegate;

public class FlightDetailsActivity extends AppCompatActivity  implements WeatherTaskDelegate{

    public static final String DESTINATION_CITY = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.city";
    public static final String WEATHER_DESCRIPTION = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.weatherDescription";
    public static final String WEATHER_TEMPERATURE = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.weatherTemperature";
    public static final String LOCAL_TIME_CITY = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.localTime";
    public static final String WEATHER_ICON = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.weatherIcon";
    public static final String WEATHER = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.weather";

    private Toolbar toolbar;
    private String city;
    private String weatherDescription;
    private String weatherTemperature;
    private String localTime;
    private Drawable weatherIcon;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight_details);
        setUpToolbar();

        Flight flight = (Flight) getIntent().getSerializableExtra(TripFlightListActivity.CURRENT_FLIGHT_ID);

        city = flight.getDestinationName();
        ((TextView) findViewById(R.id.label_from)).setText(String.format(getString(R.string.string_flight_from), flight.getOriginName(), city));
        ((TextView) findViewById(R.id.label_date_text)).setText(String.format(getString(R.string.label_date_text), Utils.formatTime(flight.getStartDate())));
        ((TextView) findViewById(R.id.label_start)).setText(String.format(getString(R.string.label_start_time_start_loc), Utils.formatTime(flight.getStartDate()), flight.getOriginAbbreviation()));
        ((TextView) findViewById(R.id.label_transfer)).setText(String.format(getString(R.string.label_transfer), flight.getNumberOfStopsFormatted()));
        ((TextView) findViewById(R.id.label_dest)).setText(String.format(getString(R.string.label_dest_loc_dest_time), flight.getDestinationAbbreviation(), Utils.formatTime(flight.getEndDate())));
        ((TextView) findViewById(R.id.label_terminal)).setText(String.format(getString(R.string.label_terminal), flight.getOriginTerminal()));
        ((TextView) findViewById(R.id.label_flight_number)).setText(String.format(getString(R.string.label_flight_number), flight.getFlightNumber()));
        ((TextView) findViewById(R.id.label_airline)).setText(String.format(getString(R.string.label_airline), flight.getAirlineName()));
        ((TextView) findViewById(R.id.label_weather)).setText(String.format(getString(R.string.label_weather), city));

        getWeather(city);

        localTime = getLocalTime(city);
        ((TextView) findViewById(R.id.label_dest_time)).setText(String.format(getString(R.string.label_local_time), localTime));

        findViewById(R.id.button_remove_from_trip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: Remove the selected flight from trip
                throw new RuntimeException("not implemented.");
            }
        });

        findViewById(R.id.button_show_destination_map).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Weather destiantionWeather = weather;
                Intent intent = new Intent(getApplicationContext(), DestinationMapActivity.class);
                intent.putExtra(DESTINATION_CITY,city);
                intent.putExtra(LOCAL_TIME_CITY,localTime);
                intent.putExtra(WEATHER,  destiantionWeather);
                startActivity(intent);
            }
        });


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

    public void getWeather(String city) {
        new WeatherTask(this).execute(city);
    }

    public String getLocalTime(String city) {
        String time = null;
        try {
            time = new TimeZoneTask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return time;
    }


    @Override
    public void setUpResult(Weather weather) {
       this.weather = weather;
        weatherIcon = new BitmapDrawable(getResources(), weather.getIcon());
        weatherDescription = weather.getDescription();
        weatherTemperature = weather.getTemperatureC();
        ((ImageView) findViewById(R.id.imageView)).setImageDrawable(weatherIcon);
        ((TextView) findViewById(R.id.label_weather_desc_and_temp)).setText(String.format(getString(R.string.label_weather_desc_and_temp), weatherDescription, weatherTemperature));
    }
}
