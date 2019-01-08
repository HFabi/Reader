package com.example.lenovo.reader.activities.di;

import com.example.lenovo.reader.activities.base.BaseActivity;
import com.example.lenovo.reader.annotations.PerActivity;
import com.example.lenovo.reader.fragments.base.BaseFragment;
import dagger.Module;
import dagger.Provides;

@Module()
public class ActivityModule {

    private BaseActivity baseActivity;

    public ActivityModule(BaseActivity baseActivity) {
        this.baseActivity = baseActivity;
    }

    @Provides @PerActivity
    BaseActivity provideBaseActivity() {
        return baseActivity;
    }

}
