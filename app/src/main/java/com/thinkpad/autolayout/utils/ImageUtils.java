package com.thinkpad.autolayout.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;


/**
 * @package com.vungu.gonghui.utils
 * @description: image的工具类
 * @author win64
 * @date 2016-4-13 下午13:44:47
 */
public class ImageUtils {
	/**
	 * 根据InputStream获取图片实际的宽度和高度
	 * 
	 * @param imageStream
	 * @return
	 */
	public static ImageSize getImageSize(InputStream imageStream) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(imageStream, null, options);
		return new ImageSize(options.outWidth, options.outHeight);
	}

	public static class ImageSize {
		int width;
		int height;

		public ImageSize() {
		}

		public ImageSize(int width, int height) {
			this.width = width;
			this.height = height;
		}

		@Override
		public String toString() {
			return "ImageSize{" + "width=" + width + ", height=" + height + '}';
		}
	}

	public static int calculateInSampleSize(ImageSize srcSize, ImageSize targetSize) {
		// 源图片的宽度
		int width = srcSize.width;
		int height = srcSize.height;
		int inSampleSize = 1;

		int reqWidth = targetSize.width;
		int reqHeight = targetSize.height;

		if (width > reqWidth && height > reqHeight) {
			// 计算出实际宽度和目标宽度的比率
			int widthRatio = Math.round((float) width / (float) reqWidth);
			int heightRatio = Math.round((float) height / (float) reqHeight);
			inSampleSize = Math.max(widthRatio, heightRatio);
		}
		return inSampleSize;
	}

	/**
	 * 计算合适的inSampleSize
	 */
	public static int computeImageSampleSize(ImageSize srcSize, ImageSize targetSize, ImageView imageView) {
		final int srcWidth = srcSize.width;
		final int srcHeight = srcSize.height;
		final int targetWidth = targetSize.width;
		final int targetHeight = targetSize.height;

		int scale = 1;

		if (imageView == null) {
			scale = Math.max(srcWidth / targetWidth, srcHeight / targetHeight); // max
		} else {
			switch (imageView.getScaleType()) {
			case FIT_CENTER:
			case FIT_XY:
			case FIT_START:
			case FIT_END:
			case CENTER_INSIDE:
				scale = Math.max(srcWidth / targetWidth, srcHeight / targetHeight); // max
				break;
			case CENTER:
			case CENTER_CROP:
			case MATRIX:
				scale = Math.max(srcWidth / targetWidth, srcHeight / targetHeight); // min
				break;
			default:
				scale = Math.max(srcWidth / targetWidth, srcHeight / targetHeight); // max
				break;
			}
		}

		if (scale < 1) {
			scale = 1;
		}

		return scale;
	}

	/**
	 * 根据ImageView获适当的压缩的宽和高
	 * 
	 * @param view
	 * @return
	 */
	public static ImageSize getImageViewSize(View view) {

		ImageSize imageSize = new ImageSize();

		imageSize.width = getExpectWidth(view);
		imageSize.height = getExpectHeight(view);

		return imageSize;
	}

	/**
	 * 根据view获得期望的高度
	 * 
	 * @param view
	 * @return
	 */
	private static int getExpectHeight(View view) {

		int height = 0;
		if (view == null)
			return 0;

		final ViewGroup.LayoutParams params = view.getLayoutParams();
		// 如果是WRAP_CONTENT，此时图片还没加载，getWidth根本无效
		if (params != null && params.height != ViewGroup.LayoutParams.WRAP_CONTENT) {
			height = view.getWidth(); // 获得实际的宽度
		}
		if (height <= 0 && params != null) {
			height = params.height; // 获得布局文件中的声明的宽度
		}

		if (height <= 0) {
			height = getImageViewFieldValue(view, "mMaxHeight");// 获得设置的最大的宽度
		}

		// 如果宽度还是没有获取到，憋大招，使用屏幕的宽度
		if (height <= 0) {
			DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
			height = displayMetrics.heightPixels;
		}

		return height;
	}

	/**
	 * 根据view获得期望的宽度
	 * 
	 * @param view
	 * @return
	 */
	private static int getExpectWidth(View view) {
		int width = 0;
		if (view == null)
			return 0;

		final ViewGroup.LayoutParams params = view.getLayoutParams();
		// 如果是WRAP_CONTENT，此时图片还没加载，getWidth根本无效
		if (params != null && params.width != ViewGroup.LayoutParams.WRAP_CONTENT) {
			width = view.getWidth(); // 获得实际的宽度
		}
		if (width <= 0 && params != null) {
			width = params.width; // 获得布局文件中的声明的宽度
		}

		if (width <= 0)

		{
			width = getImageViewFieldValue(view, "mMaxWidth");// 获得设置的最大的宽度
		}
		// 如果宽度还是没有获取到，憋大招，使用屏幕的宽度
		if (width <= 0)

		{
			DisplayMetrics displayMetrics = view.getContext().getResources().getDisplayMetrics();
			width = displayMetrics.widthPixels;
		}

		return width;
	}

	/**
	 * 通过反射获取imageview的某个属性值
	 * 
	 * @param object
	 * @param fieldName
	 * @return
	 */
	private static int getImageViewFieldValue(Object object, String fieldName) {
		int value = 0;
		try {
			Field field = ImageView.class.getDeclaredField(fieldName);
			field.setAccessible(true);
			int fieldValue = field.getInt(object);
			if (fieldValue > 0 && fieldValue < Integer.MAX_VALUE) {
				value = fieldValue;
			}
		} catch (Exception e) {
		}
		return value;

	}

	/**
	 * @methods setImageFromUrl
	 * @description 描述信息
	 * @date 2015-12-15 下午2:00:49
	 * @param viewId
	 * @param url
	 * @param defaultImageId
	 * @return 参数说明 //int 默认图片，Imageview ,是那个控件
	 */
//	public static void setImageFromUrl(ImageView mView, String url, int defaultImageId) {
//		try {
//
//			ImageLoader.getInstance().displayImage(
//					url,
//					mView,
//					new DisplayImageOptions.Builder().showStubImage(defaultImageId).showImageForEmptyUri(defaultImageId)
//							.showImageOnFail(defaultImageId).cacheInMemory().cacheOnDisc().resetViewBeforeLoading().build(), null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * 根据图片字节数组，对图片可能进行二次采样，不致于加载过大图片出现内存溢出
	 * 
//	 * @param bytes
	 * @return
	 */
	// public static Bitmap getBitmapByBytes(byte[] bytes){
	//
	// //对于图片的二次采样,主要得到图片的宽与高
	// int width = 0;
	// int height = 0;
	// int sampleSize = 1; //默认缩放为1
	// BitmapFactory.Options options = new BitmapFactory.Options();
	// options.inJustDecodeBounds = true; //仅仅解码边缘区域
	// //如果指定了inJustDecodeBounds，decodeByteArray将返回为空
	// BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
	// //得到宽与高
	// height = options.outHeight;
	// width = options.outWidth;
	//
	// //图片实际的宽与高，根据默认最大大小值，得到图片实际的缩放比例
	// while ((height / sampleSize > Cache.IMAGE_MAX_HEIGHT)
	// || (width / sampleSize > Cache.IMAGE_MAX_WIDTH)) {
	// sampleSize *= 2;
	// }
	//
	// //不再只加载图片实际边缘
	// options.inJustDecodeBounds = false;
	// //并且制定缩放比例
	// options.inSampleSize = sampleSize;
	// return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
	// }

	// 保存Img的方法
	public static File saveImage(Bitmap bmp, String imgPath, String imgName) {
		File appDir = new File(imgPath);
		if (!appDir.exists()) {
			appDir.mkdir();
		}
		File file = new File(appDir, imgName);
		try {
			FileOutputStream fos = new FileOutputStream(file);
			bmp.compress(CompressFormat.JPEG, 100, fos);
			fos.flush();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}
	

	/**
	 * @methods setImageFromFile
	 * @description 从本地设置图片
	 * @date 2016-1-18 上午11:11:17 参数说明
	 */
//	public static void setImageFromFile(ImageView mView, String url, int defaultImageId) {
//		// String imageUri = "http://site.com/image.png"; // 网络图片
//		// String imageUri = "file:///mnt/sdcard/image.png"; //SD卡图片
//		// String imageUri = "content://media/external/audio/albumart/13";媒体文件夹
//		// String imageUri = "assets://image.png"; // assets
//		// String imageUri = "drawable://" + R.drawable.image; // drawable文件
//		url = "file://" + url;
//		ImageLoader.getInstance().displayImage(
//				url,
//				mView,
//				new DisplayImageOptions.Builder().showStubImage(defaultImageId).showImageForEmptyUri(defaultImageId)
//						.showImageOnFail(defaultImageId).cacheInMemory().cacheOnDisc().resetViewBeforeLoading().build(),
//				new ImageLoadingListenerImpl());
//	}

	/**
	 * 保存图片
	 * 
	 * @param fullName
	 * @param bitmap
	 */
	public static void saveBitmap2Dir(String fullName, Bitmap bitmap) {
		if (bitmap != null) {
			try {
				File file = new File(fullName);
				if (!file.exists()) {
					creatFolder(fullName);
					file.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(file);
				bitmap.compress(CompressFormat.PNG, 100, fos);
				fos.flush();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}
	}
	/**
	 * 创建保存文件的文件夹
	 * 
	 * @param fullName
	 *            带文件名的文件路径
	 * @return
	 */
	private static void creatFolder(String fullName) {
		if (getLastName(fullName).contains(".")) {
			String newFilePath = fullName.substring(0, fullName.lastIndexOf('/'));
			File file = new File(newFilePath);
			file.mkdirs();
		}
	}
	/**
	 * 获取最后的‘/’后的文件名
	 * 
	 * @param name
	 * @return
	 */
	public static String getLastName(String name) {
		int lastIndexOf = 0;
		try {
			lastIndexOf = name.lastIndexOf('/');
		} catch (Exception e) {
			e.printStackTrace();
		}
		return !name.equals("") ? name.substring(lastIndexOf + 1) : "";
	}
}
