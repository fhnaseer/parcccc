package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model;

import com.google.android.gms.maps.model.LatLng;

public class Parking {
    public final String Name;
    public final LatLng Location;
    public final double Rent;
    public final boolean IsSmall;

    public Parking(String name, LatLng location, double rent, boolean isSmall) {
        Name = name;
        Location = location;
        Rent = rent;
        IsSmall = isSmall;
    }
}
