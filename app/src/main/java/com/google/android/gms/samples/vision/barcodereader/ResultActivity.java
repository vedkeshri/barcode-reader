package com.google.android.gms.samples.vision.barcodereader;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.google.android.gms.common.api.Status.st;

public class ResultActivity extends AppCompatActivity {
    String JSON_STRING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_result );
        String barcode = getIntent().getStringExtra( "barcode" );
        Button b1 = (Button) findViewById( R.id.search );

        b1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ScanConnect().execute();
            }
        } );


    }

    private class ScanConnect extends AsyncTask<Void, Void, String> {
        String url;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            url = "10.0.0.2/localhost/login.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                URL conurl = new URL( url );
                HttpURLConnection httpURLConnection = (HttpURLConnection) conurl.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader( new InputStreamReader( inputStream ) );
                StringBuilder stringBuilder = new StringBuilder();
                while ((JSON_STRING = bufferedReader.readLine()) != null) {
                    stringBuilder.append( JSON_STRING + "\n" );
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

                return stringBuilder.toString().trim();

            } catch (Exception e) {
                e.printStackTrace();

            }
           return null;
        }

        @Override
        protected void onPostExecute(String string) {
            super.onPostExecute( string );
        }


    }



}
