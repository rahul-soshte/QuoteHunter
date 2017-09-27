package com.hunterlab.hunter.motiva;



import android.content.Context;

import android.graphics.Color;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;


public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    Button nextButton;
    DatabaseReference rootRef,demoRef;
    TextView textView;
    TextView hunter;
    static long n;
    String value;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = (ImageView) findViewById(R.id.imageView);
        nextButton = (Button) findViewById(R.id.imageButton2);
        textView = (TextView) findViewById(R.id.url);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        hunter=(TextView)findViewById(R.id.hunter);
        nextButton.setBackgroundColor(Color.WHITE);
        progressBar.setVisibility(View.GONE);
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (isOnline()) {
                    progressBar.setVisibility(View.VISIBLE);
                    hunter.setVisibility(View.GONE);
                    rootRef.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            n = dataSnapshot.getChildrenCount();
                        }

                     @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                    Random r = new Random();
                    long number = (long) (r.nextDouble() * n);
                    demoRef = rootRef.child(Long.toString(number));
                    demoRef.child("img_url").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            value = dataSnapshot.getValue(String.class);

                            Glide.with(getApplicationContext())
                                    .load(value)
                                    .into(imageView);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"Not Connected to Internet!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    }