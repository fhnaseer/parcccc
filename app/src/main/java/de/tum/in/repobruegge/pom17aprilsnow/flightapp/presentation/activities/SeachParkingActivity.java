package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.POI;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SeachParkingActivity extends NavigationItemActivity implements OnMapReadyCallback {
    private GoogleMap _map;
    public static final String CURRENT_TRIP_ID = "de.tum.in.repobruegge.pom16aprilsnow.flightapp.currentflight";
    private List<POI> _availableParkings = null;
    private POI _selectedParking = null;
    private LatLng _currentPosition = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addParkings();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void addParkings() {
        _availableParkings = new ArrayList<POI>();
        _currentPosition = new LatLng(48.150474, 11.619199);
        addParking("Arabellastr 11", 48.150474, 11.619199);
        addParking("Gotthelfstr 69", 48.141653,11.624478);
        addParking("Richard-Strauss-Str. 81", 48.147469, 11.615880);
        addParking("Oberföhringer str. 2", 48.151131, 11.608724);
        addParking("Elektrastr. 4",         48.153282, 11.621051);
        addParking("Englschalkinger str. 136", 48.153282, 11.621051);
        addParking("Denninger str 169", 48.147873, 11.627997);
    }

    private void addParking(String name, double latitude, double longitude) {
        POI parking = new POI();
        parking.setName(name);
        parking.setLatitude(latitude);
        parking.setLongitude(longitude);
        _availableParkings.add(parking);
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
        _map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean  onMarkerClick(Marker marker) {
                for (POI parking : _availableParkings) {
                    if (marker.getPosition().latitude == parking.getLatitude() && marker.getPosition().longitude == parking.getLongitude())
                    {
                        _selectedParking = parking;
                        String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+_currentPosition.latitude+","+_currentPosition.longitude+"&daddr="+_selectedParking.getLatitude()+","+_selectedParking.getLongitude();
                        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(Intent.createChooser(intent, "Select an application"));
                    }
                }
                return true;
            }
        });
        addMarkersToMap();
        _map.animateCamera(CameraUpdateFactory.newLatLngZoom(_currentPosition,14));
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
