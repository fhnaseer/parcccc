package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

public class SearchResultsActivity extends NavigationItemActivity {

    public static Intent newIntent(Context context) {
        return new Intent(context, SearchResultsActivity.class);
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_search_results;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
