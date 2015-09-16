package com.andy.keyme;

import android.app.Application;

/**
 * Created by Andy on 9/6/2015.
 */
public class KeyMeApplication extends Application {
    private KeyMeActivity currentActivity;

    protected synchronized KeyMeActivity getActivity() {
        return currentActivity;
    }

    private synchronized void setActivity(KeyMeActivity act) {
        this.currentActivity = act;
    }

    public void onResume(KeyMeActivity activity) {
        setActivity(activity);
    }

    public void onPause(KeyMeActivity activity) {
        setActivity(null);
    }
}
