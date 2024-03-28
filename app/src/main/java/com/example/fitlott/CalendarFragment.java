package com.example.fitlott;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CalendarFragment extends Fragment {

    private String date;
    private SimpleDateFormat format= new SimpleDateFormat("MM/dd/yyyy");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        CalendarView calendarView = view.findViewById(R.id.calendar_view);
        date = format.format(calendarView.getDate());

        RecyclerView l = view.findViewById(R.id.logged_exercise_list);
        l.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.d("NM", "excerciseList: "+l);



        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth);

                date = format.format(calendar.getTime()); // Current date
                Log.d("NM", "onDataChange: "+date);
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("AppPrefs", MODE_PRIVATE);
                String userID = sharedPreferences.getString("UserID", "");
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users").child(userID).child("excercises");






                reference.addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<Exercise> exerciseList = new ArrayList<>();

                        if (snapshot.exists()) {



                            for (DataSnapshot exSnapshot : snapshot.getChildren()) {


                                String sDate = exSnapshot.child("date").getValue(String.class);

                                if(date.equals(sDate)){
                                    String reps = exSnapshot.child("reps").getValue(String.class);
                                    String weights = exSnapshot.child("weights").getValue(String.class);
                                    String sets = exSnapshot.child("sets").getValue(String.class);
                                    Boolean isPounds = exSnapshot.child("isPounds").getValue(Boolean.class);
                                    String exercise = exSnapshot.child("exercise").getValue(String.class);
                                    exerciseList.add(new Exercise(exercise, reps,sets,weights,isPounds));

                                }




                            }

                        }
                        ExerciseAdapter adapter = new ExerciseAdapter(exerciseList);
                        l.setAdapter(adapter);


                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                    }
                });



            }
        });

        view.findViewById(R.id.fab_add_exercise).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), AddExerciseActivity.class);
                intent.putExtra("date",date);
                // Start the activity
                startActivity(intent);
            }
        });

        return view;
    }
}
