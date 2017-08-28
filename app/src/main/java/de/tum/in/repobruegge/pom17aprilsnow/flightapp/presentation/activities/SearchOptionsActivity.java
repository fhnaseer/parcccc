package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.Model.Feedback;
import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SearchOptionsActivity extends NavigationItemActivity {

    private Feedback _feedback = new Feedback();

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
}
