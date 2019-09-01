package com.example.myapplication;

import com.allyants.chipview.SimpleChipAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomChipAdapter extends SimpleChipAdapter {
    public CustomChipAdapter(ArrayList<Object> search_data) {
        super(search_data);
    }

    public List<String> getSelected(){
        ArrayList<String> selected=new ArrayList<>();
        for(int i=0;i<getCount();++i){
            if(isSelected(i))
                selected.add((String)getItem(i));
        }
        return selected;
    }
}
