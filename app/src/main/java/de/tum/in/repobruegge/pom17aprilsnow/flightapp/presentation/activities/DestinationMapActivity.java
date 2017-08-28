package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.graphics.drawable.BitmapDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.POI;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Weather;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network.POITask;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.adapters.FlightArrayAdapter;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.adapters.POIArrayAdapter;

public class DestinationMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Toolbar toolbar;
    private String city = "Munich";
    private List<POI> poiList  = null;
    private LatLng location;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination_map);
        setUpToolbar();

        Weather weather = (Weather) getIntent().getSerializableExtra(FlightDetailsActivity.WEATHER);

        city = getIntent().getStringExtra(FlightDetailsActivity.DESTINATION_CITY);
        String localTime = getIntent().getStringExtra(FlightDetailsActivity.LOCAL_TIME_CITY);

        ((TextView) findViewById(R.id.city_name)).setText(String.format(getString(R.string.city_name),city));
        ((TextView) findViewById(R.id.label_dest_time)).setText(String.format(getString(R.string.label_local_time), localTime));
        ((TextView) findViewById(R.id.label_weather_desc_and_temp)).setText(String.format(getString(R.string.label_weather_desc_and_temp), weather.getDescription(), weather.getTemperatureC()));
        ((ImageView) findViewById(R.id.imageView)).setImageDrawable(new BitmapDrawable(getResources(), weather.getIcon()));

        poiList = getPoiList();

        ArrayAdapter<POI> arrayAdapter = new POIArrayAdapter(this, poiList);
        ListView listPOIDestinations = (ListView) findViewById(R.id.list_destiny_poi);
        listPOIDestinations.setAdapter(arrayAdapter);
        listPOIDestinations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                POI poi = poiList.get(position);
                LatLng poiLocation = new LatLng(poi.getLatitude(), poi.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(poiLocation,18));
            }
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


    }

    private List<POI> getPoiList(){
        Geocoder gc = new Geocoder(this);
        List<POI> pois = null;
        List<Address> addresses; // get the found Address Objects
        try {
            addresses = gc.getFromLocationName(city, 1);
            if (addresses.size() > 0){
                location = new LatLng(addresses.get(0).getLatitude(),addresses.get(0).getLongitude());
                pois = new POITask().execute(location.latitude + "", location.longitude + "", 500 + "").get();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return pois;
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

        addPOItoMap();
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location,14));
    }

    private void addPOItoMap () {
        for(POI poi : poiList) {
            LatLng poiLocation = new LatLng(poi.getLatitude(), poi.getLongitude());
            mMap.addMarker(new MarkerOptions()
                                .position(poiLocation)
                                .title(poi.getName())
                                 .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)
                                 ));
        }
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
