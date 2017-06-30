package com.thinkpad.autolayout.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sj on 2015/11/15.
 */
public class SharedPreferenceUtil {
    public static void saveData(Context context,String fileName,String key,Object data){

        String type = data.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context
                .getSharedPreferences(fileName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if ("Integer".equals(type)){
            editor.putInt(key, (Integer)data);
        }else if ("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)data);
        }else if ("String".equals(type)){
            editor.putString(key, (String)data);
        }else if ("Float".equals(type)){
            editor.putFloat(key, (Float)data);
        }else if ("Long".equals(type)){
            editor.putLong(key, (Long)data);
        }

        editor.commit();
    }

    /**
     * 从文件中读取数据
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static  <T extends  Object> T getData(Context context,String fileName, String key, Object defValue){

        String type = defValue.getClass().getSimpleName();
        SharedPreferences sharedPreferences = context.getSharedPreferences
                (fileName, Context.MODE_PRIVATE);

        //defValue为为默认值，如果当前获取不到数据就返回它
        if ("Integer".equals(type)){
            return (T)(Integer)sharedPreferences.getInt(key, (Integer)defValue);
        }else if ("Boolean".equals(type)){
            return (T)(Boolean)sharedPreferences.getBoolean(key, (Boolean)defValue);
        }else if ("String".equals(type)){
            return (T)sharedPreferences.getString(key, (String)defValue);
        }else if ("Float".equals(type)){
            return (T)(Float)sharedPreferences.getFloat(key, (Float)defValue);
        }else if ("Long".equals(type)){
            return (T)(Long)sharedPreferences.getLong(key, (Long)defValue);
        }

        throw new RuntimeException("get SharedPrefrences error!!");
    }
    public static final String SHARED_PREFERENCE_NAME = "itktnew"; // SharedPreference操作的文件
    public static final String ISFIRSTENTER = "isFirstEnter";// 是否第一此进入应用
    /**
     * @Description: 保存int数值
     * @param context
     * @param key
     * @param value
     */
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * @Description: 获取保存的int数值
     * @param context
     * @param key
     * @return
     */
    public static int getInt(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        int value = shared.getInt(key, 0);
        return value;
    }

    /**
     * @Description: 保存long数值
     * @param context
     * @param key
     * @param value
     */
    public static void saveLong(Context context, String key, long value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * @Description: 获取保存的long数值
     * @param context
     * @param key
     * @return
     */
    public static long getLong(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        long value = shared.getLong(key, 0L);
        return value;
    }

    /**
     * @Description: 保存boolean值
     * @param context
     * @param key
     * @param value
     */
    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * @Description: 获取boolean值
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        boolean value = shared.getBoolean(key, false);
        return value;
    }

    /**
     * @Description: 保存String数值
     * @param context
     * @param key
     * @param value
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * @Description: 获取保存的String数值
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        String value = shared.getString(key, "");
        return value;
    }

    /**
     *
     * @Description 清空本地的缓存
     * @param context
     * @param key
     */
    public static void removeString(Context context, String key) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove(key);
        editor.commit();
    }

    /**
     *
     * @createdate 2014-12-22 上午11:03:35
     * @Description: (判断是否第一次进入)
     * @param context
     * @return
     *
     */
    public static boolean isFirstEnter(Context context) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        return shared.getBoolean(ISFIRSTENTER, true);
    }
    /**
     *
     * @createdate 2014-12-22 上午11:06:45
     * @Description: (修改进入状态)
     * @param context
     * @param changeBoolean
     *
     */
    public static void changeFirstEnter(Context context, boolean changeBoolean) {
        SharedPreferences shared = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putBoolean(ISFIRSTENTER, changeBoolean);
        editor.commit();
    }
    /**
     * @Description 保存信用卡信息
     * @param isNotVip
     *            是否是非会员，true为非会员，false为会员
     */
    // public static void saveCreditCardInfo(Context context, CreditCardModel
    // bean, boolean isNotVip) {
    // // boolean isNotVip =
    // this.getIntent().getBooleanExtra(IntentConstants.NO_VIP, true); //
    // 是否是非会员，true为非会员，false为会员
    // if (isNotVip == false) { // 只有会员才保存信用卡信息
    // SharedPreferenceUtil.saveString(context, "credit_card_userId",
    // ItktApplication.USER_ID); // 用户ID
    // SharedPreferenceUtil.saveString(context, "credit_card_id", bean.getId());
    // SharedPreferenceUtil.saveString(context, "credit_card_username",
    // bean.getUserName());
    // SharedPreferenceUtil.saveString(context, "credit_card_idCard",
    // bean.getIdCard());
    // SharedPreferenceUtil.saveString(context, "credit_card_bank",
    // bean.getBank());
    // SharedPreferenceUtil.saveString(context, "credit_card_bankId",
    // bean.getBankId());
    // SharedPreferenceUtil.saveString(context, "credit_card_bankIdCard",
    // bean.getBankIdCard());
    // SharedPreferenceUtil.saveString(context, "credit_card_validityDate",
    // bean.getValidityDate());
    // }
    // }

    /**
     * @Description 清空本地信用卡信息
     */
    public static void clearCreditCardInfo(Context context) {
        SharedPreferenceUtil.removeString(context, "credit_card_userId");
        SharedPreferenceUtil.removeString(context, "credit_card_id");
        SharedPreferenceUtil.removeString(context, "credit_card_username");
        SharedPreferenceUtil.removeString(context, "credit_card_idCard");
        SharedPreferenceUtil.removeString(context, "credit_card_bank");
        SharedPreferenceUtil.removeString(context, "credit_card_bankId");
        SharedPreferenceUtil.removeString(context, "credit_card_bankIdCard");
        SharedPreferenceUtil.removeString(context, "credit_card_validityDate");
    }
}
