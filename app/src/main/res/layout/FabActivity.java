package com.example.fitlott;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class FabActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set the content view to the layout file you've created
        setContentView(R.layout.activity_calender);

        // Find the FloatingActionButton by its ID
        FloatingActionButton fab = findViewById(R.id.fab_add_exercise);

        // Set an OnClickListener on the FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent to start AddExerciseActivity
                Intent intent = new Intent(FabActivity.this, AddExerciseActivity.class);

                // Start the activity
                startActivity(intent);
            }
        });
    }
}
