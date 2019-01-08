package com.example.lenovo.reader.activities.di;

import com.example.lenovo.reader.di.ApplicationComponent;
import dagger.Component;

@Component(modules = ActivityModule.class, dependencies = ApplicationComponent.class)
public interface ActivityComponent {



}
