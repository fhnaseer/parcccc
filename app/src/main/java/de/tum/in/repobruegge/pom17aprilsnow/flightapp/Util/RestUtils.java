//package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util;
//
//import android.support.annotation.NonNull;
//
//import org.json.JSONException;
//import org.json.JSONObject;
//import org.restlet.data.MediaType;
//import org.restlet.data.Method;
//import org.restlet.representation.Representation;
//import org.restlet.resource.ClientResource;
//
//import java.io.IOException;
//
///**
// * Created by Vincent Bode on 02.07.2017.
// */
//
//public class RestUtils {
//    @NonNull
////    public static JSONObject executeJSONPost(JSONObject jsonObject, String loginEndpoint) {
//
//        try {
//            ClientResource requestResource = new ClientResource(Method.POST, loginEndpoint);
//
//            Representation post = requestResource.post(jsonObject, MediaType.APPLICATION_JSON);
//            return new JSONObject(post.getText());
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void ensureNumberOfArguments(int numberRequiredArguments, String[] args) {
//        if (args.length < numberRequiredArguments) {
//            throw new RuntimeException("Wrong number of arguments to LoginTask: " + args.length);
//        }
//    }
//}
