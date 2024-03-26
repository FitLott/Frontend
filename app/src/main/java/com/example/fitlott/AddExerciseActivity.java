package com.example.fitlott;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddExerciseActivity extends Activity {

    private EditText editTextExercise;
    private EditText editTextRepsSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_workout_activity); // Ensure this layout file has the EditTexts and Buttons with correct IDs

        // Initialize your EditText fields and Buttons
        editTextExercise = findViewById(R.id.editTextExercise);
        editTextRepsSet = findViewById(R.id.editTextRepsSet);
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
                finish(); // Close this activity
            }
        });
    }

    private void addExercise() {
        // Extract the input from EditTexts
        String exercise = editTextExercise.getText().toString().trim();
        String reps = editTextRepsSet.getText().toString().trim();

        if (!exercise.isEmpty() && !reps.isEmpty()) {
            // TODO: Add your logic here to handle the new exercise, e.g., add it to a database or a list
            Toast.makeText(this, "Exercise added", Toast.LENGTH_SHORT).show();
            finish(); // Close the activity after adding
        } else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }
}
