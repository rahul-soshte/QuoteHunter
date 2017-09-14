package com.hunterlab.hunter.motiva;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
  String tableName = MotDatabaseHelper.tableName;
    public static SQLiteDatabase newDB;
 public static MotDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView = (ImageView) findViewById(R.id.imageView);
        imageButton = (Button) findViewById(R.id.imageButton2);
        textView = (TextView) findViewById(R.id.url);
        progressBar=(ProgressBar)findViewById(R.id.progress);
        hunter=(TextView)findViewById(R.id.hunter);

        progressBar.setVisibility(View.GONE);
        //database reference pointing to root of database
        rootRef = FirebaseDatabase.getInstance().getReference();

        openAndQueryDatabase();
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



                    if(number == n || number > n )
                    {
                     number = 0;
                    }
                    else{
                        number++;
                    }

                    dbHelper.updateLastValue(newDB,number);


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


private void openAndQueryDatabase() {

        try {

            dbHelper = new MotDatabaseHelper(this.getApplicationContext());
            newDB = dbHelper.getWritableDatabase();

            Cursor c = newDB.rawQuery("SELECT * FROM " +
                    tableName , null);

            if(c != null)
            {
                if(c.moveToFirst()) {
                    number = c.getInt(c.getColumnIndex("Last"));
                    c.close();
                }
            }
            else{
                number = -1;
            }

        } catch (SQLiteException e ) {
            Log.e(getClass().getSimpleName(), "Could not create or Open the database");
        }

    }

    private void openAndQueryDatabase2() {
        newDB.beginTransaction();
        try {
            newDB.execSQL("UPDATE " + tableName + " SET Last=" + number);
            newDB.setTransactionSuccessful();
        }finally {
            newDB.endTransaction();
        }
    }

    @Override
    public void onDestroy()
    {
        newDB.close();
        super.onDestroy();


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