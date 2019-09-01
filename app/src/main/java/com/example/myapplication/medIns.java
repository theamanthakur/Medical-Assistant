package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class medIns extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_med_ins);
        ListView listView=findViewById(R.id.medins_list_view);
        ArrayList<medinsr> medins = new ArrayList<>();
        medins.add(new medinsr());
        medins.add(new medinsr());
        medins.add(new medinsr());
        medins.add(new medinsr());
        final medinsrArrayAdapter adapter=new medinsrArrayAdapter(this, medins);
        listView.setAdapter(adapter);
    }
}
