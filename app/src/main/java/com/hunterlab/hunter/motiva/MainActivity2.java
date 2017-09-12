package com.hunterlab.hunter.motiva;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
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

//https://stackoverflow.com/questions/42186839/how-to-get-a-random-object-from-firebase-database-in-android
public class MainActivity2 extends AppCompatActivity {
    ImageView imageView;
    Button imageButton;
    DatabaseReference rootRef,demoRef;
    TextView textView;
    TextView hunter;
    static long n;
    String value;
ProgressBar progressBar;
    static int number;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (Button) findViewById(R.id.imageButton2);
        textView = (TextView) findViewById(R.id.url);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        hunter=(TextView)findViewById(R.id.hunter);

 //       Glide.with(getApplicationContext()).load(R.drawable.hunter).into(imageView);
        progressBar.setVisibility(View.GONE);
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();


        SharedPreferences sp = getSharedPreferences("LOL", Activity.MODE_PRIVATE);
        number = sp.getInt("hey", -1);

        imageButton.setOnClickListener(new View.OnClickListener() {
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

                   // Random r = new Random();
                   // if(number==-1)
                    //{
                     //   number=0;
                    //}
                    if(number == n || number > n )
                    {
                     number=0;
                    }
                    else{
                        number++;
                    }

                    demoRef = rootRef.child(Integer.toString(number));
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
@Override
protected void onStop()
{
    super.onStop();
    SharedPreferences sp1 = getSharedPreferences("LOL", Activity.MODE_PRIVATE);
    SharedPreferences.Editor editor = sp1.edit();
    editor.putInt("hey", number);
    editor.commit();

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