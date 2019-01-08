package com.example.lenovo.reader;

import android.app.Application;
import com.example.lenovo.reader.di.ApplicationComponent;
import com.example.lenovo.reader.di.ApplicationModule;
import org.jetbrains.annotations.NotNull;
import timber.log.Timber;

public class ReaderApplication extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpTimber();
        setUpDependencyInjection();
    }

    private void setUpTimber() {
        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(@NotNull StackTraceElement element) {
                return super.createStackElementTag(element) + ", " +
                        element.getClassName() + ", " +
                        element.getMethodName() + ", " +
                        element.getLineNumber();
            }
        });
    }

    private void setUpDependencyInjection() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                //.modelModule(new ModelModule())
                .build();
    }

    private ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
