package com.edu.fateczl.teamplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @author Adriano M Sanchez
 */
public class TeamFragment extends Fragment {

    private View view;
    private EditText etCodeTeam;
    private EditText etNameTeam;
    private EditText etCityTeam;
    private TextView tvListTeam;
    private Button btInsertTeam;
    private Button btUpdateTeam;
    private Button btDeleteTeam;
    private Button btFindTeam;
    private Button btListTeam;

    public TeamFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        etCodeTeam = view.findViewById(R.id.etCodeTeam);
        etNameTeam = view.findViewById(R.id.etNameTeam);
        etCityTeam = view.findViewById(R.id.etCityTeam);
        tvListTeam = view.findViewById(R.id.tvListTeam);
        btInsertTeam = view.findViewById(R.id.btInsertTeam);
        btUpdateTeam = view.findViewById(R.id.btUpdateTeam);
        btDeleteTeam = view.findViewById(R.id.btDeleteTeam);
        btFindTeam = view.findViewById(R.id.btFindTeam);
        btListTeam = view.findViewById(R.id.btListTeam);
        btListTeam.setMovementMethod(new ScrollingMovementMethod());
        return view;
    }

}