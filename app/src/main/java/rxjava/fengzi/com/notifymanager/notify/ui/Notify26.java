package rxjava.fengzi.com.notifymanager.notify.ui;

import android.app.PendingIntent;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;
import rxjava.fengzi.com.notifymanager.R;
import rxjava.fengzi.com.notifymanager.notify.NotifyChannels;

/**
 * 注意:如果一个Notification存在多个PendingIntent,若多个PendingIntent相同,那么最后一个生效,其他的被cancel,无法打开
 * <p>
 * PendingIntent的匹配规则
 * 如果两个PendingIntent内部的Intent相同并且requestCode也相同,那么这两个PendingIntent相同,
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
 */
public class Notify26 extends NotifyDefault implements Notify {

    private Context context;

    private NotifyChannels channels;

    public Notify26(Context context) {
        this.context = context;
        channels = new NotifyChannels(context);
        channels.setDefault();
    }


    @Override
    public void sendCustomNotify(INotifyParams notifyParams) {
        channels.getNotificationManager().notify(1, notifyParams.addNotifyAndBuilderParams(notifyParams.getDefault(context, channels.DEFAULT)));
    }

    @Override
    public void sendCustomNotifyBuilder(INotifyParams notifyParams) {
        channels.getNotificationManager().notify(1, notifyParams.addAction(notifyParams.getDefault(context, channels.DEFAULT)).build());
    }

    /**
     * 一般消息通知,显示标题,内容,图标;action:点击后进行的操作
     * // 区分id,图标,标题,内容,右边的图片
     *
     * @param contentAction
     */
    @Override
    public void sendNormalNotify(PendingIntent contentAction) {
        this.sendNormalNotify(contentAction, null);
    }


    @Override
    public void sendNormalNotify(PendingIntent contentAction, PendingIntent deleteAction) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                //设置通知右侧的大图标
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_notifiation_big))
                .setContentIntent(contentAction)
                .setDeleteIntent(deleteAction);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    /**
     * RemoteInput,Notification.Action....
     *
     * @param contentAction
     * @param actions
     */
    @Override
    public void sendActionNotify(PendingIntent contentAction, NotificationCompat.Action... actions) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentAction);
        if (actions != null)
            for (int i = 0; i < actions.length; i++) {
                notifyBuilder.addAction(actions[i]);
            }
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    /**
     * 待回复的notify:必须在android 7以上
     *
     * @param contentAction
     * @param actions
     */
    @Override
    public void sendRemoteInputNotify(PendingIntent contentAction, NotificationCompat.Action... actions) {
        sendActionNotify(contentAction, actions);
    }

    /**
     * // setStyle在Android 4.1之前会被忽略
     *
     * @param contentAction
     * @param style
     */
    @Override
    public void sendBigPictureStyleNotify(PendingIntent contentAction, NotificationCompat.BigPictureStyle style) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentAction)
                .setStyle(style);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    @Override
    public void sendBigTextStyleNotify(PendingIntent contentAction, NotificationCompat.BigTextStyle style) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentAction)
                .setStyle(style);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    @Override
    public void sendInboxStyleNotify(PendingIntent contentAction, NotificationCompat.InboxStyle style) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentAction)
                .setStyle(style);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    @Override
    public void sendMediaStyleNotify(PendingIntent contentAction, android.support.v4.media.app.NotificationCompat.MediaStyle style, NotificationCompat.Action... actions) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentAction)
                //设置通知不可删除
                .setOngoing(true)
                .setStyle(style);
        if (actions != null) {
            for (int i = 0; i < actions.length; i++) {
                notifyBuilder.addAction(actions[i]);
            }
        }
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }


    @Override
    public void sendMessagingStyleNotify(PendingIntent contentAction, NotificationCompat.MessagingStyle style) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(true)
                .setContentIntent(contentAction)
                .setStyle(style);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    @Override
    public void sendProgressViewNotify(PendingIntent contentAction, int progress, boolean indeterminate) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.DEFAULT)
                .setAutoCancel(false)
                .setContentIntent(contentAction)
                .setProgress(100, progress, indeterminate);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    /**
     * 浮动通知(Heads-up Notifications)
     * Android 5.0(API level 21)开始，当屏幕未上锁且亮屏时，通知可以以小窗口形式显示。用户可以在不离开当前应用前提下操作该通知。
     * <p>
     * 以下两种情况会显示浮动通知:
     * 1.setFullScreenIntent()
     * 2.通知拥有高优先级且使用了铃声和振动
     * 可以通过setVisibility()控制通知的显示等级:
     *
     * @param contentAction
     * @param remoteViews
     */
    @Override
    public void sendCustomHeadsUpViewNotify(PendingIntent contentAction, RemoteViews remoteViews) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.CRITICAL)
                //设置点击通知后自动删除通知
                .setAutoCancel(true)
                //设置点击通知时的响应事件
                .setContentIntent(contentAction)
                //设置全屏响应事件;
                .setFullScreenIntent(contentAction, true)
                //设置自定义顶部提醒视图
                .setCustomHeadsUpContentView(remoteViews);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }

    /**
     * 自定义通知
     * 自定义普通视图通知高度限制为64dp，大视图通知高度限制为256dp。同时，建议自定义通知尽量简单，以提高兼容性。
     * <p>
     * 自定义通知需要做如下操作:
     * 1.创建自定义通知布局
     * 2.使用RemoteViews定义通知组件，如图标、文字等
     * 3.setCustomContentView()将RemoteViews对象绑定到NotificationCompat.Builder
     * 4.同正常发送通知流程
     *
     * @param contentAction
     * @param smallRemoteView
     * @param bigRemoteView
     */
    @Override
    public void sendCustomViewNotify(PendingIntent contentAction, RemoteViews smallRemoteView, RemoteViews bigRemoteView) {
        NotificationCompat.Builder notifyBuilder = getDefault(context, channels.MEDIA)
                .setOngoing(true)
                .setShowWhen(true)
                .setContentIntent(contentAction)
                .setCustomContentView(smallRemoteView)
                .setCustomBigContentView(bigRemoteView);
        channels.getNotificationManager().notify((int) (SystemClock.uptimeMillis() / 100), notifyBuilder.build());
    }


}
