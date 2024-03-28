package com.example.fitlott;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        //buttons declaration
        FloatingActionButton addClient = view.findViewById(R.id.addClientFAB);
        RadioGroup todayGroup = view.findViewById(R.id.todayRadioGroup);

        RecyclerView clientList = view.findViewById(R.id.clientRecyclerView);
        clientList.setLayoutManager(new LinearLayoutManager(getContext()));

        //if the client radio button is selected in the todayGroup chip group set the FAB to be visible, if not hide the button
        todayGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId == R.id.clientsRdButton){
                    addClient.setVisibility(View.VISIBLE);
                    fetchMembers();
                }
                else{
                    addClient.setVisibility(View.GONE);
                }
            }
        });

        //open addclient when pressing the fab button
        view.findViewById(R.id.addClientFAB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AddClientActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void fetchMembers() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("AppPrefs", MODE_PRIVATE);
        String userID = sharedPreferences.getString("UserID", "");
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userID).child("members");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<ClientData> clientDataList = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot exSnapshot : snapshot.getChildren()) {
                            String reps = exSnapshot.child("reps").getValue(String.class);
                            String weights = exSnapshot.child("weights").getValue(String.class);
                            String sets = exSnapshot.child("sets").getValue(String.class);
                            Boolean isPounds = exSnapshot.child("isPounds").getValue(Boolean.class);
                            String exercise = exSnapshot.child("exercise").getValue(String.class);
                            exerciseList.add(new ExerciseData(exercise, reps,sets,weights, Boolean.TRUE.equals(isPounds)));
                        }
                    }
                }
                ExerciseAdapter adapter = new ExerciseAdapter(exerciseList);
                l.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        }
}