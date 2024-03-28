package com.example.fitlott;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.chip.Chip;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
        nameText = findViewById(R.id.editTextAddName);
        emailText = findViewById(R.id.editTextAddEmail);
        //adding members to the db
        addMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add function to add the members credentials to the firebase db
                addMemberToList();
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

    private void addMemberToList() {
        String memberName = nameText.getText().toString().trim();
        String memberEmail = emailText.getText().toString().trim();

        SharedPreferences sharedPreferences = this.getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String userID = sharedPreferences.getString("UserID", "");
        //add functionality of adding to the database
        if (!memberEmail.isEmpty() && !memberEmail.isEmpty()) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userID);
            String memberId = reference.push().getKey();
            Map<String, Object> member = new HashMap<>();
            member.put("MemberName", memberName);
            member.put("MemberEmail", memberEmail);
            reference.child("members").child(memberId).setValue(member)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            // Data successfully written
                            Toast.makeText(AddClientActivity.this, "Client added", Toast.LENGTH_SHORT).show();
                            finish(); // Close the activity after adding

                        }
                    })
                .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Failed to write data
                    Toast.makeText(AddClientActivity.this, "Failed to add client. Please try again.", Toast.LENGTH_SHORT).show();
                    Log.e("Firebase", "Failed to write user data to Firebase", e);
                }
            });
        }
        else {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
        }
    }

}