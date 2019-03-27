package rxjava.fengzi.com.notifymanager.notify.bean;

import android.app.PendingIntent;

/**
 * 通知的提醒方式:
 * 1、声音提醒
 * <p>
 * 默认声音
 * notification.defaults |= Notification.DEFAULT_SOUND;
 * 自定义声音
 * notification.sound = Uri.parse("file:///sdcard0/notification.ogg");
 * <p>
 * 2、震动提醒
 * 默认振动
 * notification.defaults |= Notification.DEFAULT_VIBRATE;
 * 自定义振动
 * long[] vibrate = {100, 200, 300, 400}; //震动效果
 * // 表示在100、200、300、400这些时间点交替启动和关闭震动 notification.vibrate = vibrate;
 * <p>
 * 闪烁提醒
 * 默认闪烁
 * notification.defaults |= Notification.DEFAULT_LIGHTS;
 * 自定义闪烁
 * notification.ledARGB = 0xff00ff00; // LED灯的颜色，绿灯
 * notification.ledOnMS = 300; // LED灯显示的毫秒数，300毫秒
 * notification.ledOffMS = 1000; // LED灯关闭的毫秒数，1000毫秒
 * notification.flags |= Notification.FLAG_SHOW_LIGHTS; // 必须加上这个标志
 * <p>
 */
public class BaseNotifyType {

    private int mSmallIcon;
    private CharSequence mTitle;
    private CharSequence mContent;
    private boolean mShowWhen = true;
    private PendingIntent mContentIntent;
    private boolean mAutoCancel = false;

}
