package com.example.lenovo.reader.fragments.dashboard;

import com.example.lenovo.reader.annotations.PerFragment;
import dagger.Module;
import dagger.Provides;

@Module()
public class DashboardModule {

    private DashboardFragment dashboardFragment;
    private DashboardContract.DashboardPresenter dashboardPresenterImpl;

    public DashboardModule(DashboardFragment dashboardFragment, DashboardPresenterImpl dashboardPresenterImpl) {
        this.dashboardFragment = dashboardFragment;
        this.dashboardPresenterImpl = dashboardPresenterImpl;
    }

    @Provides @PerFragment
    DashboardContract.DashboardView provideDashboardView() {
        return dashboardFragment;
    }

    @Provides @PerFragment
    DashboardContract.DashboardPresenter provideDashboardPresenter() {
        return dashboardPresenterImpl;
    }
}
