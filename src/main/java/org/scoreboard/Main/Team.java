package org.scoreboard.Main;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Team {
    String teamName;
    int score;
    String modifiedDate;
    Boolean isUpdated;

    public Team() {
        this.teamName = "No name provided";
        this.score = 0;
        this.isUpdated = false;
        setDate();
    }

    public Team(String teamName, int score) {
        this.teamName = teamName;
        this.score = score;
        this.isUpdated = false;
        setDate();
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        Date date = new Date();
        this.modifiedDate = formatter.format(date);
    }

    public String getModifiedDate() {
        return modifiedDate;
    }

    public void setIsUpdated(Boolean update) {
        this.isUpdated = update;
    }

    public Boolean getIsUpdated() {
        return isUpdated;
    }

    public String toString() {
        return String.format("%-20s% 20d", teamName, score);
    }
}