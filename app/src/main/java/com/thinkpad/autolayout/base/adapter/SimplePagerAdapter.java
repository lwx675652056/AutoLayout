package com.thinkpad.autolayout.base.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public abstract class SimplePagerAdapter<E> extends PagerAdapter {

    private List<E> mEntities;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    
    
    public SimplePagerAdapter(List<E> entities, Context context) {
        mEntities = entities;
        mContext = context;
        mLayoutInflater=LayoutInflater.from(context);
    }
    
    @Override
    public int getCount() {
        return mEntities.size();
    }
    
    public  abstract int setLayouResID();
    
    public abstract  void initView(View itemView,ViewGroup container, int position,E entity);
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
    
        View itemView=mLayoutInflater.inflate(setLayouResID(),container,false);
        initView(itemView,container,position,mEntities.get(position));
        container.addView(itemView);
        return itemView;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
