package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.allyants.chipview.ChipView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SymptomsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        Object[] ret = readData("data.txt");
        final HashMap<String, Set<String>> dict=(HashMap<String,Set<String>>)ret[0];
        final HashSet<String> allSymps=(HashSet<String>)ret[1];

        final ChipView cvTag = (ChipView)findViewById(R.id.symptoms_chip_view);
        final CustomChipAdapter chipAdapter = new CustomChipAdapter(new ArrayList<Object>(allSymps));
        cvTag.setAdapter(chipAdapter);

        Button button = findViewById(R.id.go_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<String> symps = new ArrayList<>(chipAdapter.getSelected());
                Log.i("Main Activity",symps.toString());
                Object[] ret = getDiasease(dict, allSymps, symps);
                Log.i("Main Activity","diasease:"+ Arrays.toString(ret));
                Intent intent = new Intent(SymptomsActivity.this, ShowDiaseaseActivity.class);
                intent.putExtra(ShowDiaseaseActivity.DIASEASE, ret[0].toString());
                intent.putExtra(ShowDiaseaseActivity.PROB, ret[1].toString());
                startActivity(intent);
            }
        });
    }

    public Object[] getDiasease(HashMap<String,Set<String>> dict, HashSet<String> allSymps, List<String> list){
        float ratio = 0f;
        float prob = 0f;
        String res="";
        for(String key:dict.keySet()){
            HashSet<String> symps = (HashSet<String>)dict.get(key);
            int sympLen = symps.size();
            symps.retainAll(list);
            float temp = symps.size()*1f/allSymps.size();
            if(temp>ratio){
                ratio = temp;
                prob = symps.size()*1f/sympLen;
                res = key;
            }
        }
        return new Object[]{res, prob};
    }
    public Object[] readData(String filename){
        HashMap<String,Set<String>> dict=new HashMap<>();
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
