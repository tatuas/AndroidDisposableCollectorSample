package com.tatuas.android.disposingcollectorsample;

import android.app.Application;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import timber.log.Timber;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupTimber();
        setupFinalLandfillSiteOfRxThrowable();
    }

    private void setupTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void setupFinalLandfillSiteOfRxThrowable() {
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                Timber.tag("final landfill site").d(throwable);
            }
        });
    }
}
