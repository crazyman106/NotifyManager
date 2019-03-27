package rxjava.fengzi.com.notifymanager.notify.ui;

import android.app.Notification;
import android.content.Context;
import android.support.v4.app.NotificationCompat;

/**
 * 通知的提醒方式:
 * 1、声音提醒
 * 默认声音：builder.setDefaults(Notification.DEFAULT_SOUND) ;
 * 自定义声音: builder.setSound(Uri.parse(""))
 * <p>
 * 2、震动提醒
 * 默认振动:builder.setDefaults(Notification.DEFAULT_VIBRATE) ;
 * 自定义振动:
 * long[] vibrate = {100, 200, 300, 400}; //震动效果
 * builder.setVibrate(vibrate)
 * 表示在100、200、300、400这些时间点交替启动和关闭震动
 * <p>
 * 3.闪烁提醒
 * 默认闪烁:builder.setDefaults(Notification.DEFAULT_LIGHTS) ;
 * 自定义闪烁:builder.setLights()
 * notification.ledARGB = 0xff00ff00; // LED灯的颜色
 * notification.ledOnMS = 300; // LED灯显示的毫秒数，300毫秒
 * notification.ledOffMS = 1000; // LED灯关闭的毫秒数，1000毫秒
 * builder.build().flags = xxx|Notification.FLAG_SHOW_LIGHTS; // 必须加上这个标志
 */

public abstract class INotifyParams extends NotifyDefault {

    @Override
    protected NotificationCompat.Builder getDefault(Context context, String channelId) {
        return super.getDefault(context, channelId);
    }

    protected NotificationCompat.Builder addAction(NotificationCompat.Builder builder) {
        return builder;
    }

    protected Notification addNotifyAndBuilderParams(NotificationCompat.Builder builder) {
        return builder.build();
    }
}
