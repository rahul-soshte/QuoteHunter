package com.example.hunter.motiva;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ThreadLocalRandom;

//https://stackoverflow.com/questions/42186839/how-to-get-a-random-object-from-firebase-database-in-android
public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    Button imageButton;
DatabaseReference rootRef,demoRef;
TextView textView;
static long n;
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (Button) findViewById(R.id.imageButton2);
        textView = (TextView) findViewById(R.id.url);

        //  Random random=new Random();
        //Toast.makeText(this,Integer.toString(random.nextInt(45)),Toast.LENGTH_LONG).show();

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

                //Random random1=new Random();
                //Integer i=random1.nextLong((int)n);
                // long v = ThreadLocalRandom.current().nextLong(n);

                demoRef = rootRef.child(Long.toString(number));
                demoRef.child("img_url").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        value = dataSnapshot.getValue(String.class);

                        //Picasso.with(MainActivity2.this)
                          //      .load(value)
                            //    .into(imageView);
                        Glide.with(MainActivity2.this).load(value).into(imageView);
                      //  Toast.makeText(MainActivity2.this,value,Toast.LENGTH_LONG).show();
                        //textView.setText(value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });

            }
        });
    }

        @Override
        protected void onStart() {
            super.onStart();
        //    https://firebasestorage.googleapis.com/v0/b/moti-a1b1f.appspot.com/o/2.jpg?alt=media&token=c241ecfd-5cbd-40dc-8fe5-0b45d1c9b0c6
         //   String url="https://firebasestorage.googleapis.com/v0/b/moti-a1b1f.appspot.com/o/2.jpg?alt=media&token=c241ecfd-5cbd-40dc-8fe5-0b45d1c9b0c6";

            //Picasso.with(MainActivity2.this)
              //      .load(url)
                //    .into(imageView);

            String url="https://firebasestorage.googleapis.com/v0/b/moti-a1b1f.appspot.com/o/1.jpg?alt=media&token=762ccf25-4fc3-4f16-a611-5061f6c6bb10";
            //Picasso.with(MainActivity2.this)
              //      .load(url)
                //    .into(imageView);
            Glide.with(this).load(url).into(imageView);
//            String url="https://firebasestorage.googleapis.com/v0/b/moti-a1b1f.appspot.com/o/2.jpg?alt=media&token=c241ecfd-5cbd-40dc-8fe5-0b45d1c9b0c6";

            //Toast.makeText(this,value,Toast.LENGTH_LONG).show();
        }
       // Picasso.with(MainActivity2.this)
         //       .load(value)
           //     .into(imageView);

    }



