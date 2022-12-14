package com.example.apitesta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private CountryAdapter countryAdapter;
    private Dialog mDialog;
    TextView text;
    private List<Country> countryList= new ArrayList<>();
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.popup);
        mDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView no = mDialog.findViewById(R.id.noTV);
        text = mDialog.findViewById(R.id.textTV);
        no.setOnClickListener(view ->{
            mDialog.dismiss();
        });

        Button bt = findViewById(R.id.submitButtonBT);
        EditText et = findViewById(R.id.searchBarTV);
        listView = findViewById(R.id.listViewLV);
        progressBar = findViewById(R.id.progressBarPB);

        bt.setOnClickListener(view ->{
            String text = et.getText().toString();
            progressBar.setVisibility(View.VISIBLE);
            try {
                getData(text);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

    }

    private void getData(String str) throws IOException, InterruptedException {
        text.setText("No Internet Connection.");
        if(!isConnected()){
            mDialog.show();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }
        if(str.equals("")){
            text.setText("Enter Name.");
            mDialog.show();
            progressBar.setVisibility(View.INVISIBLE);
            return;
        }


        CountryInterface api = RetrofitClient.getRetrofitClient();

        Call<CountryItem> call = api.getDataUsingName(str);

        call.enqueue(new Callback<CountryItem>() {
            @Override
            public void onResponse(Call<CountryItem> call, Response<CountryItem> response) {
                CountryItem list = response.body();
                List<Country> list1 = list.getCountry();

                countryAdapter = new CountryAdapter(MainActivity.this, list1);
                listView.setAdapter(countryAdapter);
            }

            @Override
            public void onFailure(Call<CountryItem> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Something Went Wrong!!",Toast.LENGTH_LONG).show();
            }
        });
        progressBar.setVisibility(View.INVISIBLE);
    }

    public boolean isConnected() {
        try {
            String command = "ping -c 1 google.com";
            return (Runtime.getRuntime().exec(command).waitFor() == 0);
        } catch (Exception e) {
            return false;
        }
    }

}