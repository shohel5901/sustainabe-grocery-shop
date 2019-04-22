package com.example.login;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference mRef;
    private FirebaseAuth auth;
    RecyclerView recyclerView;

    private ExpandableListView expandableListView;
    private CustomAdpter customAdpter;
    List<String> listheader;
    HashMap<String,List<String>>listdatachild;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle("Home");


       // LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        recyclerView=findViewById(R.id.recye);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

       // recyclerView.setLayoutManager(layoutManager);

        preparelistData();

        expandableListView=(ExpandableListView)findViewById(R.id.expandableid);
        customAdpter=new CustomAdpter(this,listheader,listdatachild);
        expandableListView.setAdapter(customAdpter);







        auth = FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        mRef=firebaseDatabase.getReference("Data");

    }

    private void preparelistData() {

        String[] header=getResources().getStringArray(R.array.header);
        String[] child=getResources().getStringArray(R.array.child);

        listheader=new ArrayList<>();
        listdatachild=new HashMap<>();

        for(int i=0;i<header.length;i++){
            listheader.add(header[i]);
            List<String> childlist=new ArrayList<>();
            childlist.add(child[i]);
            listdatachild.put(listheader.get(i),childlist);
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Model,ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Model, ViewHolder>(
                        Model.class,
                        R.layout.listitem,
                        ViewHolder.class,
                        mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Model model, int position) {
                        viewHolder.setDetail(getApplicationContext(),model.getName(),model.getImage());
                    }
                };

        recyclerView.setAdapter(firebaseRecyclerAdapter);

    }

}
