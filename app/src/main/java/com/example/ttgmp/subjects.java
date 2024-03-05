package com.example.ttgmp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


public class subjects extends AppCompatActivity  {
   // CheckBox cse,mech,civil;
    Button next;
    RadioButton radio_year,radio_dept;
    RadioGroup year_group,dept_group;
    public static String department,year,sel_sem ;

    Intent in;
    ToggleButton sem;
    public static final String myPreff = "MyPreff";
    public static final String YEAR = "yearKey";
    public static final String SEM = "semKey";
    public static final String DEPT = "deptKey";
    SharedPreferences sharedPreferences;
//    @Override
//    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//        switch (buttonView.getId()){
//            case R.id.cse:
//                if (isChecked){
//                    department += "CSE,";
//                }
//                break;
//            case R.id.civil:
//                if (isChecked){
//                    department += "CIVIL,";
//                }
//                break;
//            case R.id.mech:
//                if (isChecked){
//                    department += "MECH,";
//                }
//                break;
//        }
//    }
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

//        cse = findViewById(R.id.cse);
//        civil = findViewById(R.id.civil);
//        mech = findViewById(R.id.mech);

//        cse.setOnCheckedChangeListener(this);
//        civil.setOnCheckedChangeListener(this);
//        mech.setOnCheckedChangeListener(this);

        sem = findViewById(R.id.sem_btn);

        year_group = findViewById(R.id.radio_group);
        dept_group = findViewById(R.id.radio_dept);
//next button
           next = findViewById(R.id.next1);
           next.setOnClickListener(v -> {
               int selectedId = year_group.getCheckedRadioButtonId();
               int selectedDept = dept_group.getCheckedRadioButtonId();
                    if (selectedId != -1 && selectedDept != -1) {
                        in = new Intent(getBaseContext(), teachers.class);

                        radio_year = findViewById(selectedId);
                        radio_dept = findViewById(selectedDept);
                            year = radio_year.getText().toString();
                            department = radio_dept.getText().toString();
                            startActivity(in);

                        if (sem.isChecked()) {
                            sel_sem = "2nd sem";
                        } else {
                            sel_sem = "1st sem";
                        }
                        sharedPreferences = getSharedPreferences(myPreff, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString(DEPT, department);
                        editor.putString(YEAR, year);
                        editor.putString(SEM, sem.getText().toString());
                        editor.commit();
                        startActivity(in);

                        //sliding effect
                        overridePendingTransition(R.anim.slide_in_right,
                                R.anim.slide_out_left);
                    }else {
                        if (selectedId == -1) {
                            Toast.makeText(subjects.this, "select year", Toast.LENGTH_SHORT).show();
                        } else if (selectedDept == -1) {
                            Toast.makeText(this, "select department", Toast.LENGTH_SHORT).show();
                        }
                    }
           });



    }
    // when the user pressed back button this function
    // get invoked automatically.
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left,
                R.anim.slide_out_right);
    }
}

