package com.example.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder {

    View mView;
    private TextView mname,mnutrition,mco2;
    private ImageView mimage;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mView=itemView;
    }

    public void  setDetails(Context ctx, String name, String nutrition, String image, String co2){
        mname=mView.findViewById(R.id.productname);
        mnutrition=mView.findViewById(R.id.nutrationvalue);
         mco2=mView.findViewById(R.id.co2value);
        mimage=mView.findViewById(R.id.imageid);

        mname.setText(name);
        mco2.setText(co2);



        mnutrition.setText(nutrition);
        Picasso.with(ctx).load(image).into(mimage);
    }

    public void setDetail(Context ctx,String name,String image){
       TextView nm=mView.findViewById(R.id.nameH);
       ImageView im=mView.findViewById(R.id.image_view);

        nm.setText(name);
        Picasso.with(ctx).load(image).into(im);

    }
}
