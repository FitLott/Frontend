package com.example.fitlott;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class EditProfileActivity extends AppCompatActivity {

    EditText editName, editPassword;
    Button saveButton, backButton;
    String userID; // The user ID of the profile being edited

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Initialize views
        editName = findViewById(R.id.editName);
        editPassword = findViewById(R.id.editPassword);
        saveButton = findViewById(R.id.editsave);
        backButton = findViewById(R.id.editback);

        // Receive the user ID and other data passed from the previous activity
        Intent intent = getIntent();
        userID = intent.getStringExtra("userID"); // Make sure to pass this correctly from the previous activity
        editName.setText(intent.getStringExtra("name"));
        // Password field is set by the user for updating

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateInput()) {
                    updateUserInfo();
                }
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    private boolean validateInput() {
        // Check if any fields are empty and return false if so
        if (editName.getText().toString().trim().isEmpty() ||

                editPassword.getText().toString().trim().isEmpty()) {
            Toast.makeText(EditProfileActivity.this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void updateUserInfo() {
        // Reference to the Firebase node of the current user
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userID);

        // Preparing the updated data
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", editName.getText().toString().trim());
        updates.put("password", editPassword.getText().toString().trim()); // Consider security implications

        // Performing the update
        reference.updateChildren(updates).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(EditProfileActivity.this, "Profile updated successfully", Toast.LENGTH_SHORT).show();
                finish(); // Go back to the previous activity
            } else {
                Toast.makeText(EditProfileActivity.this, "Failed to update profile", Toast.LENGTH_SHORT).show();
            }
        });
    }
}