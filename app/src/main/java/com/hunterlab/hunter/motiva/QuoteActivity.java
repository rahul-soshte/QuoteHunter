package com.hunterlab.hunter.motiva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class QuoteActivity extends AppCompatActivity {
ArrayList<String> quotes=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        Intent intent=getIntent();
        String id=intent.getStringExtra("id");


    }
}
