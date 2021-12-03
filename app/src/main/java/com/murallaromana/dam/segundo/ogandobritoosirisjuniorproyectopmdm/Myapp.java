package com.murallaromana.dam.segundo.ogandobritoosirisjuniorproyectopmdm;

import android.app.Application;
import android.os.SystemClock;
//https://stackoverflow.com/questions/66980512/error-message-android-gradle-plugin-requires-java-11-to-run-you-are-currently
public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(8000);
    }
}
