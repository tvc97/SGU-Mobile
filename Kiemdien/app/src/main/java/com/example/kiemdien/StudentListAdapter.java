package com.example.kiemdien;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.ArrayList;

public class StudentListAdapter extends ArrayAdapter<Student> {

    private static final String TAG = "PersonListAdapter";

    private Context mContext;
    private int mResource;

    private static class ViewHolder {
        TextView tvMSSV;
        TextView tvName;
        TextView tvCheckIn;
    }

    public StudentListAdapter(Context context, int resource, ArrayList<Student> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String id = getItem(position).getId();
        String name = getItem(position).getName();
        String checkIn = getItem(position).getCheckIn();

        Student person = new Student(id, name, checkIn);

        final View result;

        ViewHolder holder;


        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder= new ViewHolder();
            holder.tvMSSV = (TextView) convertView.findViewById(R.id.tvMSSV);
            holder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            holder.tvCheckIn = (TextView) convertView.findViewById(R.id.tvCheckIn);

            ((ImageButton) convertView.findViewById(R.id.btnCheckIn)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainActivity)mContext).updateTimeForMSSV(id);
                }
            });

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        holder.tvMSSV.setText(person.getId());
        holder.tvName.setText(person.getName());
        holder.tvCheckIn.setText(person.getCheckIn());



        return convertView;
    }

}