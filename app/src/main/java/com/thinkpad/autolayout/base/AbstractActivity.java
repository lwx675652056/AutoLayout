package com.thinkpad.autolayout.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.thinkpad.autolayout.Constant;
import com.thinkpad.autolayout.R;
import com.thinkpad.autolayout.autolayout.AutoLayoutActivity;


/**
 * @package
 * @description: 基类Activity
 * @date 2016-4-13 下午13:44:47
 */
public abstract class AbstractActivity extends AutoLayoutActivity {

	protected ViewGroup main_view, title_parentlayout;// 内容主题，标题栏
	public View abstractView;// view_main的布局对象
	protected TextView title_lefttextview, title_centertextview, title_righttextview;// 标题栏的文字
	protected ImageView title_leftimageview, title_centerimageview, title_rightimageview;// 标题的图标
	protected View title_bottomline;// 标题栏的底部横线
	// 上下文
	protected Context mContext;
	protected Activity mActivity;
	protected Application mApplication;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setImmersionStatus();
		setContentView(R.layout.activity_view_main);
		initActivityViews();
		setTitleSize();
		setTitleEvent();
//		ActivityManager.getInstance().pushActivity(this);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);// 默认不弹出软键盘
		
	}

	@Override
	protected void onResume() {
		super.onResume();

	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * @methods setImmersionStatus
	 * @description 设置浸入式的状态栏
	 * @date 2015-9-7 上午9:27:50 参数说明
	 */
	private void setImmersionStatus() {
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}
	}

	/**
	 * @methods setTitleVisible
	 * @description 设置title栏的可见性
	 * @date 2015-11-17 下午5:08:26
	 * @param flag
	 *            参数说明
	 */
	protected void setTitleVisible(boolean flag) {
		if (flag)
			title_parentlayout.setVisibility(View.VISIBLE);
		else
			title_parentlayout.setVisibility(View.GONE);

	}
	/**设置标题栏背景颜色**/
	protected void setTitleBackground(int color) {
			title_parentlayout.setBackgroundColor(color);
	}

	private void setTitleEvent() {
		title_leftimageview.setOnClickListener(new OnClickListener() {

			
			@Override
			public void onClick(View v) {
				finish();
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
	 * @description 设置左侧的图片是否可见
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
	 * @description 设置左侧的图片是否可见
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
		title_righttextview.setVisibility(View.VISIBLE);
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
		LayoutParams params = title_parentlayout.getLayoutParams();
		params.height = (int) (Constant.screenHeight * 0.06563);
		title_parentlayout.setLayoutParams(params);
	}

	// 初始化对象
	private void initActivityViews() {
		mContext = this;
		mActivity = this;
		mApplication = getApplication();
		title_parentlayout = (ViewGroup) findViewById(R.id.title_parentlayout);
		main_view = (ViewGroup) findViewById(R.id.activity_main_view);
		title_lefttextview = (TextView) findViewById(R.id.title_lefttextview);
		title_centertextview = (TextView) findViewById(R.id.title_centertextview);
		title_righttextview = (TextView) findViewById(R.id.title_righttextview);
		title_leftimageview = (ImageView) findViewById(R.id.title_leftimageview);
		title_centerimageview = (ImageView) findViewById(R.id.title_centerimageview);
		title_rightimageview = (ImageView) findViewById(R.id.title_rightimageview);
		title_bottomline = findViewById(R.id.title_bottomline);
	}

	@Override
	public void setContentView(int layoutResID) {
		if (layoutResID == R.layout.activity_view_main) {
			abstractView = LayoutInflater.from(this).inflate(layoutResID, null);
			super.setContentView(abstractView);
		} else {
			main_view.removeAllViews();
			View inflate = this.getLayoutInflater().inflate(layoutResID, null);
			inflate.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
			main_view.addView(inflate);
			initViews();// 初始化view对象
			initDatas();// 初始化必要的数据
			registEvent();// 为view对象注册点击事件
		}
	}

	@Override
	public void setContentView(View view) {
		main_view.removeAllViews();
		main_view.addView(view);

	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		main_view.removeAllViews();
		main_view.addView(view, params);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE); 
		imm.hideSoftInputFromWindow(abstractView.getWindowToken(), 0);
		releaseResource();
	}

	/**
	 * @methods initViews
	 * @description 初始化view对象
	 * @date 2015-8-7 上午11:54:49 参数说明
	 */
	public abstract void initViews();

	/**
	 * @methods initDatas
	 * @description 获取必要的初始化数据
	 * @date 2015-8-7 下午1:23:44 参数说明
	 */
	public abstract void initDatas();

	/**
	 * @methods registEvent
	 * @description 注册点击事件
	 * @date 2015-8-7 下午1:22:28 参数说明
	 */
	public abstract void registEvent();

	/**
	 * @methods releaseResource
	 * @description 释放资源
	 * @date 2015-8-7 上午11:44:10 参数说明
	 */
	public abstract void releaseResource();



	/**
	 * 启动Activity
	 **/
	public void goToActivity(Class activity) {
		goToActivity(activity, null, null);
	}

	public void goToActivity(Class activity, String key, Bundle bundle) {
		Intent intent = new Intent(this, activity);

		if (key != null && bundle != null) {
			intent.putExtra(key, bundle);
		}

		this.startActivity(intent);
	}

	/**启动Activity**/


	/**
	 * 启动Service
	 **/
	public void goToService(Class service) {
		goToService(service, null, null);
	}

	public void goToService(Class service, String key, Bundle bundle) {
		Intent intent = new Intent(this, service);

		if (key != null && bundle != null) {
			intent.putExtra(key, bundle);
		}

		this.startService(intent);
	}

	/**启动Service**/


	/**
	 * 快捷操作Fragmnet
	 **/
	public void addFrag(int desId, BaseFragment fragment) {

		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.add(desId, fragment, fragment.mTag);
		transaction.commit();

//        fragmentManager.findFragmentByTag()
	}

	public void removeFrag(BaseFragment fragment) {

		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.remove(fragment);
		transaction.commit();

	}

	public void replaceFrag(int desId, BaseFragment fragment) {

		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(desId, fragment, fragment.mTag);
		transaction.commit();

//        fragmentManager.findFragmentByTag()
	}

	public void hideFrag(BaseFragment fragment) {

		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.hide(fragment);
		transaction.commit();

	}

	public void showFrag(BaseFragment fragment) {

		FragmentManager fragmentManager = this.getSupportFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.show(fragment);
		transaction.commit();

	}
	/**快捷操作Fragmnet**/
}
