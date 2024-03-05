package com.example.ttgmp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.FirebaseDatabase;

public class test_cloud extends AppCompatActivity {
    EditText sub, nick;
    CheckBox prac;
    Button add, show;
    TextView res;
    static String subs_CSE="",subs_MECH="",subs_CIVIL="";
    int key_count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_cloud);
        TextView t2 = findViewById(R.id.textView13);


        Intent intent_test = getIntent();
        String[] dept= intent_test.getStringExtra("department").split(",");
        //Toast.makeText(this, ""+dept[1], Toast.LENGTH_SHORT).show();
        FirebaseDatabase reference = FirebaseDatabase.getInstance();
        int i;
        for (i=0;i<dept.length;i++) {
            int finalI = i;
            reference.getReference(""+dept[i]).child(intent_test.getStringExtra("years")).child(intent_test.getStringExtra("semester"))
              //reference.getReference("CSE").child("first year").child("1st sem")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DataSnapshot> task) {
                            if (task.isSuccessful()) {
                                if (task.getResult().exists()) {
                                    if (dept[finalI].equals("CSE")) {
                                        DataSnapshot snapshot = task.getResult();
                                        key_count = (int) snapshot.getChildrenCount();
                                        for (int i = 1; i <= key_count; i++) {
                                            subs_CSE += snapshot.child("" + i).getValue() + "\n";
                                        }
                                    }
                                        if (dept[finalI].equals("MECH")) {
                                            DataSnapshot snapshot1 = task.getResult();
                                            key_count = (int) snapshot1.getChildrenCount();
                                            for (int i = 1; i <= key_count; i++) {
                                                subs_MECH += snapshot1.child("" + i).getValue() + "\n";
                                            }
                                        }
                                        if (dept[finalI].equals("CIVIL")) {
                                              DataSnapshot snapshot2 = task.getResult();
                                              key_count = (int) snapshot2.getChildrenCount();
                                              for (int i = 1; i <= key_count; i++) {
                                                  subs_CIVIL += snapshot2.child("" + i).getValue() + "\n";
                                              }
                                        }
                                        t2.setText(String.format("%s\n\n%s\n\n%s", subs_CSE, subs_CIVIL, subs_MECH));
                                    }
                                }
                            }
                    });
        }

//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("CSE")
//                .document("first year")
//                .collection("1st sem")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()){
//                            for (QueryDocumentSnapshot snapshot : task.getResult()){
//                                t2.setText(String.format("%s", snapshot.getData()));
//                            }
//                        }
//                    }
//                });

    }
}
//        sub = (EditText) findViewById(R.id.subjectname);
//        nick = (EditText) findViewById(R.id.nick);
//        prac = (CheckBox) findViewById(R.id.practical);
//        add = (Button) findViewById(R.id.add_sub);
//        show = (Button) findViewById(R.id.show_sub);
//        res = (TextView) findViewById(R.id.res);
//
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        //add subjects
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean pr = prac.isChecked();
//                Map<String,Object> subject = new HashMap<String,Object>();
//                subject.put("subject",sub.getText().toString());
//                subject.put("short name",nick.getText().toString());
//                subject.put("practical",pr);
//                db.collection("subject_test")
//                        .add(subject)
//                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                            @Override
//                            public void onSuccess(DocumentReference documentReference) {
//                                Toast.makeText(test_cloud.this, "id: "+documentReference.getId(), Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(test_cloud.this, "Error", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            }
//        });
//