package org.firstinspires.ftc.teamcode;

/**
 * Created by connorespenshade on 11/9/17.
 */

public abstract class AutonBase {
    protected boolean active = false;

    public abstract void routine();

    public void run() {
        active = true;
        routine();
        done();
    }

    public void done() {

    }

    public void stop() {
        active = false;
    }

    public boolean isActive() {
        return active;
    }
    /*
    public void runAction(Action action) {
        action.start();
        if (isActive() && !action.isFinished()) {
            action.update();
        }
        action.done();
    }
    */
}
