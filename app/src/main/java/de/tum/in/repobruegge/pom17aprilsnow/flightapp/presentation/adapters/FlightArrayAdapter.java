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
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Util.Utils;

/**
 * Created by Yesika Ramirez on 21.06.17.
 */

public class FlightArrayAdapter extends ArrayAdapter<Flight> {
    private Context context;
    private List<Flight> flights;

    public FlightArrayAdapter(@NonNull Context context, @NonNull List<Flight> objects) {
        super(context, R.layout.layout_flight_list_row, objects);
        flights = objects;
        this.context = context;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = layoutInflater.inflate(R.layout.layout_flight_list_row, parent, false);
        Flight flight = flights.get(position);
        ((TextView) rowView.findViewById(R.id.label_list_origin)).setText(String.format("%s %s", Utils.formatTime(flight.getStartDate()), flight.getOriginAbbreviation()));
        ((TextView) rowView.findViewById(R.id.label_list_dest)).setText(String.format("%s %s", flight.getDestinationAbbreviation(), Utils.formatTime(flight.getEndDate())));
        ((TextView) rowView.findViewById(R.id.label_list_origin_name)).setText(flight.getOriginName());
        ((TextView) rowView.findViewById(R.id.label_list_destination_name)).setText(flight.getDestinationName());
        ((TextView) rowView.findViewById(R.id.label_list_date)).setText(Utils.formatTime(flight.getStartDate())
        ); /**getString(R.string.label_date_text) +*/
        ((TextView) rowView.findViewById(R.id.label_list_airline)).setText(flight.getAirlineName());
        return rowView;
    }
}
