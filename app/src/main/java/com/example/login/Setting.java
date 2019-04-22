package com.example.login;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Setting extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Button btnSignOut;
    FirebaseAuth auth;
    FirebaseUser user;
    ProgressDialog PD;

    private DrawerLayout Dl;
    private ActionBarDrawerToggle at;
    private NavigationView nb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        FirebaseApp.initializeApp(this);

        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle("Setting");

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


        btnSignOut = (Button) findViewById(R.id.sign_out_button);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                auth.signOut();
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            startActivity(new Intent(Setting.this, LoginActivity.class));
                        }
                    }
                };
            }
        });

        findViewById(R.id.change_password_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),
                        ForgetAndChangePasswordActivity.class).putExtra("Mode", 1));
            }
        });

        findViewById(R.id.change_email_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),
                        ForgetAndChangePasswordActivity.class).putExtra("Mode", 2));
            }
        });

        findViewById(R.id.delete_user_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),
                        ForgetAndChangePasswordActivity.class).putExtra("Mode", 3));
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
            startActivity(new Intent(Setting.this, LoginActivity.class));
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
                break;

            case R.id.cart:
                Toast.makeText(getApplicationContext(),"Cart",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Setting.this, Cart.class));
                break;


            case R.id.qr:
                Toast.makeText(getApplicationContext(),"Code scanner",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Setting.this, QRcode.class));
                break;

            case R.id.climate:
                Toast.makeText(getApplicationContext(),"Climate",Toast.LENGTH_SHORT).show();
                break;

            case R.id.about:
                Toast.makeText(getApplicationContext(),"About",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Setting.this, About_SGS.class));
                break;

            case R.id.contact:
                Toast.makeText(getApplicationContext(),"Contact",Toast.LENGTH_SHORT).show();
                break;

            case R.id.setting:
                Toast.makeText(getApplicationContext(),"Setting",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Setting.this, Setting.class));
                break;

            case R.id.signOut:
                Toast.makeText(getApplicationContext(),"Sign out",Toast.LENGTH_SHORT).show();

                auth.signOut();
                FirebaseAuth.AuthStateListener authListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user == null) {
                            startActivity(new Intent(Setting.this, LoginActivity.class));
                            finish();
                        }
                    }
                };

                break;
        }
        Dl.closeDrawer(GravityCompat.START);

        return true;
    }
}