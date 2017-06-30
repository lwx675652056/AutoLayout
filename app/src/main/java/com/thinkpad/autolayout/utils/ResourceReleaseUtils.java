package com.thinkpad.autolayout.utils;

import android.graphics.Bitmap;
import android.os.AsyncTask;

/**
 * @title: ResourceReleaseUtils.java
 * @package com.vungu.gonghui.utils
 * @description: 资源释放的工具类
 * @author win64
 * @date 2016-4-13 下午13:44:47
 */
public class ResourceReleaseUtils {

	/**
	 * @methods releaseBitmap
	 * @description 释放掉位图
	 * @date 2015-8-11 下午2:06:26
	 * @param bitmap
	 *            参数说明
	 */
	public static void releaseBitmap(Bitmap bitmap) {
		if (null != bitmap && !bitmap.isRecycled()) {
			bitmap.recycle();
		}
	}

	/**
	 * @methods closeAsynctask
	 * @description AsynTask释放的类
	 * @date 2015-8-11 下午2:05:25
	 * @param asyncTask
	 *            参数说明
	 */
	public static void closeAsynctask(AsyncTask<?, ?, ?> asyncTask) {
		if (null != asyncTask && asyncTask.getStatus() == AsyncTask.Status.RUNNING) {
			asyncTask.cancel(true);
			asyncTask = null;
		}
	}

}
