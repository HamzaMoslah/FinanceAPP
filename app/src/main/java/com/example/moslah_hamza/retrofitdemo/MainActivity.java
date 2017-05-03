package com.example.moslah_hamza.retrofitdemo;

import android.app.ProgressDialog;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TableLayout tab;
    EditText cur;
    Spinner from, to;
    Button convert;
    ProgressDialog mProgressDialog;
    private String url = "http://192.168.1.4/";
    List<Cours> courses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressDialog = ProgressDialog.show(this, "", "Wait", true);
        cur = (EditText) findViewById(R.id.cur);
        convert = (Button) findViewById(R.id.btConvert);
        from = (Spinner) findViewById(R.id.from);
        to = (Spinner) findViewById(R.id.to);

        List<Currency> currencies1 = new ArrayList<>();
        List<String> currencies = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            currencies1.addAll(Currency.getAvailableCurrencies());
        }
        for (Currency currency : currencies1) {
            currencies.add(currency.getCurrencyCode());
        }

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, currencies);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        from.setAdapter(dataAdapter);
        from.setSelection(currencies.indexOf("EUR"));
        to.setAdapter(dataAdapter);
        to.setSelection(currencies.indexOf("TND"));

        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mount = cur.getText().toString();
                String fromTxt = from.getSelectedItem().toString();
                String toTxt = to.getSelectedItem().toString();
                try {
                    convertCurrency(mount, fromTxt, toTxt);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

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

    private void convertCurrency(final String mount, final String fromTxt, final String toTxt) throws JSONException {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url+"dom-currency-conversion.php",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        Log.d("Result: ",response);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("from",fromTxt);
                params.put("to",toTxt);
                params.put("mount", mount);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

        //Here we will handle the http request to insert user to mysql db
        //Creating a RestAdapter
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        //Creating object for our interface
//        RequestInterface api = retrofit.create(RequestInterface.class);
//
//        //Defining the method insertuser of our interface
//        Call<JSONConverter> call = api.convertcur(fromTxt,toTxt,mount);
//        call.enqueue(new Callback<JSONConverter>() {
//            @Override
//            public void onResponse(Call<JSONConverter> call, Response<JSONConverter> response) {
//                JSONConverter jsonResponse = response.body();
//                Log.d("azert ", jsonResponse.getResult().length + "");
//                Toast.makeText(getApplicationContext(), jsonResponse.getResult()[0].getRes(), Toast.LENGTH_LONG).show();
//                //mProgressDialog.dismiss();
//            }
//
//            @Override
//            public void onFailure(Call<JSONConverter> call, Throwable t) {
//                Log.d("Error", t.getMessage());
//                //mProgressDialog.dismiss();
//            }
//        });
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
                Log.d("Error", "");
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
