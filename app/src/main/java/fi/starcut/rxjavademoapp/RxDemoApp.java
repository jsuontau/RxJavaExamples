package fi.starcut.rxjavademoapp;

import android.app.Application;
import android.util.Log;

import io.reactivex.annotations.NonNull;
import io.reactivex.exceptions.UndeliverableException;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;

/**
 * Created by jsuontaus on 31/08/2017.
 */

public class RxDemoApp extends Application {

    private static final boolean LOG = true;

    private static final String TAG = RxDemoApp.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        hookRxJava2Errors();
    }

    private void hookRxJava2Errors() {
        //Hook Undelivered RxJava2 errors
        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {

            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {
                //Undelivered error can be ignored
                if (throwable instanceof UndeliverableException) {
                    if (LOG) {
                        Log.w(TAG,
                                "[RxJavaPlugins][Undelivered RxJava2 Error]",
                                throwable);
                    }
                //Other errors should Crash the app
                } else {
                    Thread currentThread = Thread.currentThread();
                    currentThread.getUncaughtExceptionHandler().uncaughtException(
                            currentThread,
                            throwable);
                }
            }
        });
    }

}
