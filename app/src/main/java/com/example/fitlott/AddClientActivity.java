package com.example.fitlott;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.chip.Chip;

public class AddClientActivity extends AppCompatActivity {
    private EditText nameText;
    private EditText emailText;
    private Button addMember;
    private Button cancelMember;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_client);


        addMember = findViewById(R.id.buttonAddMember);
        //adding members to the db
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add function to add the members credentials to the firebase db
                String memberName = nameText.getText().toString().trim();
                String memberEmail = emailText.getText().toString().trim();
            }
        });

        cancelMember = findViewById(R.id.buttonCancelMember);
        //close the activity if user presses cancel
        cancelMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


}