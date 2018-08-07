package com.azens.countrylist;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.azens.countrylist.adapter.CountryAdapter;
import com.azens.countrylist.api.CountryClient;
import com.azens.countrylist.api.ServiceGenerator;
import com.azens.countrylist.model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Country> countries;
    private SwipeRefreshLayout refreshData;
    private ProgressDialog dialog;
    private Snackbar snackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        View parentView = findViewById(R.id.constraintLayout);
        refreshData = (SwipeRefreshLayout) findViewById(R.id.refresh);
        refreshData.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (isNetworkConnected()){
                    loadJSON();
                    Toast.makeText(MainActivity.this, "Latest data is loaded...", Toast.LENGTH_SHORT).show();
                }else{
                    refreshData.setRefreshing(false);
                    Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_LONG).show();
                }

            }
        });

        loadJSON();

        if (isNetworkConnected()){
            //Progress Dialog for User Interaction
            dialog = new ProgressDialog(MainActivity.this);
            dialog.setTitle("Getting user info");
            dialog.setMessage("Please wait...");
            dialog.show();

            loadJSON();
        }else {
            snackbar.make(parentView, "No internet connection...", Snackbar.LENGTH_INDEFINITE)
                    .setAction("RETRY", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if (isNetworkConnected()){
                                loadJSON();
                            }
                        }
                    }).show();
        }
    }

    private void loadJSON() {
        CountryClient client = ServiceGenerator.createService(CountryClient.class);
        Call<List<Country>> call = client.getCountries();

        call.enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                List<Country> countries = response.body();
                refreshData.setRefreshing(false);
                recyclerView.setAdapter(new CountryAdapter(MainActivity.this,countries));
                dialog.hide();
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean isNetworkConnected () {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
}
