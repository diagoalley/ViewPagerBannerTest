package com.diagoalley.android.viewpagerbannertest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.TypedValue;

/**
 * Created by admin on 2015/11/24.
 */
public class MyUtils {
    /**
     * *
     * 转换数值类型
     *
     * @param context
     * @param unit    数据类型 dp sp等
     * @param value   转换前的数值
     * @return
     */
    public static float getRawSize(Context context, int unit, float value) {
        Resources res = context.getResources();
        return TypedValue.applyDimension(unit, value, res.getDisplayMetrics());
    }

    /**
     * 获取通知栏高度
     *
     * @param activity
     * @return
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    /**
     * **
     * 判断系统是否是魅族
     *
     * @return
     */
    public boolean isFlymeOs4x() {
        String sysVersion = android.os.Build.VERSION.RELEASE;
        if ("4.4.4".equals(sysVersion)) {
            String sysIncrement = android.os.Build.VERSION.INCREMENTAL;
            String displayId = android.os.Build.DISPLAY;
            if (!TextUtils.isEmpty(sysIncrement)) {
                return sysIncrement.contains("Flyme_OS_4");
            } else {
                return displayId.contains("Flyme OS 4");
            }
        }
        return false;
    }

    /**
     * *
     * 获取手机尺寸
     *
     * @param activity
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Activity activity) {
        DisplayMetrics dm;
        dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm;
    }


    /**
     * intent跳转
     *
     * @param activity 原activity
     * @param cls      跳转activity
     * @param bundle   传递的bundle
     */
    public static void IntentToOther(Activity activity, Class<?> cls,
                                     Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(activity, cls);
        if (bundle != null)
            intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * 带result的activity跳转
     *
     * @param activity 原activity
     * @param cls      跳转activity
     * @param bundle   传递的bundle
     * @param code     result值
     */
    public static void IntentToOtherForResult(Object activity, Class<?> cls,
                                              Bundle bundle, int code) {
        Intent intent = new Intent();
        if (bundle != null)
            intent.putExtras(bundle);
        if (activity instanceof Activity) {
            intent.setClass((Activity) activity, cls);
            ((Activity) activity).startActivityForResult(intent, code);
        } else if (activity instanceof Fragment) {
            intent.setClass(((Fragment) activity).getActivity(), cls);
            ((Fragment) activity).startActivityForResult(intent, code);
        }
    }

    /**
     * 判断字符串是否为Email *
     */
    public static boolean isEmail(String email) {
        if (email.matches("\\w+([-+.']\\w+)*@\\w+\\.\\w+([-.]\\w+)*")
                && email.length() > 0)
            return true;
        return false;
    }

    /**
     * 判断字符串是否为mobile *
     */
    public static boolean isMobilePhone(String mobile) {
        if (mobile.matches("1[0-9]{10}"))
            return true;
        return false;
    }

    /**
     * @author chengfy
     * created at 15/8/17 10:57
     * description 拨打电话
     */
//    public static void callPhone(Context context, String phone) {
//        try {
//            if (PhoneUtils.isSimExit(context)) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phone));
//                context.startActivity(intent);
//            } else {
//                ToaskUtils.ViewToast((Activity)context,context.getString(R.string.error_no_sim));
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
}
