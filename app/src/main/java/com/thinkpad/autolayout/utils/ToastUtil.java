package com.thinkpad.autolayout.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 
* @title: ToastUtil.java 
* @package com.vungu.meimeng.utils 
* @description: 创建土司，解决重复连续弹出土司 
* @author 
* @date 2015-4-30 上午11:16:16
 */
public class ToastUtil {
	private static Toast toast;
	
	/*
	 * 弹出短时间土司
	 */
	public static void showShortToastMessage(Context mContext,String message) {
		if (toast == null) {
			toast = Toast.makeText(mContext, message, Toast.LENGTH_SHORT);
		} else {
			toast.setText(message);
		}
		toast.show();
	}
	/*
	 * 弹出长时间土司
	 */
	public static void showLongToastMessage(Context context,String message) {
		if (toast == null) {
			toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
		} else {
			toast.setText(message);
		}
		toast.show();
	}
}
