package com.example.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class ShowDiaseaseActivity extends AppCompatActivity {

    public static final String DIASEASE ="DIASEASE";
    public static final String PROB = "PROB";
    public static final String TAG = "ShowDiaseaseActivity";

    private String mDiasease;
    private float mProb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_diasease);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Log.i(TAG, bundle.getString(DIASEASE)+":"+bundle.getString(PROB));
        mDiasease = bundle.getString(DIASEASE);
        mProb = Float.parseFloat(bundle.getString(PROB));
        TextView diaseaseTextView = findViewById(R.id.diasease_text_view);
        TextView probTextView = findViewById(R.id.prob_text_view);
        diaseaseTextView.setText(bundle.getString(DIASEASE));
        probTextView.setText(String.format("%.3f",mProb));
    }

    public void onButtonClicked(View view){
        switch (view.getId()){
            case R.id.medication_button:
                Intent intent = new Intent(ShowDiaseaseActivity.this, MedicationActivity.class);
                intent.putExtra(MedicationActivity.DIASEASE, mDiasease);
                startActivity(intent);
                break;
            case R.id.hospital_button:
                break;
            case R.id.diet_button:
                break;
            case R.id.yoga_button:
        }
    }
}
