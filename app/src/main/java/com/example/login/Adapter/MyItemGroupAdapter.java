package com.example.login.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.login.R;
import com.example.login.model.ItemData;
import com.example.login.model.ItemGroup;

import java.util.List;

public class MyItemGroupAdapter extends RecyclerView.Adapter<MyItemGroupAdapter.MyViewHolder> {

    private Context context;
    private List<ItemGroup>datalist;

    public MyItemGroupAdapter(Context context, List<ItemGroup> datalist) {
        this.context = context;
        this.datalist = datalist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View itemview= LayoutInflater.from(context).inflate(R.layout.layout_group,viewGroup,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, int i) {

        myViewHolder.item_title.setText(datalist.get(i).getHeaderTitle());
        List<ItemData>itemData=datalist.get(i).getListItem();

        MyItemAdapter itemListAdapter= new MyItemAdapter(context,itemData);
        myViewHolder.recycler_view_item_list.setHasFixedSize(true);
        myViewHolder.recycler_view_item_list.setLayoutManager(new
                LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        myViewHolder.recycler_view_item_list.setAdapter(itemListAdapter);

        myViewHolder.recycler_view_item_list.setNestedScrollingEnabled(false);

        myViewHolder.btn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Button More : "+myViewHolder.item_title.getText(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return (datalist != null ? datalist.size() : 0 );
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView item_title;
        RecyclerView recycler_view_item_list;
        Button btn_more;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_title=(TextView)itemView.findViewById(R.id.itemTitle);
            btn_more=(Button)itemView.findViewById(R.id.btnMore);
            recycler_view_item_list=(RecyclerView)itemView.findViewById(R.id.my_recyclr_view_list);
        }
    }
}
