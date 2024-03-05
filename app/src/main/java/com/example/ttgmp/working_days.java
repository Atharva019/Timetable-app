package com.example.ttgmp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class working_days extends AppCompatActivity implements View.OnClickListener {
    Button next,prev,sel_all;
    Intent intent_work;
    CheckBox mon,tue,wed,thu,fri,sat;
    public static String days = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_days);
        next = findViewById(R.id.next_work);
        prev = findViewById(R.id.prev_work);
        next.setOnClickListener(this);
        prev.setOnClickListener(this);
        mon = findViewById(R.id.mon);
        tue = findViewById(R.id.tues);
        wed = findViewById(R.id.wed);
        thu = findViewById(R.id.thu);
        fri = findViewById(R.id.fri);
        sat = findViewById(R.id.sat);
        sel_all = findViewById(R.id.sel_all);
        sel_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mon.setChecked(true);
                tue.setChecked(true);
                wed.setChecked(true);
                thu.setChecked(true);
                sat.setChecked(true);
                fri.setChecked(true);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v == next){
            if (mon.isChecked() || tue.isChecked() || wed.isChecked() || thu.isChecked() || fri.isChecked() || sat.isChecked()) {
                if (mon.isChecked()){
                    days += mon.getText().toString()+",";
                }if (tue.isChecked()){
                    days += tue.getText().toString()+",";
                }if (wed.isChecked()){
                    days += wed.getText().toString()+",";
                }if (thu.isChecked()){
                    days += thu.getText().toString()+",";
                }if (fri.isChecked()){
                    days += fri.getText().toString()+",";
                }if (sat.isChecked()){
                    days += sat.getText().toString()+",";
                }
                intent_work = new Intent(getBaseContext(), roomActivity.class);
                startActivity(intent_work);
                //sliding effect right to lefr
                overridePendingTransition(R.anim.slide_in_right,
                        R.anim.slide_out_left);
            }else {
                Toast.makeText(this, "select atleast one day", Toast.LENGTH_SHORT).show();
            }
        }
        if (v == prev){
            intent_work = new Intent(getBaseContext(),assignSubTech.class);
            startActivity(intent_work);
            //sliding effect left to right
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
    }
}