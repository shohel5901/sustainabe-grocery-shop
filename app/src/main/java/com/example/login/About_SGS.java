package com.example.login;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class About_SGS extends AppCompatActivity {


    private TextView t1,t2,p1,p12,p2,p21,p3,p31,p4,p41,p5,p51,p6,p61,p7,p71;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about__sgs);

        ActionBar actionbar=getSupportActionBar();
        actionbar.setTitle("About");


        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t12);
        p1=(TextView)findViewById(R.id.p1);
        p12=(TextView)findViewById(R.id.p12);
        p2=(TextView)findViewById(R.id.p2);
        p21=(TextView)findViewById(R.id.p21);
        p3=(TextView)findViewById(R.id.p3);
        p31=(TextView)findViewById(R.id.p31);
        p4=(TextView)findViewById(R.id.p4);
        p41=(TextView)findViewById(R.id.p41);
        p5=(TextView)findViewById(R.id.p5);
        p51=(TextView)findViewById(R.id.p51);
        p6=(TextView)findViewById(R.id.p6);
        p61=(TextView)findViewById(R.id.p61);
        p7=(TextView)findViewById(R.id.p7);
        p71=(TextView)findViewById(R.id.p71);

    }
}
