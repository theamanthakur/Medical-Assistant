package com.example.myapplication;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    public Button btnappoin,btnsypm,btnMedins;
    ArrayList<modelAppoinments> arrayListapoin = new ArrayList<>();

    modelAppoinments mm, mm1, mm2, mm3;
Intent intent;
    private String[] urls = new String[]{"https://image.freepik.com/free-photo/tablet-medical-application-stethoscope_1134-454.jpg","https://9q6pu33arq33jokx6qglbp6n-wpengine.netdna-ssl.com/wp-content/uploads/2018/10/data-mining-medical-records-with-machine-learning-5-current-applications.png","https://augray.com/blog/wp-content/uploads/2017/02/Augmented-Reality-in-Healthcare.jpg"
            ,"https://medtechboston.medstro.com/wp-content/uploads/2015/02/EHRdata1-830x553.jpg"
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        recyclerView = findViewById(R.id.rvappoint);
    arrayListapoin = new ArrayList<>();
//        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//        appoinmentAdapter = new AppoinmentAdapter(this, arrayListModelPg, featuresArrayList);
//        recyclerView.setAdapter(appoinmentAdapter);
        btnsypm= (Button) findViewById(R.id.buttonSymp);
        btnappoin= (Button) findViewById(R.id.buttonappoin);
        btnMedins= (Button) findViewById(R.id.buttonMedins);

        btnsypm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,SymptomsActivity.class));
               }
        });
        btnappoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,HospitalListActivity.class));
            }
        });
        btnMedins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,medIns.class));
            }
        });

        init();
    }
    public void init() {

        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlidingImage_Adapter(MainActivity.this,urls));

        CirclePageIndicator indicator = (CirclePageIndicator)
                findViewById(R.id.indicator);

        indicator.setViewPager(mPager);

        final float density = getResources().getDisplayMetrics().density;

//Set circle indicator radius
        indicator.setRadius(5 * density);

        NUM_PAGES = urls.length;

        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);

        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });


    }
}
