package com.example.lenovo.reader.fragments.dashboard;

import com.example.lenovo.reader.activities.di.ActivityComponent;
import dagger.Component;

@Component(modules = DashboardModule.class, dependencies = ActivityComponent.class)
public interface DashboardComponent {
}
