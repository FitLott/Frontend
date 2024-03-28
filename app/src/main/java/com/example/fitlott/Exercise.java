package com.example.fitlott;

public class Exercise{
    String sets;
    String reps;
    String weights;
    String exercise;
    boolean isPounds;
    public Exercise(String exercise,String reps,String sets, String weights, boolean isPounds){
        this.exercise = exercise;
        this.reps = reps;
        this.sets = sets;
        this.weights = weights;
        this.isPounds = isPounds;

    }
}
