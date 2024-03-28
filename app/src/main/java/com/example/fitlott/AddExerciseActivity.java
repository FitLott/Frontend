package com.example.fitlott;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddExerciseActivity extends AppCompatActivity {
    private EditText exerciseText;
    private EditText setsText;
    private EditText weightText;
    private EditText repsText;
    private String date;
    private String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_workout_activity);
        date = getIntent().getStringExtra("date");

        //editTexts
        exerciseText = findViewById(R.id.editTextExercise);
        setsText = findViewById(R.id.editTextSets);
        weightText = findViewById(R.id.editTextWeight);
        repsText = findViewById(R.id.editTextReps);
        //Buttons
        Button buttonAdd = findViewById(R.id.buttonAdd);
        Button buttonCancel = findViewById(R.id.buttonCancel);

        // Set the click listener for the Add button
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExercise();
            }
        });

        // Set the click listener for the Cancel button
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addExercise() {
        // Extract the input from EditTexts
        String exercise = exerciseText.getText().toString().trim();
        String reps = repsText.getText().toString().trim();
        String weight = weightText.getText().toString().trim();
        String sets = setsText.getText().toString().trim();

        SharedPreferences sharedPreferences = this.getSharedPreferences("AppPrefs",MODE_PRIVATE);
        String userID = sharedPreferences.getString("UserID", "");
        //add functionality of adding to the database
        if (!exercise.isEmpty() && !reps.isEmpty()) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userID);
            String exId = reference.push().getKey();
            Map<String, Object> ex = new HashMap<>();
            ToggleButton toggleButton = findViewById(R.id.toggleButton);
            ex.put("exercise", exercise);
            ex.put("reps", reps);
            ex.put("weights", weight);
            ex.put("sets", sets);
            ex.put("isPounds", toggleButton.isChecked());
            ex.put("date", date);

            reference.child("excercises").child(exId).setValue(ex)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Data successfully written
                            Toast.makeText(AddExerciseActivity.this, "Exercise added", Toast.LENGTH_SHORT).show();
                            finish(); // Close the activity after adding
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            // Failed to write data
                            Toast.makeText(AddExerciseActivity.this, "Failed to sign up. Please try again.", Toast.LENGTH_SHORT).show();
                            Log.e("Firebase", "Failed to write user data to Firebase", e);
                        }
                    });

        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
