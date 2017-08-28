package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Weather;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class TripMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Toolbar toolbar;
    List<String> cities;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_map);
        setUpToolbar();

        cities = (List<String>) getIntent().getSerializableExtra(TripFlightListActivity.TRIP_CITIES);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        ArrayList <LatLng> locations = new ArrayList<>();
        Geocoder gc = new Geocoder(this);
        PolylineOptions polylineOptions = new PolylineOptions();


        mMap.addPolyline(polylineOptions);

        for (String city: cities) {
            List<Address> addresses = null; // get the found Address Objects
            try {
                addresses = gc.getFromLocationName(city, 1);
                if (addresses.size() > 0){
                    LatLng location = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                    polylineOptions.add(location);
                    polylineOptions.color(Color.RED);
                    mMap.addMarker(new MarkerOptions().position(location).title(city));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mMap.addPolyline(polylineOptions);

       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,14));
    }

    //Search the coordinates for all the cities
    public List<LatLng> getLocations (List<String> cities) {
        ArrayList <LatLng> locations = new ArrayList<>();
        Geocoder gc = new Geocoder(this);

        for (String city: cities) {
            List<Address> addresses = null; // get the found Address Objects
            try {
                addresses = gc.getFromLocationName(city, 1);
                if (addresses.size() > 0){
                    locations.add(new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude()));
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return locations;
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getString(R.string.map_activity_title));
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
}
