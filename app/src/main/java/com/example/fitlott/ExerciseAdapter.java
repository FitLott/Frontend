package com.example.fitlott;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {

    private List<ExerciseData> exercises; // Assuming Exercise is your data model class

    public ExerciseAdapter(List<ExerciseData> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_item, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseData exercise = exercises.get(position);
        holder.exerciseNameTextView.setText(exercise.exercise);
        holder.exerciseRepsTextView.setText("Reps: "+exercise.reps);
        holder.exerciseSetsTextView.setText("Sets: "+exercise.sets);
        holder.exerciseWeightsTextView.setText("Weight: "+ exercise.weights + exercise.isPounds);
    }

    @Override
    public int getItemCount() {
        return exercises.size();
    }

    class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView exerciseNameTextView;
        TextView exerciseRepsTextView;
        TextView exerciseSetsTextView;
        TextView exerciseWeightsTextView;

        ExerciseViewHolder(View itemView) {
            super(itemView);
            exerciseNameTextView = itemView.findViewById(R.id.exerciseNameTextView);
            exerciseRepsTextView = itemView.findViewById(R.id.exerciseRepsTextView);
            exerciseSetsTextView  = itemView.findViewById(R.id.exerciseSetsTextView);
            exerciseWeightsTextView = itemView.findViewById(R.id.exerciseWeightsTextView);

        }
    }
}