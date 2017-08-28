package de.tum.in.repobruegge.pom17aprilsnow.flightapp.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import de.tum.in.repobruegge.pom17aprilsnow.flightapp.R;

/**
 * Created by Yesika Ramirez on 19.06.17.
 */

public abstract class NavigationItemActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    protected DrawerLayout mDrawerLayout;
    protected NavigationView mNavigationView;
    protected ActionBarDrawerToggle mToggle;
    protected Toolbar mToolbar;


    protected abstract int getContentLayoutId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentLayoutId());
        setUpNavigationView();
    }

    protected void setUpNavigationView() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Intent intent = null;

        if (id == R.id.main) {
            intent = TripListActivity.newIntent(NavigationItemActivity.this);
        } else if (id == R.id.safety_instructions) {
            intent = SafetyInstructionsActivity.newIntent(NavigationItemActivity.this);
        } else if (id == R.id.service_request) {

        } else if (id == R.id.favourite_poi) {

        } else if (id == R.id.passenger_survey) {
            intent = FeedbackActivity.newIntent(NavigationItemActivity.this);
        } else if (id == R.id.settings) {

        }

        if (intent != null) {
            mDrawerLayout.closeDrawers();
            startActivity(intent);
            finish();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
