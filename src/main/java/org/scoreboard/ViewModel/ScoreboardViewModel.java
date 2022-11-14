package org.scoreboard.ViewModel;

import org.scoreboard.Main.Observer;
import org.scoreboard.Main.Subject;
import org.scoreboard.Main.Team;
import org.scoreboard.View.ScoreboardView;
import org.scoreboard.View.EditorView;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ScoreboardViewModel implements Subject {
    private ArrayList<Observer> observers;
    private ArrayList<Team> teams;

    public static final int MINTEAMNAME = 5;
    public static final int MAXTEAMNAME = 50;
    public static final int MINTEAMSCORE = 0;
    public static final int MAXTEAMSCORE = 2000;

    public ScoreboardViewModel() {
        observers = new ArrayList<Observer>();
        teams = new ArrayList<Team>();
        setScoreboard();
    }

    public void setScoreboard() {
        teams.add(new Team());
        teams.add(new Team());
        teams.add(new Team());
        teams.add(new Team());
        teams.add(new Team());
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void updateTeam(Team updatedTeam, String teamName, String teamScore) {
        if (checkTeamName(teamName)) {
            updatedTeam.setTeamName(teamName);
            updatedTeam.setIsUpdated(true);
            updatedTeam.setDate();
        }
        if (checkTeamScore(teamScore)) {
            updatedTeam.setScore(Integer.parseInt(teamScore));
            updatedTeam.setIsUpdated(true);
            updatedTeam.setDate();
        }
        notifyObserver();
    }

    public Boolean checkTeamName(String teamName) {
        Pattern pattern = Pattern.compile("[^a-zA-Z0-9 ]", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(teamName);

        if (teamName.length() > MAXTEAMNAME || teamName.length() < MINTEAMNAME)
            return false;

        if (matcher.find())
            return false;

        return true;
    }

    public Boolean checkTeamScore(String teamScore) {
        try {
            int score = Integer.parseInt(teamScore);
            if (score > MAXTEAMSCORE || score < MINTEAMSCORE)
                return false;
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
        return true;
    }

    public void registerObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObserver() {
        Observer editorObserver = null;

        for (Observer observer : observers) {
            System.out.println(observer);
            if ((observer instanceof EditorView) && ((EditorView) observer).getTeam().getIsUpdated()) {
                observer.update();
                editorObserver = observer;
            } else if (observer instanceof ScoreboardView) {
                observer.update();
            }
            if (editorObserver != null) {
                ((EditorView) editorObserver).getTeam().setIsUpdated(false);
            }
        }
    }
}
