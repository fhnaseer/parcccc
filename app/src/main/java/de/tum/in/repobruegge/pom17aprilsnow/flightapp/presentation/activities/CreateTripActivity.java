package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.database.DatabaseDescription;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class CreateTripActivity extends AppCompatActivity {
    private Button save_trip_button;
    private TextInputLayout nameTextInputLayout;
    private RelativeLayout relativeLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_trip);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        relativeLayout = (RelativeLayout) findViewById(R.id.create_trip_layout);

        nameTextInputLayout = (TextInputLayout) findViewById(R.id.name_text_input_layout);
        nameTextInputLayout.getEditText().addTextChangedListener(nameChangedListener);

        save_trip_button = (Button) findViewById(R.id.button_save_trip);
        save_trip_button.setOnClickListener(saveTripButtonClicked);
        updateSaveTripButton();
    }

    // detects when the text in the name_text_input_layout's EditText changes
    // to hide or show save_trip_button
    private final TextWatcher nameChangedListener = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {}

        // called when the text in name_text_input_layout changes
        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            updateSaveTripButton();
        }

        @Override
        public void afterTextChanged(Editable s) { }
    };

    // shows save_trip_button only if the name is not empty
    private void updateSaveTripButton() {
        String input =
                nameTextInputLayout.getEditText().getText().toString();

        // if there is a name for the trip, show the save_trip_button
        if (input.trim().length() != 0)
            save_trip_button.setEnabled(true);
        else
            save_trip_button.setEnabled(false);
    }

    // responds to event generated when user saves a contact
    private final View.OnClickListener saveTripButtonClicked =
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // TODO: do we really need to hide the virtual keyboard?
                    //View focused = getCurrentFocus();
                    //focused.clearFocus();
                    //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    //imm.hideSoftInputFromWindow(focused.getWindowToken(), 0);

                    saveTrip(); // save trip to the database
                }
            };

    // saves contact information to the database
    private void saveTrip() {
        // create ContentValues object containing contact's key-value pairs
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseDescription.Trip.COLUMN_NAME,
                nameTextInputLayout.getEditText().getText().toString());

        // use Activity's ContentResolver to invoke
        // insert on the TripContentProvider


        Uri newContactUri = getContentResolver().insert(DatabaseDescription.Trip.CONTENT_URI, contentValues);

        if (newContactUri != null) {
            // the trip is saved, go back to the trip list screen
            setResult(RESULT_OK);
            finish();
        }
        else {
            Snackbar.make(relativeLayout,
                    R.string.trip_not_created, Snackbar.LENGTH_LONG).show();
        }

    }
}




