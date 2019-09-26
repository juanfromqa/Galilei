package sample;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;

public class ProgressThread extends Thread {

    ProgressBar pb;
    double progress;
    Boolean shouldStop;

    public ProgressThread(ProgressBar pb) {
        this.pb = pb;
        progress = 0.1;
        shouldStop = false;
    }

    @Override
    public void run() {

        while (!shouldStop) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Platform.runLater(() -> {
                pb.setProgress(progress);
            });

            progress = progress + 0.001;

        }
        if(shouldStop) Platform.runLater(() -> {
            pb.setProgress(1);
        });

    }

    public void setShouldStop(Boolean shouldStop){
        this.shouldStop = shouldStop;

    }
}

