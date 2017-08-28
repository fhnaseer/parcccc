package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.RadioButton;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Parking;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.TimePickerFragment;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SearchOptionsActivity extends NavigationItemActivity {

//    private Feedback _feedback = new Feedback();

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchOptionsActivity.class);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search_options;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        newFragment.show(fragmentManager, "timePicker");
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.radio_bigCar:
                Parking.CurrentFilter.IsSmall = !checked;
                break;
            case R.id.radio_smallCar:
                Parking.CurrentFilter.IsSmall = checked;
                break;
        }
    }

    public void setOptions(View v) {
        Parking.ApplyFilter = true;
        Intent intent = new Intent(getApplicationContext(), SearchParkingActivity.class);
        startActivity(intent);
    }
}
