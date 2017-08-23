package com.example.hunter.motiva;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;
import java.util.concurrent.ExecutionException;

//https://stackoverflow.com/questions/42186839/how-to-get-a-random-object-from-firebase-database-in-android
public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    Button imageButton;
DataSnapshot dataSnapshot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (Button) findViewById(R.id.imageButton2);
        Random random=new Random();
        Toast.makeText(this,Integer.toString(random.nextInt(45)),Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        String url="https://firebasestorage.googleapis.com/v0/b/moti-a1b1f.appspot.com/o/2.jpg?alt=media&token=c241ecfd-5cbd-40dc-8fe5-0b45d1c9b0c6";
        Picasso.with(MainActivity2.this)
                .load(url)
                .into(imageView);

    }

}

