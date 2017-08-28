//package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network;
//
//import android.os.AsyncTask;
//import android.support.annotation.Nullable;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.restlet.resource.ClientResource;
//
//import java.io.IOException;
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.List;
//
//import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.POI;
//
///**
// * AprilSnow FlightApp
// * Created by Vincent on 29.06.17.
// */
//
//public class POITask extends AsyncTask<String, String, List<POI>> {
//
//    @Override
//    protected List<POI> doInBackground(String... param) {
//        return getPOIs(param);
//    }
//
//    @Nullable
//    public List<POI> getPOIs(String... param) {
//        try {
//            if (param.length != 3) {
//                throw new RuntimeException("Incorrect number of arguments");
//            }
//            String request = String.format(Endpoints.MAPS_ENDPOINT, param[0], param[1], param[2]);
//            String rawAnswer = new ClientResource(request).get().getText();
//
//            JSONObject reader = new JSONObject(rawAnswer);
//            JSONArray results = reader.getJSONArray("results");
//            LinkedList<POI> pois = new LinkedList<>();
//
//            for (int i = 0; i < results.length(); i++) {
//                JSONObject poiObject = results.getJSONObject(i);
//                POI poi = new POI();
//
//                //Latitude and Longitude
//                JSONObject geometryObject = poiObject.getJSONObject("geometry");
//                JSONObject locationObject = geometryObject.getJSONObject("location");
//
//                poi.setLatitude(locationObject.getDouble("lat"));
//                poi.setLongitude(locationObject.getDouble("lng"));
//
//
//                poi.setIconURL(poiObject.optString("icon"));
//                poi.setIdentifier(poiObject.getString("id"));
//                poi.setName(poiObject.getString("name"));
//
//                poi.setVicinity(poiObject.optString("vicinity"));
//                poi.setRating(poiObject.optDouble("rating"));
//
//                JSONArray photos = poiObject.optJSONArray("photos");
//                if (photos != null) {
//                    for (int p = 0; p < photos.length(); p++) {
//                        poi.addPhotoReference(photos.getJSONObject(p).getString("photo_reference"));
//                    }
//                }
//                pois.addLast(poi);
//            }
//
//            return pois;
//        } catch (IOException | JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//}
