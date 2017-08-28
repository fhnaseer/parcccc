package parccc.Network;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.BitmapUtil;

/**
 * POM17 AprilSnow FlightApp
 * Created by Vincent on 29.06.17.
 */

public class LoadPhotoTask extends AsyncTask<String, String, Bitmap> {

    @Override
    protected Bitmap doInBackground(String... param) {
        return loadPhoto(param);
    }

    @Nullable
    public Bitmap loadPhoto(String... param) {
        if (param.length != 1) {
            throw new RuntimeException("Incorrect number of arguments");
        }
        String request = String.format(Endpoints.PHOTO_ENDPOINT, param[0]);

        return BitmapUtil.loadBitmap(request);
    }


}
