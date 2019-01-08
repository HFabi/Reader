package com.example.lenovo.reader.fragments.settings;

import com.example.lenovo.reader.R;
import com.example.lenovo.reader.annotations.Layout;
import com.example.lenovo.reader.fragments.base.BaseFragment;
import com.example.lenovo.reader.fragments.base.BasePresenter;

import javax.inject.Inject;

@Layout(R.layout.fragment_settings)
public class SettingsFragment extends BaseFragment implements SettingsContract.SettingsView {

    @Inject
    SettingsContract.SettingsPresenter settingsPresenter;

    @Override
    protected BasePresenter providePresenter() {
        return settingsPresenter;
    }
}
