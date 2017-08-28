package com.example.hunter.motiva;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
//https://stackoverflow.com/questions/42186839/how-to-get-a-random-object-from-firebase-database-in-android
public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    Button imageButton;
    DatabaseReference rootRef,demoRef;
    TextView textView;
    static long n;
    String value;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (Button) findViewById(R.id.imageButton2);
        textView = (TextView) findViewById(R.id.url);
        progressBar=(ProgressBar)findViewById(R.id.progress);

        Glide.with(getApplicationContext()).load(R.drawable.hunter).into(imageView);

        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        });
    }

    }



