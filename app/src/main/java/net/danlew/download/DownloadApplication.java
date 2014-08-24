package net.danlew.download;

import android.app.Application;
import com.crashlytics.android.Crashlytics;

public class DownloadApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Crashlytics.start(this);
    }
}
