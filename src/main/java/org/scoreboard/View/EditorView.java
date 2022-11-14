package org.scoreboard.View;

import org.scoreboard.Main.Observer;
import org.scoreboard.Main.Team;
import org.scoreboard.ViewModel.ScoreboardViewModel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class EditorView implements Observer {
    private ScoreboardViewModel scoreboardViewModel;
    private Team team;

    @FXML
    private TextField myName;

    @FXML
    private TextField myScore;

    @FXML
    private TextField myDate;

    @FXML
    private Button mySave;

    @FXML
    void onClickSave(ActionEvent event) {
        scoreboardViewModel.updateTeam(team, myName.getText(), myScore.getText());
    }

    public void initialize() {
        scoreboardViewModel.registerObserver(this);
        update();
    }


    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeamName() {
        this.myName.setText(team.getTeamName());
    }

    public void setTeamScore() {
        this.myScore.setText(Integer.toString(team.getScore()));

    }

    public void setTeamDate() {
        team.setDate();
        this.myDate.setText(team.getModifiedDate());
    }

    public void setModelView(ScoreboardViewModel scoreboardView) {
        this.scoreboardViewModel = scoreboardView;
    }

    public void update() {
        setTeamName();
        setTeamScore();
        setTeamDate();
    }

}
