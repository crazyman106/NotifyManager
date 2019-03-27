package rxjava.fengzi.com.notifymanager.notify;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Color;

import java.util.Arrays;


/**
 * 可以查看源码:发现必须创建channel
 * NotificationManager.notify()->notifyAsUser()->NotificationManagerService.enqueueNotificationWithTag()->RankingHelper.shouldHaveDefaultChannel(Record r)
 * <p>
 * 在android版本targetSdk >= 26 时，系统是不会给你添加默认Channel的,需要我们自己创建管理notifyChannel,适配android O+
 * <p>
 * 在android版本>=26时,创建NotificationCompat.Builder时,需要传入对应的channelId.否则发送通知时会找不到对应ChannelId的Notification
 * <p>
 * Notification的一些属性设置在android版本targetSdk >= 26 由它NotificationChannel来设置:通知声音、呼吸灯、震动、优先级
 * <p>
 * <p>
 * NotificationChannel是在程序初始化时创建并注册，不要每次发通知的时候都去重新创建一次，没有任何意义。
 * <p>
 * 已经设置的 Channel 属性又不能更改:声音、震动、呼吸灯、优先级,它们在创建的时候就已经确定了.只有Name和Description可以更改,优先级往低了调(前提是用户没有手动更改过).
 * 如果想要改变的话只能重新创建一个新的不同ChannelId的Channel或者清除应用数据又或者卸载应用
 * <p>
 * 调用delete函数只是更改了NotificationChannel的mDeleted状态,没有从列表中把它移除掉.
 * <p>
 * 注意:如果想彻底删除已经创建注册的Channel，只有清除应用数据或者卸载应用。
 *
 * <p>
 * 不同优先级别的Channel
 * NotificationManager.IMPORTANCE_NONE = 0
 * NotificationManager.IMPORTANCE_MIN = 1
 * NotificationManager.IMPORTANCE_LOW = 2
 * NotificationManager.IMPORTANCE_DEFAULT = 3
 * NotificationManager.IMPORTANCE_HIGH = 4
 * <p>
 * <p>
 * Importance 	Behavior 	                            Usage 	                                                                                                    Examples
 * HIGH         Makes a sound and appears on screen     Time-critical information that the user must know, or act on, immediately                                   Text messages, alarms, phone calls
 * DEFAULT      Makes a sound                           Information that should be seen at the user’s earliest convenience, but not interrupt what they're doing   Traffic alerts, task reminders
 * LOW          No sound                                Notification channels that don't meet the requirements of other importance levels                           New content the user has subscribed to, social network invitations
 * MIN          No sound or visual interruption         Non-essential information that can wait or isn’t specifically relevant to the user                         Nearby places of interest, weather, promotional content
 * NONE         Don't show in the shade                 Normally, Suppressing notification from package by user request                                             Blocked apps notification
 */

public class NotifyChannels extends ContextWrapper {

    public final static String MEDIA = "media";
    public final static String CRITICAL = "critical";
    public final static String IMPORTANCE = "importance";
    public final static String DEFAULT = "default";
    public final static String LOW = "low";

    NotificationManager notificationManager;

    public NotifyChannels(Context base) {
        super(base);
        notificationManager = (NotificationManager) base.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setDefault() {
        NotificationChannel mediaChannel = new NotificationChannel(
                MEDIA,
                "media",
                NotificationManager.IMPORTANCE_DEFAULT);
        mediaChannel.enableLights(true);
        mediaChannel.enableVibration(true);
        mediaChannel.setLightColor(Color.YELLOW);
        mediaChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});
        NotificationChannel defaultChannel = new NotificationChannel(
                IMPORTANCE,
                "critical",
                NotificationManager.IMPORTANCE_DEFAULT);
        defaultChannel.enableLights(true);
        defaultChannel.enableVibration(true);
        defaultChannel.setLightColor(Color.YELLOW);
        defaultChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});
        // 设置锁屏显示状态
        defaultChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        NotificationChannel highChannel = new NotificationChannel(
                CRITICAL,
                "importance",
                NotificationManager.IMPORTANCE_HIGH);
        highChannel.enableLights(true);
        highChannel.enableVibration(true);
        highChannel.setLightColor(Color.YELLOW);
        highChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});
        NotificationChannel lowChannel = new NotificationChannel(
                DEFAULT,
                "default",
                NotificationManager.IMPORTANCE_DEFAULT);
        lowChannel.enableLights(true);
        lowChannel.enableVibration(true);
        lowChannel.setLightColor(Color.YELLOW);
        lowChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});

        NotificationChannel minChannel = new NotificationChannel(
                LOW,
                "low",
                NotificationManager.IMPORTANCE_MIN);
        minChannel.enableLights(true);
        minChannel.enableVibration(true);
        minChannel.setLightColor(Color.YELLOW);
        minChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 600});
        notificationManager.createNotificationChannels(Arrays.asList(highChannel, defaultChannel, lowChannel, minChannel, mediaChannel));
    }

    public void addChannel(String channelId, String channelName, String channel, int channelImportance) {
        notificationManager.createNotificationChannel(new NotificationChannel(channelId, channelName, channelImportance));
    }

    public void addChannel(NotificationChannel channel) {
        notificationManager.createNotificationChannel(channel);
    }

    public void addChannels(NotificationChannelGroup channelGroup) {
        notificationManager.createNotificationChannelGroup(channelGroup);
    }

}
