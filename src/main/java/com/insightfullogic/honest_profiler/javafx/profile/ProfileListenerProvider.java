package com.insightfullogic.honest_profiler.javafx.profile;

import com.insightfullogic.honest_profiler.collector.ProfileListener;
import javafx.application.Platform;
import org.picocontainer.injectors.ProviderAdapter;

public class ProfileListenerProvider extends ProviderAdapter {

    public ProfileListener provide(FlatViewModel flatModel, TreeViewModel treeModel, TraceCountViewModel countModel) {
        return profile -> {
            onFxThread(() -> {
                flatModel.accept(profile);
                treeModel.accept(profile);
                countModel.accept(profile);
            });
        };
    }

    // ViewModel instances can happily update the UI
    // without worrying about threading implications
    private void onFxThread(Runnable block) {
        if (Platform.isFxApplicationThread()) {
            block.run();
        } else {
            Platform.runLater(block);
        }
    }

}
