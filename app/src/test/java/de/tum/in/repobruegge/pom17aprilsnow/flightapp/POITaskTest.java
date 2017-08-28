package de.tum.in.repobruegge.pom17aprilsnow.flightapp;

import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.POI;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network.POITask;

import static org.junit.Assert.assertTrue;

public class POITaskTest {

    @Before
    public void setup() {
    }

    @Test
    public void fetchPOIs() throws Exception {
        double latitude = 48.2617455;
        double longitude = 11.6716109;

        POITask poiTask = new POITask();
        Collection<POI> poiCollection = poiTask.getPOIs(latitude + "", longitude + "", 500 + "");

        if (poiCollection == null) {
            throw new RuntimeException("Result is null");
        }

        for (POI poi : poiCollection) {
            assertTrue("Range Latitude", poi.getLatitude() - latitude < 0.05);
            assertTrue("Range Longitude", poi.getLongitude() - longitude < 0.05);
        }
    }

//    @Test
//    public void fetchPicture() {
//        LoadPhotoTask task = new LoadPhotoTask();
//        Bitmap bitmap = task.loadPhoto("CmRaAAAAb_Km2no0Ed3Tu_yu8EP6thEwStJxLL5eekaycwmAxxqRJ1JNXb5IckATjugU1H_V3S1SPqTc2JODIFGppM-m42S1eOOZrOZbjThCckU5OGGcSx7Ga9T7fFOu6wEmoBMwEhBEy21kvoveL5Z5gK2JVDZdGhQ76R0E1capIQeYYyqUEhuWgBlGHQ");
//        assertTrue("Loaded bitmap width", bitmap.getWidth() > 0);
//    }

}
