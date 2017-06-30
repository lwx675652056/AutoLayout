package com.thinkpad.autolayout.ui.activity;

import android.os.Bundle;
import com.thinkpad.autolayout.R;
import com.thinkpad.autolayout.base.AbstractActivity;
import com.thinkpad.autolayout.ui.fragment.TestFragment;

public class MainActivity extends AbstractActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitleVisible(true);
        setTitleCenterTextView("zhuye");
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initViews() {
        TestFragment fragment = new TestFragment();
        addFrag(R.id.layout,fragment);
    }

    @Override
    public void initDatas() {

    }

    @Override
    public void registEvent() {

    }

    @Override
    public void releaseResource() {

    }
}
