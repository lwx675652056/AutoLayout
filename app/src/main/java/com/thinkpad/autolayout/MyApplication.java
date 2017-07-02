package com.thinkpad.autolayout;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.thinkpad.autolayout.ui.activity.MainActivity;
import com.thinkpad.autolayout.utils.ScreenUtils;

import org.xutils.x;

import java.util.ArrayList;


/**
 * @title: MyApplication.java
 * @description: 应用程序启动的Application
 * @author win64
 * @date 2016-4-13 下午13:44:47
 */
public class MyApplication extends Application {
	ArrayList<Activity> list = new ArrayList<Activity>();
	public static Context mContextGlobal;

	@Override
	public void onCreate() {
		super.onCreate();
		mContextGlobal = this;
		initParames();
		initXutils();
		initImageLoader(this);
	}

	private void initXutils() {

		x.Ext.init(this);
		x.Ext.setDebug(true);//上线时关闭
	}

	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.discCacheSize(50 * 1024 * 1024)
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.build();
		ImageLoader.getInstance().init(config);
	}
	/**
	 * @methods initParames
	 * @description 必要参数的初始化
	 * @date 2015-8-7 上午11:37:33 参数说明
	 */
	private void initParames() {
		int[] screenSizes = ScreenUtils.getScreenSize(this);
		Constant.screenWidth = screenSizes[0];
		Constant.screenHeight = screenSizes[1];
		Constant.screenStatus = ScreenUtils.getStatusHeight(this);
	}
	public class UnCeHandler implements Thread.UncaughtExceptionHandler {

		private Thread.UncaughtExceptionHandler mDefaultHandler;
		public static final String TAG = "CatchExcep";
		MyApplication application;

		public UnCeHandler(MyApplication application) {
			// 获取系统默认的UncaughtException处理器
			mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
			this.application = application;
		}

		@Override
		public void uncaughtException(Thread thread, Throwable ex) {
			if (!handleException(ex) && mDefaultHandler != null) {
				// 如果用户没有处理则让系统默认的异常处理器来处理
				mDefaultHandler.uncaughtException(thread, ex);
			} else {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					Log.e(TAG, "error : ", e);
				}
				Intent intent = new Intent(application.getApplicationContext(), MainActivity.class);
				PendingIntent restartIntent = PendingIntent.getActivity(application.getApplicationContext(), 0, intent,
						Intent.FLAG_ACTIVITY_NEW_TASK);
				// 退出程序
				AlarmManager mgr = (AlarmManager) application.getSystemService(Context.ALARM_SERVICE);
				mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 1000, restartIntent); // 1秒钟后重启应用
				application.finishActivity();
			}
		}

		/**
		 * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
		 *
		 * @param ex
		 * @return true:如果处理了该异常信息;否则返回false.
		 */
		private boolean handleException(Throwable ex) {
			if (ex == null) {
				return false;
			}
			// 使用Toast来显示异常信息
			new Thread() {
				@Override
				public void run() {
					Looper.prepare();
					Toast.makeText(application.getApplicationContext(), "很抱歉,程序出现异常,即将退出.", Toast.LENGTH_SHORT).show();

					Looper.loop();

				}
			}.start();
			return true;
		}
	}

	/**
	 * Activity关闭时，删除Activity列表中的Activity对象
	 */
	public void removeActivity(Activity a) {
		list.remove(a);
	}

	/**
	 * 向Activity列表中添加Activity对象
	 */
	public void addActivity(Activity a) {
		list.add(a);
	}

	/**
	 * 关闭Activity列表中的所有Activity
	 */
	public void finishActivity() {
		for (Activity activity : list) {
			if (null != activity) {
				activity.finish();
			}
		}
		// 杀死该应用进程
		android.os.Process.killProcess(android.os.Process.myPid());
	}
}
