package com.example.moslah_hamza.retrofitdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by Moslah_Hamza on 04/05/2017.
 */

public class PalmaresActivity extends AppCompatActivity {
    TableLayout tab;
    ProgressDialog mProgressDialog;
    private String url = "http://192.168.1.4/";
    List<Palmares> palmares = new ArrayList<>();
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palmares);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Closing drawer on item click
                drawerLayout.closeDrawers();
                Intent i;
                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {

                    case R.id.nav_bank:

                        return true;

                    case R.id.nav_plm:
                        i = new Intent(getApplicationContext(), PalmaresActivity.class);
                        startActivity(i);
                        return true;

                    case R.id.nav_Ind:
                        i = new Intent(getApplicationContext(), IndicesActivity.class);
                        startActivity(i);

                        return true;

                    case R.id.nav_dev:
                        i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);

                        return true;
                    default:
                        return true;
                }

            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer,
                R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

        mProgressDialog = ProgressDialog.show(this, "", "Wait", true);
        tab = (TableLayout) findViewById(R.id.coursTable);

        TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.table_palmares, null);
        TextView tv;

        //Filling in cells
        tv = (TextView) tableRow.findViewById(R.id.nom);
        tv.setText("Nom");
        tv = (TextView) tableRow.findViewById(R.id.haut);
        tv.setText("Haut");
        tv = (TextView) tableRow.findViewById(R.id.bas);
        tv.setText("Bas");
        tv = (TextView) tableRow.findViewById(R.id.dernier);
        tv.setText("Dernier");
        tv = (TextView) tableRow.findViewById(R.id.vol);
        tv.setText("Volume");
        tv = (TextView) tableRow.findViewById(R.id.variation);
        tv.setText("Variation");
        tab.addView(tableRow);

        //showList();

        loadJSON();
    }

    private void loadJSON() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RequestInterface request = retrofit.create(RequestInterface.class);
        Call<JSONResponsePalmares> call = request.getJSONPalmares();
        call.enqueue(new Callback<JSONResponsePalmares>() {
            @Override
            public void onResponse(Call<JSONResponsePalmares> call, Response<JSONResponsePalmares> response) {
                JSONResponsePalmares jsonResponse = response.body();
                palmares.addAll(Arrays.asList(jsonResponse.getPalmares()));
                showList();
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponsePalmares> call, Throwable t) {
                Log.d("Error", t.getMessage());
                mProgressDialog.dismiss();
            }
        });
    }

    private void showList() {
        for (Palmares indice : palmares) {
            TableRow tableRow1 = (TableRow) getLayoutInflater().inflate(R.layout.table_palmares, null);
            TextView tv1;
            //Filling in cells
            tv1 = (TextView) tableRow1.findViewById(R.id.nom);
            tv1.setText(indice.getNom());
            tv1 = (TextView) tableRow1.findViewById(R.id.haut);
            tv1.setText(indice.getHaut());
            tv1 = (TextView) tableRow1.findViewById(R.id.bas);
            tv1.setText(indice.getBas());
            tv1 = (TextView) tableRow1.findViewById(R.id.dernier);
            tv1.setText(indice.getDernier());
            tv1 = (TextView) tableRow1.findViewById(R.id.vol);
            tv1.setText(indice.getVolume());
            tv1 = (TextView) tableRow1.findViewById(R.id.variation);
            tv1.setText(indice.getVariation());

            tab.addView(tableRow1);
        }
    }
}
