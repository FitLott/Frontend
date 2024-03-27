package com.example.fitlott;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExerciseActivity extends Activity {
    private EditText exerciseText;
    private EditText setsText;
    private EditText weightText;
    private EditText repsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_workout_activity);

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

        //add functionality of adding to the datbase
        if (!exercise.isEmpty() && !reps.isEmpty()) {
            // TODO: Add your logic here to handle the new exercise, e.g., add it to a database or a list
            Toast.makeText(this, "Exercise added", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity after adding
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
