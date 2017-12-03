package com.hunterlab.hunter.motiva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CategoryIndi extends AppCompatActivity {
    ArrayList<String> quotesList=new ArrayList<>();
    RecyclerView recyclerView;
    CategoryIndiAdapter quoteAdapter;
    LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_indi);

        Intent intent=getIntent();
        String id=intent.getStringExtra("id");

        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView=(RecyclerView)findViewById(R.id.recylerview);
        recyclerView.setLayoutManager(linearLayoutManager);
        quoteAdapter = new CategoryIndiAdapter(quotesList);

        recyclerView.setAdapter(quoteAdapter);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getApplicationContext()));
        //   recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        rootRef.child("categories").child(id).child("quotes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot authorsnapshot:dataSnapshot.getChildren())
                {
                    String quote=authorsnapshot.getValue(String.class);
                    quotesList.add(quote);
                }
                quoteAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
    }

