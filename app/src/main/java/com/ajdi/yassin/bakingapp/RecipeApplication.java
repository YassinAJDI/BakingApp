package com.ajdi.yassin.bakingapp;

import android.app.Application;

import timber.log.Timber;

/**
 * @author Yassin Ajdi
 * @since 12/11/2018.
 */
public class RecipeApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }
    }
}
