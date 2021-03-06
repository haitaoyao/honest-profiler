package com.insightfullogic.honest_profiler.javafx.profile;

import com.insightfullogic.honest_profiler.collector.Profile;
import com.insightfullogic.honest_profiler.collector.ProfileListener;
import com.insightfullogic.honest_profiler.javafx.WindowViewModel;
import com.insightfullogic.honest_profiler.log.LogParser;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

import static com.insightfullogic.honest_profiler.javafx.WindowViewModel.Window.Landing;

public class ProfileViewModel {

    private final LogParser parser;
    private final WindowViewModel windows;

    private boolean flatView;

    @FXML
    private StackPane content;

    public ProfileViewModel(LogParser parser, WindowViewModel windows) {
        this.parser = parser;
        this.windows = windows;
        flatView = false;
    }

    public void quit(ActionEvent event) {
        Platform.exit();
    }

    public void flipView(ActionEvent event) {
        Button button = (Button) event.getSource();
        flipButtonText(button);
        flipContent();
    }

    private void flipContent() {
        // StackPane only displays the head of its children list
        ObservableList<Node> children = content.getChildren();
        Node previouslyVisible = children.remove(0);
        children.add(previouslyVisible);
    }

    private void flipButtonText(Button button) {
        flatView = !flatView;
        button.setText(flatView ? "Tree View" : "Flat View");
    }

    public void back(ActionEvent actionEvent) {
        windows.display(Landing);
    }

}
