package parccc.presentation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;


/**
 * Created by dmitriinechaev on 01/07/17.
 */

public class TripArrayAdapter extends ArrayAdapter<Trip>{
    private Context context;
    private List<Trip> trips;

    public TripArrayAdapter(@NonNull Context context, @NonNull List<Trip> objects) {
        super(context, R.layout.layout_trip_list_row, objects);
        trips = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.layout_trip_list_row, parent, false);
        Trip trip = trips.get(position);
        ((TextView) rowView.findViewById(R.id.label_trip_list_name)).setText(trip.getName());
        return rowView;
    }

}
