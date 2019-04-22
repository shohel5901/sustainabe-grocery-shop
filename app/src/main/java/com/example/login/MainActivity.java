package com.example.login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.login.Adapter.MyItemGroupAdapter;
import com.example.login.interfase.IFirebaseLoadListener;
import com.example.login.model.ItemData;
import com.example.login.model.ItemGroup;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, IFirebaseLoadListener {

    Button btnSignOut;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    private DrawerLayout Dl;
    private ActionBarDrawerToggle at;
    private NavigationView nb;

    AlertDialog dialog;
    IFirebaseLoadListener iFirebaseLoadListener;
    DatabaseReference myData;
    RecyclerView my_recycler_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        PD = new ProgressDialog(this);
        PD.setMessage("Loading...");
        PD.setCancelable(true);
        PD.setCanceledOnTouchOutside(false);


        Dl=(DrawerLayout)findViewById(R.id.navi);
        at=new ActionBarDrawerToggle(this,Dl,R.string.open,R.string.close);
        Dl.addDrawerListener(at);
        at.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nb=(NavigationView)findViewById(R.id.navigation) ;
        nb.setNavigationItemSelectedListener(this);

        myData= FirebaseDatabase.getInstance().getReference("MyData");
        dialog= new SpotsDialog.Builder().setContext(this).build();
        iFirebaseLoadListener = this;

        my_recycler_view=(RecyclerView)findViewById(R.id.my_recyclr_view);
        my_recycler_view.setHasFixedSize(true);
        my_recycler_view.setLayoutManager(new LinearLayoutManager(this));

        getFirebaseData();




    }

    private void getFirebaseData() {
        dialog.show();
        myData.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                List<ItemGroup> itemGroups=new ArrayList<>();
                for(DataSnapshot groupSnapShot:dataSnapshot.getChildren()){

                    ItemGroup itemGroup=new ItemGroup();
                    itemGroup.setHeaderTitle(groupSnapShot.child("headerTitle").getValue(true).toString());
                    GenericTypeIndicator<ArrayList<ItemData>> genericTypeIndicator=new GenericTypeIndicator<ArrayList<ItemData>>(){};
                    itemGroup.setListItem(groupSnapShot.child("listItem").getValue(genericTypeIndicator));
                    itemGroups.add(itemGroup);
                }
                iFirebaseLoadListener.onFirebaseLoadSuccess(itemGroups);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                iFirebaseLoadListener.onFirebaseLoadFailed(databaseError.getMessage());

            }
        });
    }


    @Override
    public void onBackPressed() {
        if(Dl.isDrawerOpen(GravityCompat.START)){
            Dl.closeDrawer(GravityCompat.START);

        }else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        if (auth.getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        }
        super.onResume();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(at.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem Item) {
        int id=Item.getItemId();

        switch (id){
            case R.id.home:

                Toast.makeText(getApplicationContext(),"Home",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Home.class));
                break;

            case R.id.cart:
                Toast.makeText(getApplicationContext(),"Cart",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Cart.class));
                break;



            case R.id.qr:
                Toast.makeText(getApplicationContext(),"Code scanner",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, QRcode.class));
                break;

            case R.id.climate:
                Toast.makeText(getApplicationContext(),"Climate",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,Climate_Controller.class));
                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, About_SGS.class));
                break;

            case R.id.contact:
                Toast.makeText(getApplicationContext(),"Contact",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Contact_Us.class));
                break;

            case R.id.setting:
                Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, Setting.class));
                break;

            case R.id.signOut:
                Toast.makeText(getApplicationContext(),"Sign out",Toast.LENGTH_SHORT).show();
                auth.signOut();
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        }
                    }
                };
                break;
        }
        Dl.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFirebaseLoadSuccess(List<ItemGroup> itemGroupList) {
        MyItemGroupAdapter adapter= new MyItemGroupAdapter(this,itemGroupList);
        my_recycler_view.setAdapter(adapter);
        dialog.dismiss();
    }

    @Override
    public void onFirebaseLoadFailed(String message) {

        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
        dialog.dismiss();

    }
}