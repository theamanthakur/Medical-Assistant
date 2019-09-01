package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class MedicationActivity extends AppCompatActivity {

    public static final String DIASEASE = "DIASEASE";
    public static final String TAG = "MedicationActivity";

    private String mDiasease;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Log.i(TAG, bundle.getString(DIASEASE));
        mDiasease = bundle.getString(DIASEASE);
        TextView textView = findViewById(R.id.medication_diasease_text_view);
        textView.setText(mDiasease);
        Object[] ret = readData("medication.txt");
        final HashMap<String,Set<String>> dict=(HashMap<String,Set<String>>)ret[0];
        final HashSet<String> allMeds=(HashSet<String>)ret[1];
        TextView medsTextView = findViewById(R.id.meds_text_view);
        medsTextView.setText(dict.get(mDiasease).toString());
    }

    public Object[] readData(String filename){
        HashMap<String, Set<String>> dict=new HashMap<>();
        HashSet<String> allSymps=new HashSet<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(getAssets().open(filename)));
            String str;
            while((str=reader.readLine())!=null){
                String words[]=str.split(",");
                String d=words[0].trim().toLowerCase();
                HashSet<String> symps=new HashSet<>();
                for(int i=1;i<words.length;++i)
                    symps.add(words[i].trim().toLowerCase());
                dict.put(d, symps);
                allSymps.addAll(symps);
            }
            reader.close();
        }catch(Exception e){
            Log.e("MainActivity",e.toString());
        }

        return new Object[]{dict, allSymps};
    }
}
