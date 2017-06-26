package com.tatuas.android.disposingcollectorsample;

import android.support.annotation.NonNull;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

class Repository {

    @NonNull
    private static <T> Observable<T> filter(Observable<T> upstream) {
        return upstream.subscribeOn(Schedulers.newThread());
    }

    @NonNull
    static Observable<String> getHomeTitle() {
        return filter(Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Home";
            }
        }));
    }

    @NonNull
    static Observable<String> getDashboardTitle() {
        return filter(Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Dashboard";
            }
        }));
    }

    @NonNull
    static Observable<String> getNotificationTitle() {
        return filter(Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(2000);
                return "Notification";
            }
        }));
    }
}
