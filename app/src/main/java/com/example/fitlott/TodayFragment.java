package com.example.fitlott;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TodayFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        //buttons declaration
        FloatingActionButton addClient = view.findViewById(R.id.addClientFAB);
        RadioGroup todayGroup = view.findViewById(R.id.todayRadioGroup);

        //if the client radio button is selected in the todayGroup chip group set the FAB to be visible, if not hide the button
        todayGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.clientsRdButton){
                    addClient.setVisibility(View.VISIBLE);
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
}