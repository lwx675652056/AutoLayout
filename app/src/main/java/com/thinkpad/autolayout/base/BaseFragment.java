package com.thinkpad.autolayout.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkpad.autolayout.Constant;
import com.thinkpad.autolayout.R;


/**
 * @title: BaseFragment.java
 * @package
 * @description: 基类Fragment
 * @author win64
 * @date 2015-8-11 下午1:25:31
 */
public abstract class BaseFragment extends Fragment {
	public String mTag;
	protected boolean isTemplate = false; // 是否使用模板 , 默认有标题栏
	protected Context mContext;
	protected FragmentActivity mActivity;
	public View mMainView;// 填充的主view
	protected ViewGroup mainBody;// 主体显示
	public View childView = null; // 子类的View
	public View titleView;// 标题栏

	protected TextView title_lefttextview, title_centertextview, title_righttextview;// 标题栏的文字
	protected ImageView title_leftimageview, title_centerimageview, title_rightimageview;// 标题的图标
	protected View title_bottomline;// 标题栏的底部横线

	public BaseFragment(){
		//this表示调用者的子类对象
		mTag=this.getClass().getSimpleName()+System.currentTimeMillis();
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@SuppressLint("InflateParams")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContext = getActivity();
		mActivity = getActivity();
		mMainView = inflater.inflate(R.layout.fragment_view_main, null);
		mainBody = (ViewGroup) mMainView.findViewById(R.id.fragment_main_view);
		titleView = mMainView.findViewById(R.id.title_parentlayout);
		initTitleView();// 初始化标题栏
		setTitleSize();// 设置标题栏的高度
		setTitleEvent();// 设置标题栏的点击事件
		childView = createViewAdded(inflater, savedInstanceState);
		if (childView != null) {
			mainBody.addView(childView);
		}
		if (isTemplate) {// 不使用模板就把标题栏去掉
			titleView.setVisibility(View.GONE);
		}
		initFragmentView();// 初始化添加的布局的对象
		registFragmentEvent();// 布局的view对象注册事件
		return mMainView;

	}
	//在fragment里写自己的findViewById，这样子类在调用时，不用再找根布局，直接this点就可以了
	public View findViewById(int resId){
		return mMainView.findViewById(resId);
	}
	/**
	 * @author win64
	 * @methods initTitleView
	 * @description 初始化title栏
	 * @date 2015-8-11 下午6:45:04 参数说明
	 */
	private void initTitleView() {
		title_lefttextview = (TextView) mMainView.findViewById(R.id.title_lefttextview);
		title_centertextview = (TextView) mMainView.findViewById(R.id.title_centertextview);
		title_righttextview = (TextView) mMainView.findViewById(R.id.title_righttextview);
		title_leftimageview = (ImageView) mMainView.findViewById(R.id.title_leftimageview);
		title_centerimageview = (ImageView) mMainView.findViewById(R.id.title_centerimageview);
		title_rightimageview = (ImageView) mMainView.findViewById(R.id.title_rightimageview);
		// title_bottomline = mMainView.findViewById(R.id.title_bottomline);
	}

	private void setTitleEvent() {
		title_leftimageview.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				mActivity.finish();
			}
		});
	}

	/**
	 * @methods setTitleLeftImageVisible
	 * @description 设置左侧的图片是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleLeftImageVisible(boolean isImageVisible, boolean isTextVisible) {
		if (isImageVisible)
			title_leftimageview.setVisibility(View.VISIBLE);
		else
			title_leftimageview.setVisibility(View.GONE);
		if (isTextVisible)
			title_lefttextview.setVisibility(View.VISIBLE);
		else
			title_lefttextview.setVisibility(View.GONE);
	}

	/**
	 * @methods setTitleCenterImageVisible
	 * @description 设置中间的图片是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleCenterImageVisible(boolean isImageVisible) {
		if (isImageVisible) {
			title_centerimageview.setVisibility(View.VISIBLE);
			title_centertextview.setVisibility(View.GONE);
		} else {
			title_centerimageview.setVisibility(View.GONE);
			title_centertextview.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * @methods setTitleRightImageVisible
	 * @description 设置右侧的图片是否可见
	 * @date 2015-8-7 下午6:10:19 参数说明
	 */
	protected void setTitleRightImageVisible(boolean isImageVisible) {
		if (isImageVisible) {
			title_rightimageview.setVisibility(View.VISIBLE);
			title_righttextview.setVisibility(View.GONE);
		} else {
			title_rightimageview.setVisibility(View.GONE);
			title_righttextview.setVisibility(View.VISIBLE);
		}

	}

	/**
	 * @methods setTitleLeftTextView
	 * @description 设置左侧的文字
	 * @date 2015-6-2 上午10:52:06
	 * @param titleFonts
	 *            参数说明
	 */
	protected void setTitleLeftTextView(String titleFonts) {
		title_lefttextview.setText(titleFonts);
	}

	/**
	 * @methods setTitleCenterTextView
	 * @description 设置中间侧的文字
	 * @date 2015-6-2 上午10:52:06
	 * @param titleFonts
	 *            参数说明
	 */
	protected void setTitleCenterTextView(String titleFonts) {
		title_centertextview.setText(titleFonts);
	}

	/**
	 * @methods setTitleRightTextView
	 * @description 设置中间侧的文字
	 * @date 2015-6-2 上午10:52:06
	 * @param titleFonts
	 *            参数说明
	 */
	protected void setTitleRightTextView(String titleFonts) {
		if (null != title_righttextview) {
			title_righttextview.setVisibility(View.VISIBLE);
		}
		title_righttextview.setText(titleFonts);
	}

	/**
	 * @methods setTitleLeftImageView
	 * @description 设置左侧的图标
	 * @date 2015-6-2 上午10:54:32
	 * @param iamgeID
	 *            参数说明
	 */
	protected void setTitleLeftImageView(int iamgeID) {
		title_leftimageview.setImageResource(iamgeID);
	}

	/**
	 * @methods setTitleCenterImageView
	 * @description 设置中间的图标
	 * @date 2015-6-2 上午10:54:32
	 * @param iamgeID
	 *            参数说明
	 */
	protected void setTitleCenterImageView(int iamgeID) {
		title_centerimageview.setImageResource(iamgeID);
	}

	/**
	 * @methods setTitleRightImageView
	 * @description 设置右侧的图标
	 * @date 2015-6-2 上午10:54:32
	 * @param iamgeID
	 *            参数说明
	 */
	protected void setTitleRightImageView(int iamgeID) {
		title_rightimageview.setVisibility(View.VISIBLE);
		title_rightimageview.setImageResource(iamgeID);
	}

	/**
	 * @methods setTitleBottomLineisVisible
	 * @description 设置title底部的线是否可见
	 * @date 2015-6-2 上午11:09:29 参数说明
	 */
	protected void setTitleBottomLineisVisible(boolean isVisible) {
		if (isVisible) {
			title_bottomline.setVisibility(View.VISIBLE);
		} else {
			title_bottomline.setVisibility(View.INVISIBLE);
		}

	}

	/**
	 * @methods setTitleSize
	 * @description 设置标题的高度
	 * @date 2015-6-2 上午10:36:56 参数说明
	 */
	private void setTitleSize() {
		LayoutParams params = titleView.getLayoutParams();
		params.height = (int) (Constant.screenHeight * 0.06563);
		titleView.setLayoutParams(params);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		releaseFragmentResource();
	}

	/**
	 * @author win64
	 * @methods createViewAdded
	 * @description 子类重写此方法，设置填充的layout布局
	 * @date 2015-8-11 下午5:58:35
	 * @param inflater
	 * @param savedInstanceState
	 * @return 参数说明
	 */
	public abstract View createViewAdded(LayoutInflater inflater, Bundle savedInstanceState);

	/**
	 * @author win64
	 * @methods initFragmentView
	 * @description 初始化fragment中的对象
	 * @date 2015-8-11 下午6:01:29 参数说明
	 */
	public abstract void initFragmentView();

	/**
	 * @author win64
	 * @methods registFragmentEvent
	 * @description 注册点击事件
	 * @date 2015-8-11 下午6:03:30 参数说明
	 */
	public abstract void registFragmentEvent();

	/**
	 * @methods releaseFragmentResource
	 * @description 释放fragment的资源
	 * @date 2015-8-7 上午11:44:10 参数说明
	 */
	public abstract void releaseFragmentResource();

	/**
	 * 启动Activity
	 **/
	public void goToActivity(Class activity) {
		goToActivity(activity, null, null);
	}

	public void goToActivity(Class activity, String key, Bundle bundle) {
		Intent intent = new Intent(mActivity, activity);

		if (key != null && bundle != null) {
			intent.putExtra(key, bundle);
		}

		this.startActivity(intent);
	}
	/**
	 * 启动Service
	 **/
	public void goToService(Class service) {
		goToService(service, null, null);
	}

	public void goToService(Class service, String key, Bundle bundle) {
		Intent intent = new Intent(mActivity, service);

		if (key != null && bundle != null) {
			intent.putExtra(key, bundle);
		}

		mActivity.startService(intent);
	}

	/**启动Service**/


	/**
	 * 快捷操作Fragmnet
	 **/
	public void addFrag(int desId, BaseFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(desId, fragment, fragment.mTag);
		transaction.commit();

//        fragmentManager.findFragmentByTag()
	}

	public void removeFrag(BaseFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.remove(fragment);
		transaction.commit();

	}

	public void replaceFrag(int desId, BaseFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(desId, fragment, fragment.mTag);
		transaction.commit();

//        fragmentManager.findFragmentByTag()
	}

	public void hideFrag(BaseFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.hide(fragment);
		transaction.commit();

	}

	public void showFrag(BaseFragment fragment) {

		FragmentManager fragmentManager = this.getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.show(fragment);
		transaction.commit();

	}
	/**快捷操作Fragmnet**/
}
