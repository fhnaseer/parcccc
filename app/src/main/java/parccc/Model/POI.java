package parccc.Model;

import java.util.LinkedList;
import java.util.List;

/**
 * POM17 AprilSnow FlightApp
 * Created by Vincent Bode on 11.07.2017.
 */

public class POI {
    private double latitude;
    private double longitude;
    private String iconURL;
    private String identifier;
    private String name;
    private String vicinity;
    private double rating;
    private List<String> photoReferences;

    public POI() {
        photoReferences = new LinkedList<>();
    }

    public String getIconURL() {
        return iconURL;
    }

    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVicinity() {
        return vicinity;
    }

    public void setVicinity(String vicinity) {
        this.vicinity = vicinity;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void addPhotoReference(String photo_reference) {
        photoReferences.add(photo_reference);
    }
}
