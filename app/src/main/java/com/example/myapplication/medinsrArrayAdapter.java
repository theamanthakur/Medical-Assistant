package com.example.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class medinsrArrayAdapter extends ArrayAdapter<medinsr> {

    private Context mContext;
    public medinsrArrayAdapter( @NonNull Context context,  @NonNull ArrayList<medinsr> objects) {
        super(context, 0, objects);
        mContext = context;
    }

    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if(v==null){
            v = LayoutInflater.from(mContext).inflate(R.layout.hopital_item, null);
        }
        TextView docName = v.findViewById(R.id.doc_name);
        TextView hosName = v.findViewById(R.id.hosp_name);
        TextView spec = v.findViewById(R.id.spec_name);

        medinsr hosp = getItem(position);
        docName.setText(hosp.comName);
        hosName.setText(hosp.phnNo);
        spec.setText(hosp.price);

        ImageView imageView=v.findViewById(R.id.photo_image_view);
        imageView.setImageResource(R.drawable.ic_person_black_24dp);
        return v;

    }
}
