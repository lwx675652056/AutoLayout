package com.thinkpad.autolayout.utils;


import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;

import com.thinkpad.autolayout.Constant;

/**
 * @title: ViewUtils.java
 * @package com.vungu.gonghui.utils
 * @description: View的工具类
 * @author win64
 * @date 2016-4-13 下午13:44:47
 */
public class ViewUtils {

	/**
	 * @methods findViewById
	 * @description 根据给定的View对象的泛型封装，减少强转代码
	 * @date 2015-8-12 上午11:27:14
	 * @param layout
	 *            view所在的父布局
	 * @param id
	 *            目标view的id
	 * @return 参数说明
	 */
	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(View layout, int id) {
		return (T) layout.findViewById(id);
	}

	/**
	 * @methods findViewById
	 * @description 寻找Activity的泛型封装，减少强转代码
	 * @date 2015-8-12 上午11:27:14
	 * @param id
	 *            目标view的id
	 * @return 参数说明
	 */
	@SuppressWarnings("unchecked")
	public static <T extends View> T findViewById(Activity mActivity, int id) {
		return (T) mActivity.findViewById(id);
	}

	/**
	 * @methods setViewSize
	 * @description 根据像素单位设置控件的宽高
	 * @date 2015-8-12 上午11:15:32
	 * @param view
	 *            要设置的对象
	 * @param mWidth
	 *            实际的宽
	 * @param mHeight
	 *            实际的高
	 */
	public static void setViewSize(View view, int mWidth, int mHeight) {
		LayoutParams params = view.getLayoutParams();
		params.width = mWidth;
		params.height = mHeight;
		view.setLayoutParams(params);
	}

	/**
	 * @methods setViewSize
	 * @description 根据屏幕的百分比设置控件的宽高
	 * @date 2015-8-12 上午11:20:59
	 * @param view
	 * @param percentWithScreenWidth
	 *            占屏幕的宽的百分比
	 * @param percentWithScreenHeight
	 *            占屏幕的高的百分比 参数说明
	 */
	public static void setViewSize(View view, double percentWithScreenWidth, double percentWithScreenHeight) {
		LayoutParams params = view.getLayoutParams();
		params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
		params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
		view.setLayoutParams(params);
	}

	/**
	 * @methods setViewPositon
	 * @description 设置空间的大小，并且按百分比设定控件的位置,如不需要设置大小传入0，即可
	 * @date 2015-9-6 下午3:32:15
	 * @param view
	 * @param percentWithScreenWidth
	 *            占屏幕的宽的百分比
	 * @param percentWithScreenHeight
	 *            占屏幕的高的百分比
	 * @param leftMarginWithScreenWidth
	 *            距离屏幕左侧占屏幕的宽的百分比
	 * @param topMarginWithScreenHeight
	 *            距离屏幕顶部占屏幕的高的百分比
	 * @param parentType
	 *            参数说明
	 */
	public static void setViewPositonAndSize(View view, double percentWithScreenWidth, double percentWithScreenHeight,
			double leftMarginWithScreenWidth, double topMarginWithScreenHeight, ParentType parentType) {
		String mParentType = parentType.name();
		if (mParentType.equals("LinearLayout")) {
			android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) view.getLayoutParams();
			if (percentWithScreenWidth != 0) {
				params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
			}
			if (percentWithScreenHeight != 0) {
				params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
			}
			params.topMargin = (int) (Constant.screenWidth * topMarginWithScreenHeight);
			params.leftMargin = (int) (Constant.screenHeight * leftMarginWithScreenWidth);
			view.setLayoutParams(params);
		} else if (mParentType.equals("RelativeLayout")) {
			android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
			if (percentWithScreenWidth != 0) {
				params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
			}
			if (percentWithScreenHeight != 0) {
				params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
			}
			params.topMargin = (int) (Constant.screenWidth * topMarginWithScreenHeight);
			params.leftMargin = (int) (Constant.screenHeight * leftMarginWithScreenWidth);
			view.setLayoutParams(params);
		} else if (mParentType.equals("FrameLayout")) {
			android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) view.getLayoutParams();
			if (percentWithScreenWidth != 0) {
				params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
			}
			if (percentWithScreenHeight != 0) {
				params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
			}
			params.topMargin = (int) (Constant.screenWidth * topMarginWithScreenHeight);
			params.leftMargin = (int) (Constant.screenHeight * leftMarginWithScreenWidth);
			view.setLayoutParams(params);
		}
	}

	/**
	 * @methods setViewPositonWithRelativeLayout
	 * @description 设定控件的位置，并按给定尺寸设定控件的大小,如不需要设置大小传入0，即可
	 * @date 2015-9-6 下午1:49:44
	 * @param view
	 * @param percentWithScreenWidth
	 *            占屏幕的宽的百分比
	 * @param percentWithScreenHeight
	 *            占屏幕的高的百分比
	 * @param leftMargin
	 *            距离屏幕左侧的尺寸
	 * @param topMargin
	 *            距离屏幕顶部的尺寸 参数说明
	 */
	public static void setViewPositonAndSize(View view, double percentWithScreenWidth, double percentWithScreenHeight, int leftMargin,
			int topMargin, ParentType parentType) {
		String mParentType = parentType.name();
		if (mParentType.equals("LinearLayout")) {
			android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) view.getLayoutParams();
			if (percentWithScreenWidth != 0) {
				params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
			}
			if (percentWithScreenHeight != 0) {
				params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
			}
			params.topMargin = topMargin;
			params.leftMargin = leftMargin;
			view.setLayoutParams(params);
		} else if (mParentType.equals("RelativeLayout")) {
			android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) view.getLayoutParams();
			if (percentWithScreenWidth != 0) {
				params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
			}
			if (percentWithScreenHeight != 0) {
				params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
			}
			params.topMargin = topMargin;
			params.leftMargin = leftMargin;
			view.setLayoutParams(params);
		} else if (mParentType.equals("FrameLayout")) {
			android.widget.FrameLayout.LayoutParams params = (android.widget.FrameLayout.LayoutParams) view.getLayoutParams();
			if (percentWithScreenWidth != 0) {
				params.width = (int) (Constant.screenWidth * percentWithScreenWidth);
			}
			if (percentWithScreenHeight != 0) {
				params.height = (int) (Constant.screenHeight * percentWithScreenHeight);
			}
			params.topMargin = topMargin;
			params.leftMargin = leftMargin;
			view.setLayoutParams(params);
		}
	}

	public enum ParentType {
		LinearLayout, RelativeLayout, FrameLayout;
	}

	/**
	 * @methods removeSelfFromParent
	 * @description 把自身从父View中移除
	 * @date 2015-8-12 上午11:28:11
	 * @param view
	 *            参数说明
	 */
	public static void removeSelfFromParent(View view) {
		if (view != null) {
			ViewParent parent = view.getParent();
			if (parent != null && parent instanceof ViewGroup) {
				ViewGroup group = (ViewGroup) parent;
				group.removeView(view);
			}
		}
	}

	/**
	 * @methods isTouchInView
	 * @description 判断触点是否落在该View上
	 * @date 2015-8-12 上午11:28:30
	 * @param ev
	 * @param v
	 * @return 参数说明
	 */
	public static boolean isTouchInView(MotionEvent ev, View v) {
		int[] vLoc = new int[2];
		v.getLocationOnScreen(vLoc);
		float motionX = ev.getRawX();
		float motionY = ev.getRawY();
		return motionX >= vLoc[0] && motionX <= (vLoc[0] + v.getWidth()) && motionY >= vLoc[1] && motionY <= (vLoc[1] + v.getHeight());
	}

	/**
	 * @methods requestLayoutParent
	 * @description 请求View树重新布局，用于解决中层View有布局状态而导致上层View状态断裂
	 * @date 2015-8-12 上午11:28:21
	 * @param view
	 * @param isAll
	 *            参数说明
	 */
	public static void requestLayoutParent(View view, boolean isAll) {
		ViewParent parent = view.getParent();
		while (parent != null && parent instanceof View) {
			if (!parent.isLayoutRequested()) {
				parent.requestLayout();
				if (!isAll) {
					break;
				}
			}
			parent = parent.getParent();
		}
	}

}
