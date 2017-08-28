package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.User;

import static de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.RestUtils.ensureNumberOfArguments;
import static de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.RestUtils.executeJSONPost;

/**
 * Created by Vincent Bode on 02.07.2017.
 */

public class CreateUserTask extends AsyncTask<String, String, User> {
    @Override
    protected User doInBackground(String... args) {
        try {
            ensureNumberOfArguments(4, args);

            JSONObject requestObject = new JSONObject();
            requestObject.put("Username", args[0]);
            requestObject.put("Password", args[1]);
            requestObject.put("First Name", args[2]);
            requestObject.put("Last Name", args[3]);
            if (args.length == 5) {
                requestObject.put("Picture", args[4]);
            }

            JSONObject responseObject = executeJSONPost(requestObject, Endpoints.REGISTER_ENDPOINT);

            //TODO Parse Response
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return new User();
    }
}
