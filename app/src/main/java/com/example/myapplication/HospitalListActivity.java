package com.example.myapplication;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HospitalListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_list);

        ListView listView=findViewById(R.id.hospital_list_view);
        ArrayList<Hospital> hospitals = new ArrayList<>();
        hospitals.add(new Hospital("Dr. Sahil Kumar","Sahil Hospital","Heart Doctor"));
        hospitals.add(new Hospital("Dr. Ankit","Ankit Dental","Dentist"));
        hospitals.add(new Hospital("Dr. Kavita Kandal","AIIMS","Dentist"));
        final HospitalArrayAdapter adapter=new HospitalArrayAdapter(this, hospitals);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.i("List",i+" clicked");
                Hospital hospital = adapter.getItem(i);
                AlertDialog.Builder builder=new AlertDialog.Builder(HospitalListActivity.this);
                View v = LayoutInflater.from(HospitalListActivity.this).inflate(R.layout.appointment_layout, null);
                TextView hosName = v.findViewById(R.id.hospname_text_view);
                hosName.setText(hospital.hosName);
                TextView docName = v.findViewById(R.id.docname_text_view);
                docName.setText(hospital.docName);
                builder.setView(v);
                builder.setTitle("Fill the Form");
                builder.setCancelable(false);
                builder.setPositiveButton("Book", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.create().show();
            }
        });
    }
}
