package com.example.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class CustomAdpter extends BaseExpandableListAdapter {

    private Context context;
    List<String> listheader;
    HashMap<String,List<String>> listdatachild;

    public CustomAdpter(Context context, List<String> listheader, HashMap<String, List<String>> listdatachild) {
        this.context = context;
        this.listheader = listheader;
        this.listdatachild = listdatachild;
    }

    @Override
    public int getGroupCount() {
        return listheader.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listdatachild.get(listheader.get(i)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listheader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listdatachild.get(listheader.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerText=(String)getGroup(groupPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
           convertView= inflater.inflate(R.layout.group,null);
        }

        TextView textView=convertView.findViewById(R.id.headerTextid);
        textView.setText(headerText);

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        String childtext=(String)getChild(groupPosition,childPosition);
        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.child,null);
        }

        TextView textView=convertView.findViewById(R.id.childTextid);
        textView.setText(childtext);

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
