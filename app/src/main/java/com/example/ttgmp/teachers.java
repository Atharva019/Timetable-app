package com.example.ttgmp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class teachers extends AppCompatActivity implements View.OnClickListener {
    Button next_t,prev_t,add_teach,delete;
    EditText teach_num,teach_name;
    int count;
    Intent intent_t;
    static String s ="sss";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teachers);

//        SharedPreferences sharedPreferences = getSharedPreferences(subjects.myPreff, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.commit();

        teach_num = findViewById(R.id.teach_num);
        teach_name = findViewById(R.id.teacher_name);
        add_teach = findViewById(R.id.add_teacher);
        add_teach.setOnClickListener(this);
        //next button
        next_t = findViewById(R.id.next_t);
        next_t.setOnClickListener(this);
        //previous button
        prev_t = findViewById(R.id.prev_t);
        prev_t.setOnClickListener(this);
        //delete teacher button
//        delete = findViewById(R.id.delete);
//        delete.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        //next button
        if (v == next_t){
            intent_t = new Intent(getBaseContext(),assignSubTech.class);
            startActivity(intent_t);
            //sliding effect right to lefr
            overridePendingTransition(R.anim.slide_in_right,
                    R.anim.slide_out_left);
        }
        //previous button
        if (v == prev_t){
            intent_t = new Intent(getBaseContext(),subjects.class);
            startActivity(intent_t);
            SharedPreferences sharedPreferences = getSharedPreferences(subjects.myPreff, Context.MODE_PRIVATE);
            //sliding effect left to right
            overridePendingTransition(R.anim.slide_in_left,
                    R.anim.slide_out_right);
        }
        //add teacher button
        if (v == add_teach){
            if (!teach_name.getText().toString().equals("")) {
                if (count != Integer.parseInt(teach_num.getText().toString())) {
                    //adding teacher to cloud firebase
                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                    Map<String, String> data = new HashMap<>();
                    data.put("name", teach_name.getText().toString().trim());
                    db.collection("staff")
                            .add(data)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    teach_name.setText("");
                                    Toast.makeText(teachers.this, "Teacher added successfully\ncount:" + (1 + count), Toast.LENGTH_SHORT).show();
                                    count++;
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(teachers.this, "Please check your network", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    Toast.makeText(teachers.this, "You reached teacher limit", Toast.LENGTH_SHORT).show();
                }
            }else {
                teach_name.setError("please fill out this field");
                teach_name.requestFocus();
            }
        }
//        if (v == delete){
//            FirebaseFirestore db = FirebaseFirestore.getInstance();
//           // CollectionReference collection = db.collection("staff");
//           // deleteCollection(collection,5);
//            db.collection("staff")
//                    .document()
//                    .delete()
//                    .addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//                            Toast.makeText(teachers.this, "Staff record deleted successfully", Toast.LENGTH_SHORT).show();
//                        }
//                    });
//
//        }
    }

    /**
     * Delete a collection in batches to avoid out-of-memory errors. Batch size may be tuned based on
     * document size (atmost 1MB) and application requirements.
     */
//    void deleteCollection(CollectionReference collection, int batchSize) {
//        try {
//            // retrieve a small batch of documents to avoid out-of-memory errors
//            ApiFuture<QuerySnapshot> future = collection.limit(batchSize).get();
//            int deleted = 0;
//            // future.get() blocks on document retrieval
//            List<DocumentSnapshot> documents = future.get().getDocuments();
//            for (DocumentSnapshot document : documents) {
//                document.getReference().delete();
//                ++deleted;
//            }
//            if (deleted >= batchSize) {
//                // retrieve and delete another batch
//                deleteCollection(collection, batchSize);
//            }
//        } catch (Exception e) {
//            System.err.println("Error deleting collection : " + e.getMessage());
//        }
//    }
}