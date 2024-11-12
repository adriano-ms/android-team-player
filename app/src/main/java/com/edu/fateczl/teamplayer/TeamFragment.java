package com.edu.fateczl.teamplayer;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.edu.fateczl.teamplayer.controller.IController;
import com.edu.fateczl.teamplayer.controller.exceptions.NotFoundException;
import com.edu.fateczl.teamplayer.controller.TeamController;
import com.edu.fateczl.teamplayer.model.Team;
import com.edu.fateczl.teamplayer.persistence.TeamDao;

import java.util.List;

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

    private IController<Team> controller;

    public TeamFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_team, container, false);
        TeamDao teamDao = new TeamDao(view.getContext());
        controller = new TeamController(teamDao, teamDao);
        initializeViews();
        btListTeam.setMovementMethod(new ScrollingMovementMethod());
        btInsertTeam.setOnClickListener(b -> insertAction());
        btUpdateTeam.setOnClickListener(b -> updateAction());
        btDeleteTeam.setOnClickListener(b -> deleteAction());
        btFindTeam.setOnClickListener(b -> findAction());
        btListTeam.setOnClickListener(b -> listAction());
        return view;
    }

    private void insertAction(){
        try {
            controller.insert(screenToEntity());
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void updateAction(){
        try {
            controller.update(screenToEntity());
        } catch (Exception e){
            showErrorMessage(e.getMessage());
        }
    }

    private void deleteAction(){
        try {
            Team t = new Team();
            t.setCode(Integer.parseInt(etCodeTeam.getText().toString()));
            controller.delete(t);
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void findAction(){
        try {
            int code = Integer.parseInt(etCodeTeam.getText().toString());
            Team t = new Team();
            t.setCode(code);
            entityToScreen(controller.findOne(t));
        } catch (NotFoundException nfe){
            showErrorMessage(getString(nfe.getMessageStringCode()));
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void listAction(){
        try {
            List<Team> list = controller.listAll();
            StringBuffer sb = new StringBuffer();
            list.forEach(t -> sb.append(t).append("\n"));
            tvListTeam.setText(sb.toString());
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private Team screenToEntity(){
        Team team = new Team();
        team.setCode(Integer.parseInt(etCodeTeam.getText().toString()));
        team.setName(etNameTeam.getText().toString());
        team.setCity(etCityTeam.getText().toString());
        etCodeTeam.getText().clear();
        etNameTeam.getText().clear();
        etCityTeam.getText().clear();
        return team;
    }

    private void entityToScreen(Team team){
        etCodeTeam.setText(String.valueOf(team.getCode()));
        etNameTeam.setText(team.getName());
        etCityTeam.setText(team.getCity());
    }

    private void showErrorMessage(String message){
        new AlertDialog.Builder(view.getContext()).setTitle(R.string.error_title).setMessage(message).show();
    }


    private void initializeViews() {
        etCodeTeam = view.findViewById(R.id.etCodeTeam);
        etNameTeam = view.findViewById(R.id.etNameTeam);
        etCityTeam = view.findViewById(R.id.etCityTeam);
        tvListTeam = view.findViewById(R.id.tvListTeam);
        btInsertTeam = view.findViewById(R.id.btInsertTeam);
        btUpdateTeam = view.findViewById(R.id.btUpdateTeam);
        btDeleteTeam = view.findViewById(R.id.btDeleteTeam);
        btFindTeam = view.findViewById(R.id.btFindTeam);
        btListTeam = view.findViewById(R.id.btListTeam);
    }

}