//package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Network;
//
//import android.os.AsyncTask;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.AuthenticationToken;
//import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.RestUtils;
//
///**
// * Created by Vincent Bode on 02.07.2017.
// */
//
//public class LoginTask extends AsyncTask<String, String, AuthenticationToken> {
//    @Override
//    // Arg[0] is username, Arg[1] is password, Result is token
//    protected AuthenticationToken doInBackground(String... args) {
//        try {
//            RestUtils.ensureNumberOfArguments(2, args);
//
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("Username", args[0]);
//            jsonObject.put("Password", args[1]);
//
//            String loginEndpoint = Endpoints.LOGIN_ENDPOINT;
//            JSONObject jsonResponse = RestUtils.executeJSONPost(jsonObject, loginEndpoint);
//
//            return new AuthenticationToken(jsonResponse.getString("Token"));
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//}
