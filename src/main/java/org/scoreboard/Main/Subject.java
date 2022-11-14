package org.scoreboard.Main;

import org.scoreboard.Main.Observer;

public interface Subject {
    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObserver();
}
