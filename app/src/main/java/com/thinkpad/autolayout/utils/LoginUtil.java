package com.thinkpad.autolayout.utils;

import android.content.Context;

import com.thinkpad.autolayout.Constant;


/**
* @description: 登录工具类 
* @author lms
* @date 2016-7-6 下午3:34:21
 */
public class LoginUtil {
	
	//是否已登录
	public static boolean isLogin(Context context){
		String memberId=SharedPreferenceUtil.getString(context, Constant.IS_LOGIN);
		if(TextUtil.stringIsNotNull(memberId))
			return true;
		else
			return false;
	}
	
	//是否已退出
	public static boolean isBack(Context context){
		String memberId=SharedPreferenceUtil.getString(context, Constant.IS_BACK);
		if(TextUtil.stringIsNotNull(memberId))
			return true;
		else
			return false;
	}
}
