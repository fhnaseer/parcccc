package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.POI;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SeachParkingActivity extends NavigationItemActivity implements OnMapReadyCallback {
    private GoogleMap _map;
    public static final String CURRENT_TRIP_ID = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.currentflight";
    private List<POI> _availableParkings = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _availableParkings = new ArrayList<POI>();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search_parking;
    }

    public static Intent newIntent(Context context) {
        return new Intent(context, SeachParkingActivity.class);
    }

    public void seeReservations(View view) {

    }

    public void bookParking(View view) {

    }

    public void seeAll(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchOptionsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _map = googleMap;
        addMarkersToMap();
    }

    private void addMarkersToMap() {
        for(POI parking : _availableParkings) {
            LatLng poiLocation = new LatLng(parking.getLatitude(), parking.getLongitude());
            _map.addMarker(new MarkerOptions()
                    .position(poiLocation)
                    .title(parking.getName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }
}
