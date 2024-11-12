package com.edu.fateczl.teamplayer;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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

import com.edu.fateczl.teamplayer.controller.IController;
import com.edu.fateczl.teamplayer.controller.exceptions.NotFoundException;
import com.edu.fateczl.teamplayer.controller.PlayerController;
import com.edu.fateczl.teamplayer.controller.TeamController;
import com.edu.fateczl.teamplayer.model.Player;
import com.edu.fateczl.teamplayer.model.Team;
import com.edu.fateczl.teamplayer.persistence.PlayerDao;
import com.edu.fateczl.teamplayer.persistence.TeamDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

/**
 * @author Adriano M Sanchez
 */
public class PlayerFragment extends Fragment {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

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

    private IController<Player> controller;

    public PlayerFragment() {
        super();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_player, container, false);
        initializeViews();
        PlayerDao playerDao = new PlayerDao(view.getContext());
        controller = new PlayerController(playerDao, playerDao);
        TeamDao teamDao = new TeamDao(view.getContext());
        TeamController teamController = new TeamController(teamDao, teamDao);
        btInsertPlayer.setOnClickListener(b -> insertAction());
        btUpdatePlayer.setOnClickListener(b -> updateAction());
        btDeletePlayer.setOnClickListener(b -> deleteAction());
        btFindPlayer.setOnClickListener(b -> findAction());
        btListPlayer.setOnClickListener(b -> listAction());
        btListPlayer.setMovementMethod(new ScrollingMovementMethod());
        List<Team> list = null;
        try {
            list = teamController.listAll();
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
        ArrayAdapter<Team> a = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, list);
        a.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTeamPlayer.setAdapter(a);
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
            Player p = new Player();
            p.setId(Integer.parseInt(etIdPlayer.getText().toString()));
            controller.delete(p);
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void findAction(){
        try {
            Player p = new Player();
            p.setId(Integer.parseInt(etIdPlayer.getText().toString()));
            entityToScreen(controller.findOne(p));
        } catch (NotFoundException nfe){
            showErrorMessage(getString(nfe.getMessageStringCode()));
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private void listAction(){
        try {
            List<Player> list = controller.listAll();
            StringBuffer sb = new StringBuffer();
            list.forEach(t -> sb.append(t).append("\n"));
            tvListPlayer.setText(sb.toString());
        } catch (Exception e) {
            showErrorMessage(e.getMessage());
        }
    }

    private Player screenToEntity() {
        Player player = new Player();
        player.setId(Integer.parseInt(etIdPlayer.getText().toString()));
        player.setName(etNamePlayer.getText().toString());
        player.setBirthdate(LocalDate.parse(etBirthdatePlayer.getText().toString(), DATE_TIME_FORMATTER));
        player.setHeight(Float.parseFloat(etHeightPlayer.getText().toString()));
        player.setWeight(Float.parseFloat(etWeightPlayer.getText().toString()));
        player.setTeam((Team) spTeamPlayer.getSelectedItem());
        etIdPlayer.getText().clear();
        etNamePlayer.getText().clear();
        etBirthdatePlayer.getText().clear();
        etHeightPlayer.getText().clear();
        etWeightPlayer.getText().clear();
        return player;
    }

    @SuppressLint("DefaultLocale")
    private void entityToScreen(Player player){
        etIdPlayer.setText(String.valueOf(player.getId()));
        etNamePlayer.setText(player.getName());
        etBirthdatePlayer.setText(DATE_TIME_FORMATTER.format(player.getBirthdate()));
        etHeightPlayer.setText(String.format("%.2f", player.getHeight()));
        etWeightPlayer.setText(String.format("%.2f", player.getWeight()));
        int size = spTeamPlayer.getAdapter().getCount();
        for(int i = 0; i < size; i++)
            if(Objects.equals(((Team) spTeamPlayer.getAdapter().getItem(i)).getCode(), player.getTeam().getCode()))
                spTeamPlayer.setSelection(i, true);
    }

    private void showErrorMessage(String message){
        new AlertDialog.Builder(view.getContext()).setTitle(R.string.error_title).setMessage(message).show();
    }

    private void initializeViews() {
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
    }
}