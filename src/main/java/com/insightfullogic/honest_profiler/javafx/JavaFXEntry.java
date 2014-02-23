package com.insightfullogic.honest_profiler.javafx;

import com.insightfullogic.honest_profiler.collector.LogCollector;
import com.insightfullogic.honest_profiler.javafx.profile.*;
import com.insightfullogic.honest_profiler.log.LogParser;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.picocontainer.DefaultPicoContainer;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.behaviors.Caching;

public class JavaFXEntry extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = createRoot();
        stage.setTitle("Honest Profiler");
        stage.setScene(new Scene(root));
        stage.show();
    }

    static Parent createRoot() {
        MutablePicoContainer pico = registerComponents();
        PicoFXLoader loader = pico.getComponent(PicoFXLoader.class);
        return loader.load("ProfileView.fxml", ProfileViewModel.class);
    }

    private static MutablePicoContainer registerComponents() {
        MutablePicoContainer pico = new DefaultPicoContainer(new Caching())
            .addAdapter(new ProfileListenerProvider())
            .addComponent(LogCollector.class)
            .addComponent(LogParser.class)
            .addComponent(FlatViewModel.class)
            .addComponent(TreeViewModel.class)
            .addComponent(ProfileViewModel.class)
            .addComponent(PicoFXLoader.class);

        return pico.addComponent(pico);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
