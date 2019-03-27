package rxjava.fengzi.com.notifymanager.notify.ui;

import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

/**
 * 定义通知文本样式:
 * 通知的背景颜色在不同的设备和版本中有所不同，Android2.3开始，系统定义了一套标准通知文本样式，建议自定义通知使用标准样式，这样有助于通知文本可见。
 * 通知文本样式:
 *
 * <p>
 * Android 5.0之前可用:
 * android:style/TextAppearance.StatusBar.EventContent.Title    // 通知标题样式
 * android:style/TextAppearance.StatusBar.EventContent             // 通知内容样式
 * <p>
 * Android 5.0及更高版本:
 * android:style/TextAppearance.Material.Notification.Title         // 通知标题样式
 * android:style/TextAppearance.Material.Notification                  // 通知内容样式
 * <p>
 * 更多通知的标准样式和布局，可参考源码frameworks/base/core/res/res/layout路径下的通知模版如:
 * <p>
 * <p>
 * Android 5.0之前:
 * notification_template_base.xml
 * notification_template_big_base.xml
 * notification_template_big_picture.xml
 * notification_template_big_text.xml
 * <p>
 * Android 5.0 及更高版本:
 * notification_template_material_base.xml
 * notification_template_material_big_base.xml
 * notification_template_material_big_picture.xml
 * notification_template_part_chronometer.xml
 * notification_template_progressbar.xml
 * <p>
 * <p>
 * <p>
 * 保留Activity返回栈:
 * 默认情况下，从通知启动一个Activity，按返回键会回到主屏幕。但某些时候有按返回键仍然留在当前应用的需求，这就要用到TaskStackBuilder了。
 * <p>
 * 1、在manifest中定义Activity的关系
 * Android 4.0.3 及更早版本
 * <activity
 * android:name=".ResultActivity">
 * <meta-data
 * android:name="android.support.PARENT_ACTIVITY"
 * android:value=".MainActivity"/>
 * </activity>
 * <p>
 * Android 4.1 及更高版本
 * <activity
 * android:name=".ResultActivity"
 * android:parentActivityName=".MainActivity">
 * </activity>
 * <p>
 * 2、创建返回栈PendingIntent
 * Intent resultIntent = new Intent(this, ResultActivity.class);
 * TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
 * // 添加返回栈
 * stackBuilder.addParentStack(ResultActivity.class);
 * // 添加Intent到栈顶
 * stackBuilder.addNextIntent(resultIntent);
 * // 创建包含返回栈的pendingIntent
 * PendingIntent resultPendingIntent =
 * stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
 * <p>
 * NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
 * builder.setContentIntent(resultPendingIntent);
 * NotificationManager mNotificationManager =
 * (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
 * mNotificationManager.notify(id, builder.build());
 * <p>
 * <p>
 * 特殊Activity:
 * 默认情况下，从通知启动的Activity会在近期任务列表里出现。如果不需要在近期任务里显示，则需要做以下操作:
 * 1、在manifest中定义Activity
 * <activity
 * android:name=".ResultActivity"
 * android:launchMode="singleTask"
 * android:taskAffinity=""
 * android:excludeFromRecents="true">
 * </activity>
 * <p>
 * 2、构建PendingIntent
 * NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
 * Intent notifyIntent = new Intent(this, ResultActivity.class);
 * <p>
 * // Sets the Activity to start in a new, empty task
 * notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
 * | Intent.FLAG_ACTIVITY_CLEAR_TASK);
 * <p>
 * PendingIntent notifyPendingIntent =
 * PendingIntent.getActivity(this, 0, notifyIntent,
 * PendingIntent.FLAG_UPDATE_CURRENT);
 * <p>
 * builder.setContentIntent(notifyPendingIntent);
 * NotificationManager mNotificationManager =
 * (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
 * mNotificationManager.notify(id, builder.build());
 */
public interface Notify {


    void sendNormalNotify(PendingIntent contentAction);

    void sendNormalNotify(PendingIntent contentAction, PendingIntent deleteAction);

    void sendActionNotify(PendingIntent contentAction, NotificationCompat.Action... actions);

    void sendRemoteInputNotify(PendingIntent contentAction, NotificationCompat.Action... actions);

    void sendBigPictureStyleNotify(PendingIntent contentAction, NotificationCompat.BigPictureStyle style);

    void sendBigTextStyleNotify(PendingIntent contentAction, NotificationCompat.BigTextStyle style);

    void sendInboxStyleNotify(PendingIntent contentAction, NotificationCompat.InboxStyle style);

    void sendMediaStyleNotify(PendingIntent contentAction, android.support.v4.media.app.NotificationCompat.MediaStyle mediaStyle, NotificationCompat.Action... actions);

    void sendMessagingStyleNotify(PendingIntent contentAction, NotificationCompat.MessagingStyle style);

    void sendProgressViewNotify(PendingIntent contentAction, int progress, boolean indeterminate);

    void sendCustomHeadsUpViewNotify(PendingIntent contentAction, RemoteViews remoteViews);

    void sendCustomViewNotify(PendingIntent contentAction, RemoteViews smallRemoteView, RemoteViews bigRemoteView);


}
