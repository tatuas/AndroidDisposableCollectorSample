package com.tatuas.android.disposingcollectorsample.common;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class DisposingComposer<T> implements ObservableTransformer<T, T> {

    private DisposableCollector disposableCollector;

    public DisposingComposer(DisposableCollector disposableCollector) {
        this.disposableCollector = disposableCollector;
    }

    @Override
    public ObservableSource<T> apply(@NonNull Observable<T> upstream) {
        return upstream.doOnSubscribe(new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {
                disposableCollector.addDisposable(disposable);
            }
        });
    }
}
