package org.scoreboard.View;

import org.scoreboard.App;
import org.scoreboard.Main.Observer;
import org.scoreboard.Main.Team;
import org.scoreboard.ViewModel.ScoreboardViewModel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ScoreboardView implements Observer {
    private ObservableList<Team> teams = FXCollections.observableArrayList();
    ScoreboardViewModel scoreboardViewModel = new ScoreboardViewModel();

    @FXML
    private ListView<Team> myListView;

    public void initialize() {
        scoreboardViewModel.registerObserver(this);
        update();
    }

    @FXML
    void handle(MouseEvent event) {
        Team editTeam = myListView.getSelectionModel().getSelectedItem();
        showEditor(editTeam);
    }

    public void showEditor(Team editTeam) {
        try {
            EditorView editorView = createEditorView(editTeam);
            FXMLLoader fxmlLoader = createFXMLLoader(editorView);
            Stage stage = createStage(fxmlLoader);
            stage.show();
        } catch (Exception e) {
            System.err.println(e);
            System.exit(-1);
        }
    }

    public EditorView createEditorView(Team editTeam) {
        EditorView editorView = new EditorView();
        editorView.setTeam(editTeam);
        editorView.setModelView(scoreboardViewModel);
        return editorView;
    }

    public FXMLLoader createFXMLLoader(EditorView editorView) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("Editor.fxml"));
            fxmlLoader.setController(editorView);
            return fxmlLoader;
        } catch (Exception e) {
            System.err.println(e);
            System.exit(-2);
        }
        return null;
    }

    public Stage createStage(FXMLLoader loader) {
        try {
            Scene scene = new Scene(loader.load(), 380, 200);
            Stage stage = new Stage();
            stage.setTitle("Team Editor");
            stage.setScene(scene);
            return stage;
        } catch (Exception e) {
            System.err.println(e);
            System.exit(-3);
        }
        return null;
    }

    public void update() {
        teams = FXCollections.observableArrayList();
        for (Team newTeam : scoreboardViewModel.getTeams()) {
            teams.add(newTeam);
        }
        myListView.setItems(teams);
        myListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        myListView.setPrefHeight(teams.size() * 24 + 12);
    }
}
