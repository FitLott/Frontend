package com.example.fitlott;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
public class DiscoverFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_discover, container, false);

        //open activity_exercise
        view.findViewById(R.id.exerciseCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ExerciseActivity.class);
                startActivity(intent);
            }
        });

        //open activity_personal_trainers
        view.findViewById(R.id.ptCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), PersonalTrainersActivity.class);
                startActivity(intent);
            }
        });

        //open activity_class
        view.findViewById(R.id.classCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), ClassActivity.class);
                startActivity(intent);
            }
        });
        //open activity_nutrition
        view.findViewById(R.id.nutritionCard).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), NutritionActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}