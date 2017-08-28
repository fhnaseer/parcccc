package parccc.Model;

import android.graphics.Bitmap;

import java.io.Serializable;

/**
 * Created by yesik on 29.06.17.
 */

public class Weather implements Serializable {

    private String temperatureC;
    private String description;
    transient private Bitmap icon;

    public String getTemperatureC() {
        return temperatureC;
    }

    public void setTemperatureC(String temperatureC) {
        this.temperatureC = temperatureC;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }
}
