package com.thinkpad.autolayout.autolayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

import com.thinkpad.autolayout.autolayout.utils.AutoLayoutHelper;
import com.thinkpad.autolayout.view.GridViewForScrollView;

/**
 * Created by hupei on 2016/2/29 9:59.
 */
public class AutoGridViewForScroll extends GridViewForScrollView
{
    private AutoLayoutHelper mHelper = new AutoLayoutHelper(this);

    public AutoGridViewForScroll(Context context)
    {
        super(context);
    }

    public AutoGridViewForScroll(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        if (!isInEditMode())
            mHelper.adjustChildren();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new LayoutParams(getContext(), attrs);
    }

    public static class LayoutParams extends GridViewForScrollView.LayoutParams
            implements AutoLayoutHelper.AutoLayoutParams
    {
        private AutoLayoutInfo mAutoLayoutInfo;

        public LayoutParams(Context c, AttributeSet attrs)
        {
            super(c, attrs);
            mAutoLayoutInfo = AutoLayoutHelper.getAutoLayoutInfo(c, attrs);
        }

        @Override
        public AutoLayoutInfo getAutoLayoutInfo()
        {
            return mAutoLayoutInfo;
        }


        public LayoutParams(int width, int height)
        {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source)
        {
            super(source);
        }

        public LayoutParams(MarginLayoutParams source)
        {
            super(source);
        }

    }
}
