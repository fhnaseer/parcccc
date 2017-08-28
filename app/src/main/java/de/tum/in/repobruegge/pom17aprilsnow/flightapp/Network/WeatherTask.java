//package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network;
//
//import android.os.AsyncTask;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.restlet.resource.ClientResource;
//
//import java.io.IOException;
//
//import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.BitmapUtil;
//
///**
// * Created by yesik on 29.06.17.
// */
//
//public class WeatherTask extends AsyncTask<String, String, Weather> {
//
//    WeatherTaskDelegate delegate;
//
//    public WeatherTask (WeatherTaskDelegate delegate){
//        this.delegate = delegate;
//    }
//
//    @Override
//    protected Weather doInBackground(String... param) {
//        try {
//            String rawAnswer = new ClientResource(Endpoints.WEATHER_ENDPOINT + param[0]).get().getText();
//
//            JSONObject reader = new JSONObject(rawAnswer);
//            JSONObject currentCondition = (JSONObject) reader.getJSONObject("data").getJSONArray("current_condition").get(0);
//            Weather weather = new Weather();
//
//            String temperatureC = currentCondition.getString("temp_C");
//            String urlIcon = ((JSONObject)(currentCondition.getJSONArray("weatherIconUrl").get(0))).getString("value");
//            String description = ((JSONObject)(currentCondition.getJSONArray("weatherDesc").get(0))).getString("value");
//
//            weather.setDescription(description);
//            weather.setIcon(BitmapUtil.loadBitmap(urlIcon));
//            weather.setTemperatureC(temperatureC);
//
//            return weather;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    @Override
//    protected void onPostExecute(Weather weather) {
//        super.onPostExecute(weather);
//        delegate.setUpResult(weather);
//    }
//}
