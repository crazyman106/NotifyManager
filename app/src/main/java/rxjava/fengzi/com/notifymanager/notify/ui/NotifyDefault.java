package rxjava.fengzi.com.notifymanager.notify.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.support.v4.app.NotificationCompat;
import rxjava.fengzi.com.notifymanager.R;

import java.util.List;

/**
 * ===============================================================================================
 * 已过时:在android O中
 * Notification标志(flag)介绍
 * <p>
 * Notification.FLAG_SHOW_LIGHTS //三色灯提醒，在使用三色灯提醒时候必须加该标志符
 * Notification.FLAG_ONGOING_EVENT //发起正在运行事件（活动中）
 * Notification.FLAG_INSISTENT //让声音、振动无限循环，直到用户响应 （取消或者打开）
 * Notification.FLAG_ONLY_ALERT_ONCE //发起Notification后，铃声和震动均只执行一次
 * Notification.FLAG_AUTO_CANCEL //用户单击通知后自动消失
 * Notification.FLAG_NO_CLEAR //只有全部清除时，Notification才会清除 ，不清除该通知
 * Notification.FLAG_FOREGROUND_SERVICE //表示正在运行的服务
 * <p>
 * <p>
 * public static final int DEFAULT_ALL = -1;使用所有默认值，比如声音，震动，闪屏等等
 * public static final int DEFAULT_SOUND = 1;使用默认提示声音
 * public static final int DEFAULT_VIBRATE = 2;使用默认手机震动，// 需要在Manifest中添加震动权限
 * public static final int DEFAULT_LIGHTS = 4;使用默认闪光提示
 * public static final int STREAM_DEFAULT = -1;
 * <p>
 * ===============================================================================================
 * PendingIntent的匹配规则
 * 如果两个PendingIntent内部的Intent相同并且requestCode也相同,那么这两个PendingIntent相同,
 * <p>
 * <p>
 * Intent的匹配规则:
 * 如果两个Intent的ComponentName和intent-filter都相同,那么两个Intent都相同.
 * <p>
 * FLAG_ONE_SHOT:当前描述的PendingIntent只能被使用一次,然后它就会被自动cancel,如果后续还有相同的PendingIntent,那么他们的send方法就会调用失败.
 * 对于通知栏消息来说,如果采用此标记,那么同类的通知只能使用一次,后续的通知单机后将无法打开.
 * FLAG_NO_CLEAR:当前描述的PendingIntent不会主动创建,如果当前PendingIntent之前不存在,那么getActivity,getService,getBroadcast方法会直接返回null,
 * 既获取PendingIntent失败,
 * FLAG_CANCEL_CURRENT:当前描述的PendingIntent如果已经存在,那么他们都会被cancel,然后系统会创建一个新的PendingIntent,对于通知栏来说,那些被cancel的消息单击后将无法打开
 * FLAG_UPDATE_CURRENT:当前描述的PendingIntent如果已经存在,那么他们都会被更新,既他们的Intent中的Extras会被替换成最新的.
 * <p>
 * <p>
 * <p>
 * 锁屏通知:
 * Android 5.0(API level 21)开始，通知可以显示在锁屏上。用户可以通过设置选择是否允许敏感的通知内容显示在安全的锁屏上。
 * 你的应用可以通过setVisibility()控制通知的显示等级:
 * VISIBILITY_PRIVATE : 显示基本信息，如通知的图标，但隐藏通知的全部内容
 * VISIBILITY_PUBLIC : 显示通知的全部内容
 * VISIBILITY_SECRET : 不显示任何内容，包括图标
 */
public class NotifyDefault {

    protected NotificationCompat.Builder getDefault(Context context, String channelId) {
        return new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("NotifyTitle")
                .setContentText("NotifyContent")
                .setShowWhen(true);
    }

}
