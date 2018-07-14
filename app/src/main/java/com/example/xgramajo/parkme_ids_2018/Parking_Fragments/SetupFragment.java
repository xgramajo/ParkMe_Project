package com.example.xgramajo.parkme_ids_2018.Parking_Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.xgramajo.parkme_ids_2018.R;

public class SetupFragment extends Fragment {

    private Spinner spinnerDur, spinnerMon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_setup, container, false);

        spinnerDur = (Spinner) view.findViewById(R.id.spinner_duracion);
        spinnerMon = (Spinner) view.findViewById(R.id.spinner_montos);

        return view;
    }

}
