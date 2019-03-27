package rxjava.fengzi.com.notifymanager.notify;

import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Arrays;


/**
 * 管理notifyChannel,适配android O+
 * <p>
 * 不同优先级别的Channel
 * NotificationManager.IMPORTANCE_NONE = 0
 * NotificationManager.IMPORTANCE_MIN = 1
 * NotificationManager.IMPORTANCE_LOW = 2
 * NotificationManager.IMPORTANCE_DEFAULT = 3
 * NotificationManager.IMPORTANCE_HIGH = 4
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
        mediaChannel.setSound(null, null);
        notificationManager.createNotificationChannels(Arrays.asList(
                new NotificationChannel(
                        CRITICAL,
                        "2",
                        NotificationManager.IMPORTANCE_HIGH),
                new NotificationChannel(
                        IMPORTANCE,
                        "1",
                        NotificationManager.IMPORTANCE_DEFAULT),
                new NotificationChannel(
                        DEFAULT,
                        "0",
                        NotificationManager.IMPORTANCE_LOW),
                new NotificationChannel(
                        LOW,
                        "-1",
                        NotificationManager.IMPORTANCE_MIN),
                mediaChannel
        ));
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
