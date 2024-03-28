package com.example.fitlott;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ProfileActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        // Retrieve passed user information
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name", "N/A");
            String email = extras.getString("email", "N/A");
            String username = extras.getString("username", "N/A");
            String password = extras.getString("password", "N/A").replaceAll(".", "*");

            // Display the information in TextViews
            ((TextView)view.findViewById(R.id.titleName)).setText(name);
            ((TextView)view.findViewById(R.id.titleUsername)).setText(username);
            ((TextView)view.findViewById(R.id.profileName)).setText(name);
            ((TextView)view.findViewById(R.id.profileEmail)).setText(email);
            ((TextView)view.findViewById(R.id.profileUsername)).setText(username);
            ((TextView)view.findViewById(R.id.profilePassword)).setText(password);
        }

        view.findViewById(R.id.edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPrefs", MODE_PRIVATE);
                String userID = sharedPreferences.getString("UserID", "");

                if (!userID.isEmpty()) {
                    Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                    intent.putExtra("userID", userID); // Pass the userID
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity(), "User ID not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.logout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                // Start the activity
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        fetchUserData();
    }

    private void fetchUserData() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String userID = sharedPreferences.getString("UserID", "");

        if (!userID.isEmpty()) {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userID);
            reference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String name = snapshot.child("name").getValue(String.class);
                        String email = snapshot.child("email").getValue(String.class); // Assuming email is stored and you want to fetch it
                        String username = snapshot.child("username").getValue(String.class); // Adjust according to your database structure
                        String password = snapshot.child("password").getValue(String.class);

                        // Update UI
                        TextView TitleName = getView().findViewById(R.id. titleName);
                        TextView TitleUsername = getView().findViewById(R.id. titleUsername);
                        TextView profileName = getView().findViewById(R.id.profileName);
                        TextView profileEmail = getView().findViewById(R.id.profileEmail);
                        TextView profileUsername = getView().findViewById(R.id.profileUsername);
                        TextView profilePassword = getView().findViewById(R.id.profilePassword);

                        TitleName.setText(name);
                        TitleUsername.setText(username);
                        profileName.setText(name);
                        profileEmail.setText(email);
                        profileUsername.setText(username);
                        profilePassword.setText(password != null ? password.replaceAll(".", "*") : "N/A");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Failed to fetch user data.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(getActivity(), "User ID not found", Toast.LENGTH_SHORT).show();
        }
    }
}