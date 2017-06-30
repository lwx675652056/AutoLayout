package com.thinkpad.autolayout.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
/**
 * 
* @title: VersionUtil.java 
* @package com.vungu.meimeng.usercenter.engine 
* @description: TODO(版本升级工具类) 
* @date 2015-7-15 下午6:02:14
 */
public class VersionUtil {
    private static PackageInfo pinfo;
	/**
     * 获取版本号
     * */
    public static String getVersionName(Context context) {
        try {
            PackageManager pm = context.getPackageManager();
            String packageName = context.getPackageName();
            pinfo = pm.getPackageInfo(packageName,
                    PackageManager.GET_CONFIGURATIONS);
        } catch (NameNotFoundException e) {
        }
        return pinfo.versionName;
    }
	/**
	 * 
	 * @createdate 2014-2-27 下午3:05:09
	 * @Description: (安装apk)
	 * @param file    文件路径
	 * @param context   上下文环境
	 *
	 */
	public static void installApk(File file,Context context) {
		Intent intent = new Intent();
		// 执行动作
		intent.setAction(Intent.ACTION_VIEW);
		// 执行的数据类型
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");// 编者按：此处Android应为android，否则造成安装不了
		context.startActivity(intent);
	}
	public static Intent installApk(File file) {
		Intent intent = new Intent();
		// 执行动作
		intent.setAction(Intent.ACTION_VIEW);
		// 执行的数据类型
		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");// 编者按：此处Android应为android，否则造成安装不了
		return intent;
	}
	/**
	 * 
	 * @createdate 2014-2-27 下午3:04:53
	 * @Description: (判断SD卡是否存在) true 有sd卡 false 无sd卡
	 *
	 */
	public static boolean ExistSDCard() {
		if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else
			return false;
	}
	// 
	/**
	 * 
	 * @createdate 2014-2-27 下午3:05:39
	 * @Description: (修改文件权限   apk文件权限由-rw------- 变为-rw----r--，可以正常启动。)
	 * @param fileName  
	 * @return
	 *
	 */
	public static String exec(String fileName) {
		String[] args = { "chmod", "604", fileName };
		String result = "";
		ProcessBuilder processBuilder = new ProcessBuilder(args);
		Process process = null;
		InputStream errIs = null;
		InputStream inIs = null;
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			int read = -1;
			process = processBuilder.start();
			errIs = process.getErrorStream();
			while ((read = errIs.read()) != -1) {
				baos.write(read);
			}
			baos.write('n');
			inIs = process.getInputStream();
			while ((read = inIs.read()) != -1) {
				baos.write(read);
			}
			byte[] data = baos.toByteArray();
			result = new String(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (errIs != null) {
					errIs.close();
				}
				if (inIs != null) {
					inIs.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (process != null) {
				process.destroy();
			}
		}
		System.out.println(result);
		return result;
	}


}
