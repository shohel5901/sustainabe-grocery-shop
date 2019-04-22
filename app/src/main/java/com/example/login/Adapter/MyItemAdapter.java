package com.example.login.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.interfase.ItemClickListener;
import com.example.login.model.ItemData;
import com.example.login.model.ItemGroup;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyItemAdapter extends RecyclerView.Adapter<MyItemAdapter.MyViewHolder> {

    private Context context;
    private List<ItemData> itemDataList;

    public MyItemAdapter(Context context, List<ItemData> itemDataList) {
        this.context = context;
        this.itemDataList = itemDataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemview= LayoutInflater.from(context).inflate(R.layout.layout_item,viewGroup,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.txt_item_title.setText(itemDataList.get(i).getName());
        Picasso.with(context).load(itemDataList.get(i).getImage()).into(myViewHolder.img_item);

        myViewHolder.setItemClickListner(new ItemClickListener() {
            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(context,""+itemDataList.get(position).getName(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (itemDataList != null ? itemDataList.size() : 0 );
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txt_item_title;
        ImageView img_item;

        ItemClickListener itemClickListner;

        public void setItemClickListner(ItemClickListener itemClickListner) {
            this.itemClickListner = itemClickListner;
        }

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_item_title=(TextView)itemView.findViewById(R.id.tvTitle);
            img_item=(ImageView)itemView.findViewById(R.id.itemImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListner .onItemClickListener(v,getAdapterPosition());
        }
    }
}
