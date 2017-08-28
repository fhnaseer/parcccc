package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;
import org.restlet.resource.ClientResource;

import java.io.IOException;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Weather;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.BitmapUtil;

/**
 * Created by yesik on 29.06.17.
 */

public class TimeZoneTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... param) {
        try {
            String rawAnswer = new ClientResource(Endpoints.TIMEZONE_ENDPOINT ).get().getText();

            JSONObject reader = new JSONObject(rawAnswer);
            JSONObject localTime = (JSONObject) reader.getJSONObject("LocalTime");

            return rawAnswer;
        } catch (IOException e) {
            e.printStackTrace();
        }catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
