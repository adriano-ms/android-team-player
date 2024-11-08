package com.edu.fateczl.teamplayer;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.edu.fateczl.teamplayer.model.Team;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adriano M Sanchez
 */
public class PlayerFragment extends Fragment {

    private View view;
    private EditText etIdPlayer;
    private EditText etNamePlayer;
    private EditText etBirthdatePlayer;
    private EditText etHeightPlayer;
    private EditText etWeightPlayer;
    private Spinner spTeamPlayer;
    private TextView tvListPlayer;
    private Button btInsertPlayer;
    private Button btUpdatePlayer;
    private Button btDeletePlayer;
    private Button btFindPlayer;
    private Button btListPlayer;

    public PlayerFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, container, false);
        etIdPlayer = view.findViewById(R.id.etIdPlayer);
        etNamePlayer = view.findViewById(R.id.etNamePlayer);
        etBirthdatePlayer = view.findViewById(R.id.etBirthdatePlayer);
        etHeightPlayer = view.findViewById(R.id.etHeightPlayer);
        etWeightPlayer = view.findViewById(R.id.etWeightPlayer);
        spTeamPlayer = view.findViewById(R.id.spTeamPlayer);
        tvListPlayer = view.findViewById(R.id.tvListPlayer);
        btInsertPlayer = view.findViewById(R.id.btInsertPlayer);
        btUpdatePlayer = view.findViewById(R.id.btUpdatePlayer);
        btDeletePlayer = view.findViewById(R.id.btDeletePlayer);
        btFindPlayer = view.findViewById(R.id.btFindPlayer);
        btListPlayer = view.findViewById(R.id.btListPlayer);
        btListPlayer.setMovementMethod(new ScrollingMovementMethod());
        List<Team> list = new ArrayList<>();
        ArrayAdapter<Team> a = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, list);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTeamPlayer.setAdapter(a);
        return view;
    }
}