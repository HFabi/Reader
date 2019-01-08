package com.example.lenovo.reader.activities.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.example.lenovo.reader.activities.di.ActivityComponent;
import com.example.lenovo.reader.activities.di.ActivityModule;

public class BaseActivity extends AppCompatActivity {

    private ActivityComponent activityComponent;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private void setUpDependencyInjection() {
        activityComponent = DaggerActivityComponent.builder()
                .activityModule(new ActivityModule(this))
                .build();
    }

    private ActivityComponent getActivityComponent() {
        return this.activityComponent;
    }
}
