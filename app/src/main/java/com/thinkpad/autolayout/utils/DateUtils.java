package com.thinkpad.autolayout.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@SuppressLint("SimpleDateFormat")
public class DateUtils {

	public static String getDateString(String format, long longTime) {
		SimpleDateFormat sformat = null;
		if (format == null || "".equals(format)) {
			sformat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
		} else {
			sformat = new SimpleDateFormat(format, Locale.getDefault());
		}
		if (longTime == 0) {
			return sformat.format(new Date(System.currentTimeMillis()));
		}
		return sformat.format(new Date(longTime));
	}
	/**
	 * 返回格式化好的日期类型
	 **/
	@SuppressLint("SimpleDateFormat")
	public static String getTime(String time) {
		long newtime = Long.parseLong(time);
		Date date = new Date(newtime);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}

	/**
	 * 返回格式化好的日期类型
	 **/
	@SuppressLint("SimpleDateFormat")
	public static String getTimeDeatil(long time) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	/**
	 * 返回月和天
	 * @param time
	 * @return
	 */
	@SuppressLint("SimpleDateFormat")
	public static String getTimeMonthAndDay(Long time) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat( "MM月dd日 " );
		return sdf.format(date);
	}

	/**
	 * 转换中文对应的时段
	 *
	 * @param date
	 * @return
	 */
	public static String convertNowHour2CN(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH");
		String hourString = sdf.format(date);
		int hour = Integer.parseInt(hourString);
		if (hour < 6) {
			return "凌晨";
		} else if (hour >= 6 && hour < 12) {
			return "早上";
		} else if (hour == 12) {
			return "中午";
		} else if (hour > 12 && hour <= 18) {
			return "下午";
		} else if (hour >= 19) {
			return "晚上";
		}
		return "";
	}

	/**
	 * 剩余秒数转换
	 *
	 * @param time
	 * @return
	 */
	public static String convertSecond2Day(int time) {
		int day = time / 86400;
		int hour = (time - 86400 * day) / 3600;
		int min = (time - 86400 * day - 3600 * hour) / 60;
		int sec = (time - 86400 * day - 3600 * hour - 60 * min);
		StringBuilder sb = new StringBuilder();
		sb.append(day);
		sb.append("天");
		sb.append(hour);
		sb.append("时");
		sb.append(min);
		sb.append("分");
		sb.append(sec);
		sb.append("秒");
		return sb.toString();
	}

	/* 获取系统时间 格式为："yyyy/MM/dd " */

	public static String getCurrentDate() {
		Date date = new Date();
		SimpleDateFormat sfData = new SimpleDateFormat("yyyy年MM月dd日");

		return sfData.format(date);
	}

	/* 时间戳转换成字符串 */
	public static String getDateToString(String time) {
		Date d = new Date(time);
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");

		return sf.format(d);
	}

	/* 将字符串转为时间戳 */
	public static long getStringToDate(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = new Date();
		try {
			date = sdf.parse(time);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date.getTime();
	}
//	/**
//	 * 根据图片字节数组，对图片可能进行二次采样，不致于加载过大图片出现内存溢出
//	 * @param bytes
//	 * @return
//	 */
//	public static Bitmap getBitmapByBytes(byte[] bytes){
//
//	    //对于图片的二次采样,主要得到图片的宽与高
//	    int width = 0;
//	    int height = 0;
//	    int sampleSize = 1; //默认缩放为1
//	    BitmapFactory.Options options = new BitmapFactory.Options();
//	    options.inJustDecodeBounds = true;  //仅仅解码边缘区域
//	    //如果指定了inJustDecodeBounds，decodeByteArray将返回为空
//	    BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
//	    //得到宽与高
//	    height = options.outHeight;
//	    width = options.outWidth;
//
//	    //图片实际的宽与高，根据默认最大大小值，得到图片实际的缩放比例
//	    while ((height / sampleSize > Cache.IMAGE_MAX_HEIGHT)
//	            || (width / sampleSize > Cache.IMAGE_MAX_WIDTH)) {
//	        sampleSize *= 2;
//	    }
//
//	    //不再只加载图片实际边缘
//	    options.inJustDecodeBounds = false;
//	    //并且制定缩放比例
//	    options.inSampleSize = sampleSize;
//	    return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
//	}
}
