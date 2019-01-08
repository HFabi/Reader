package com.example.lenovo.reader.navigation;

import com.example.lenovo.reader.fragments.dashboard.DashboardFragment;
import com.example.lenovo.reader.fragments.dashboard.DashboardModule;
import com.example.lenovo.reader.fragments.dashboard.DashboardPresenterImpl;

public class RouterImpl implements Router {

    @Override
    public void goBack() {

    }

    @Override
    public void goToDashboard() {
        DashboardFragment dashboardFragment = new DashboardFragment();
        DashboardPresenterImpl dashboardPresenterImpl = new DashboardPresenterImpl();
        DaggerDashboardComponent.builder()
                .dashboardModule(new DashboardModule(dashboardFragment, dashboardPresenterImpl))
                .build();

    }

    @Override
    public void goToSettings() {

    }
}
