package com.example.lenovo.reader.fragments.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.example.lenovo.reader.annotations.Layout;

import java.lang.annotation.Annotation;

public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResId(), container);
        unbinder = ButterKnife.bind(view);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (providePresenter() != null) {
            providePresenter().onCreate();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (providePresenter() != null) {
            providePresenter().onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (providePresenter() != null) {
            providePresenter().onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        if (providePresenter() != null) {
            providePresenter().onDestroy();
        }
    }

    private int getLayoutResId() {
        Annotation annotation = getClass().getAnnotation(Layout.class);
        if (annotation == null) {
            throw new IllegalStateException("You must provide a layout annotation for " + getClass().getName());
        } else {
            return ((Layout) annotation).value();
        }
    }

    protected abstract BasePresenter providePresenter();
}
