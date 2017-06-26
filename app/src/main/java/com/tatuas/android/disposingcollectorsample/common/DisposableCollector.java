package com.tatuas.android.disposingcollectorsample.common;

import io.reactivex.disposables.Disposable;

interface DisposableCollector {
    void addDisposable(Disposable disposable);
}
