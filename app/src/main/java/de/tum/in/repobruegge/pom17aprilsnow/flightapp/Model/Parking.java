package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model;

import com.google.android.gms.maps.model.LatLng;

public class Parking {
    public static Parking CurrentFilter = new Parking("", new LatLng(0, 0), 4, true, 0, 0);
    public static boolean ApplyFilter = false;

    public String Name;
    public LatLng Location;
    public double Rent;
    public boolean IsSmall;
    public int Hour;
    public int Minute;

    public Parking(String name, LatLng location, double rent, boolean isSmall, int hour, int minute) {
        Name = name;
        Location = location;
        Rent = rent;
        IsSmall = isSmall;
        Hour = hour;
        Minute = minute;
    }
}
