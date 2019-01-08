package com.example.lenovo.reader.fragments.dashboard;

import com.example.lenovo.reader.R;
import com.example.lenovo.reader.annotations.Layout;
import com.example.lenovo.reader.fragments.base.BaseFragment;
import com.example.lenovo.reader.fragments.base.BasePresenter;

import javax.inject.Inject;

@Layout(R.layout.fragment_dashboard)
public class DashboardFragment extends BaseFragment implements DashboardContract.DashboardView {

    @Inject
    DashboardContract.DashboardPresenter dashboardPresenter;

    @Override
    protected BasePresenter providePresenter() {
        return dashboardPresenter;
    }

}
