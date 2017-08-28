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

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Parking;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SearchParkingActivity extends NavigationItemActivity implements OnMapReadyCallback {
    private GoogleMap _map;
    private List<Parking> _availableParkings = null;
    private Marker _selectedMarker = null;
    private LatLng _currentPosition = null;

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchParkingActivity.class);
    }

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
        _availableParkings = new ArrayList<Parking>();
        _currentPosition = new LatLng(48.150474, 11.619199);
        addParking("Gotthelfstr 69", 48.141653, 11.624478, 5, true, 10, 0);
        addParking("Richard-Strauss-Str. 81", 48.147469, 11.615880, 6, true, 12, 0);
        addParking("Oberföhringer str. 2", 48.151131, 11.6087245, 4, true, 14, 0);
        addParking("Elektrastr. 4", 48.153282, 11.621051, 6, false, 12, 0);
        addParking("Englschalkinger str. 136", 48.153282, 11.621051, 5, false, 10, 0);
        addParking("Denninger str 169", 48.147873, 11.627997, 4, true, 12, 0);
    }

    private void addParking(String name, double latitude, double longitude, double rent, boolean isSmall, int hour, int minute) {
        Parking parking = new Parking(name, new LatLng(latitude, longitude), rent, isSmall, hour, minute);
        if (Parking.ApplyFilter == true) {
            if (parking.IsSmall == Parking.CurrentFilter.IsSmall && parking.Hour > Parking.CurrentFilter.Hour)
                _availableParkings.add(parking);
        } else
            _availableParkings.add(parking);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search_parking;
    }

    public void seeReservations(View view) {

    }

    public void bookParking(View view) {
         if (_selectedMarker == null) {
            Toast.makeText(getApplicationContext(), "Please select a parking.", Toast.LENGTH_SHORT).show();
            return;
        }
        String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+_currentPosition.latitude+","+_currentPosition.longitude+"&daddr="+_selectedMarker.getPosition().latitude+","+_selectedMarker.getPosition().longitude;
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
        startActivity(Intent.createChooser(intent, "Select an application"));
    }

    public void searchOptions(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchOptionsActivity.class);
        startActivity(intent);
    }

    public void seeAll(View view) {
        Intent intent = new Intent(getApplicationContext(), SearchResultsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        _map = googleMap;
        _map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean  onMarkerClick(Marker marker) {
                if (_selectedMarker != null)
                    _selectedMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE));
                if (!marker.equals(_selectedMarker)) {
                    marker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                    _selectedMarker = marker;
                }
                return true;
            }
        });
        addMarkersToMap();
        _map.animateCamera(CameraUpdateFactory.newLatLngZoom(_currentPosition,14));
    }

    private void addMarkersToMap() {
        _map.addMarker(new MarkerOptions()
                .position(_currentPosition)
                .title("Current Location")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        for (Parking parking : _availableParkings) {
            _map.addMarker(new MarkerOptions()
                    .position(parking.Location)
                    .title(parking.Name)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        }
    }
}
