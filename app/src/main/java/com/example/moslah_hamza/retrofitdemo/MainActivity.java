package com.example.moslah_hamza.retrofitdemo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

public class MainActivity extends AppCompatActivity {
    TableLayout tab;
    ProgressDialog mProgressDialog;
    private String url = "http://192.168.1.4/";
    List<Cours> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = ProgressDialog.show(this, "", "Wait", true);
        tab = (TableLayout) findViewById(R.id.coursTable);

        TableRow tableRow = (TableRow) getLayoutInflater().inflate(R.layout.table_row, null);
        TextView tv;

        //Filling in cells
        tv = (TextView) tableRow.findViewById(R.id.monnaie);
        tv.setText("Monnaie");

        tv = (TextView) tableRow.findViewById(R.id.unit);
        tv.setText("Unité");

        tv = (TextView) tableRow.findViewById(R.id.achat);
        tv.setText("Achat");

        tv = (TextView) tableRow.findViewById(R.id.vente);
        tv.setText("Vente");

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
        Call<JSONResponse> call = request.getJSON();
        call.enqueue(new Callback<JSONResponse>() {
            @Override
            public void onResponse(Call<JSONResponse> call, Response<JSONResponse> response) {
                JSONResponse jsonResponse = response.body();
                Log.d("azert ", jsonResponse.getCours().length + "");
                courses.addAll(Arrays.asList(jsonResponse.getCours()));
                showList();
                mProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<JSONResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
                mProgressDialog.dismiss();
            }
        });
    }

    public void showList() {
        Log.d("courses ", "" + courses.size());
        for (Cours cours : courses) {
            TableRow tableRow1 = (TableRow) getLayoutInflater().inflate(R.layout.table_row, null);
            TextView tv1;
            //Filling in cells
            tv1 = (TextView) tableRow1.findViewById(R.id.monnaie);
            tv1.setText(cours.getNom());

            tv1 = (TextView) tableRow1.findViewById(R.id.unit);
            tv1.setText("" + cours.getUnit());

            tv1 = (TextView) tableRow1.findViewById(R.id.achat);
            tv1.setText("" + cours.getSell());

            tv1 = (TextView) tableRow1.findViewById(R.id.vente);
            tv1.setText("" + cours.getBuy());

            tab.addView(tableRow1);
        }
    }
}
