package com.acgg.chickenfeed;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class CreateDietActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private Toolbar mToolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_diet);

        mToolBar = findViewById(R.id.nav_actionBar);
        setSupportActionBar(mToolBar);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView = findViewById(R.id.nav_view);
        NavigationMenuView navigationMenuView = (NavigationMenuView) navigationView.getChildAt(0);
        navigationMenuView.addItemDecoration(new DividerItemDecoration(CreateDietActivity.this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void openMainActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void openAccountActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, AccountActivity.class);
        startActivity(intent);
    }

    public void openNotificationActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void openSavedActivityActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, SavedActivityActivity.class);
        startActivity(intent);
    }

    public void openAboutActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, AboutActivity.class);
        startActivity(intent);
    }

    public void openHelpActivity(MenuItem item) {
        Intent intent = new Intent(CreateDietActivity.this, HelpActivity.class);
        startActivity(intent);
    }

}
