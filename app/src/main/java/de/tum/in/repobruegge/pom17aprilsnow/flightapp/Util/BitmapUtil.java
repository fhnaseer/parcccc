//package de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//
//import java.net.URL;
//
///**
// * POM17 AprilSnow FlightApp
// * Created by yesik on 29.06.17.
// */
//
//public class BitmapUtil {
//    public static Bitmap loadBitmap(String url) {
//        Bitmap bitmap = null;
//        try {
//            bitmap = BitmapFactory.decodeStream((new URL(url)).openConnection().getInputStream());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return bitmap;
//    }
//}
