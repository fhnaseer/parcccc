package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Flight;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.POI;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils;

/**
 * Created by Yesika Ramirez on 21.06.17.
 */

public class POIArrayAdapter extends ArrayAdapter<POI> {
    private Context context;
    private List<POI> pois;

    public POIArrayAdapter(@NonNull Context context, @NonNull List<POI> objects) {
        super(context, R.layout.layout_poi_list_row, objects);
        pois = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.layout_poi_list_row, parent, false);

        POI poi = pois.get(position);
        ((TextView) rowView.findViewById(R.id.label_poi_list_name)).setText(poi.getName());
        return rowView;
    }
}
