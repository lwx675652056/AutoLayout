package com.thinkpad.autolayout.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import com.thinkpad.autolayout.R;
import com.thinkpad.autolayout.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends BaseFragment {
    private View mView;

    @Override
    public View createViewAdded(LayoutInflater inflater, Bundle savedInstanceState) {
        isTemplate = true;
        mView = inflater.inflate(R.layout.fragment_test, null);
        return mView;
    }

    @Override
    public void initFragmentView() {

    }

    @Override
    public void registFragmentEvent() {

    }

    @Override
    public void releaseFragmentResource() {

    }

}
