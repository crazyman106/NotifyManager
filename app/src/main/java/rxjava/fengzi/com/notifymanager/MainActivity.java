package rxjava.fengzi.com.notifymanager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import rxjava.fengzi.com.notifymanager.notify.NotifyConstances;
import rxjava.fengzi.com.notifymanager.notify.ui.INotifyParams;
import rxjava.fengzi.com.notifymanager.notify.ui.Notify;
import rxjava.fengzi.com.notifymanager.notify.ui.Notify26;

import static rxjava.fengzi.com.notifymanager.notify.NotifyConstances.REMOTE_INPUT_RESULT_KEY;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("task_id:", getTaskId() + "");
        // 测试呼吸灯(notification的呼吸灯在屏幕黑屏后才会显示)
        findViewById(R.id.btn_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        new Notify26(MainActivity.this).sendCustomNotifyBuilder(new INotifyParams() {
                            @Override
                            protected NotificationCompat.Builder getDefault(Context context, String channelId) {
                                return super.getDefault(context, channelId);
                            }

                            @Override
                            protected NotificationCompat.Builder addAction(NotificationCompat.Builder builder) {
                                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                                intent.setAction(Intent.ACTION_VIEW);
                                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                                builder.setContentIntent(pendingIntent);
                                return builder;
                            }
                        });
                    }
                }, 5000);
            }
        });
        findViewById(R.id.btn_action).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Result2Activity.class);
                intent.setAction(Intent.ACTION_VIEW);
                PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                Intent openIntent = new Intent(MainActivity.this, Result1Activity.class);
                openIntent.setAction(Intent.ACTION_VIEW);
                PendingIntent openPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, openIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Action openActionBuilder = new NotificationCompat.Action.Builder(
                        R.mipmap.ic_yes,
                        "open",
                        openPendingIntent)
                        .build();
                //创建点击通知 NO 按钮时发送的广播
                Intent closeIntent = new Intent(MainActivity.this, ResultActivity.class);
                closeIntent.setAction(Intent.ACTION_VIEW);
                PendingIntent closePendingIntent = PendingIntent.getActivity(MainActivity.this, 0, closeIntent, PendingIntent.FLAG_CANCEL_CURRENT);
                NotificationCompat.Action closeActionBuilder = new NotificationCompat.Action.Builder(
                        R.mipmap.ic_no,
                        "close",
                        closePendingIntent)
                        .build();
                new Notify26(MainActivity.this).sendActionNotify(pendingIntent, new NotificationCompat.Action[]{
                        openActionBuilder, closeActionBuilder
                });
            }
        });
        findViewById(R.id.btn_remote_input).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RemoteInput remoteInput = new RemoteInput.Builder(REMOTE_INPUT_RESULT_KEY)
                        .setLabel("回复内容...").build();
                Intent remoteInputIntent = new Intent(MainActivity.this, ResultActivity.class);
                remoteInputIntent.setAction(NotifyConstances.ACTION_REMOTE_INPUT);
                PendingIntent replyPendingIntent = PendingIntent.getActivity(MainActivity.this, 2, remoteInputIntent, 0);
                NotificationCompat.Action replyAction = new NotificationCompat.Action.Builder(
                        R.mipmap.ic_reply,
                        "回复",
                        replyPendingIntent)
                        .addRemoteInput(remoteInput)
                        .build();
                new Notify26(MainActivity.this).sendRemoteInputNotify(null, new NotificationCompat.Action[]{
                        replyAction
                });
            }
        });
        findViewById(R.id.btn_big_picture_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.BigPictureStyle style = new NotificationCompat.BigPictureStyle()
                        .setBigContentTitle("大图展示中...")
                        .setSummaryText("notification展示大图")
                        .bigPicture(BitmapFactory.decodeResource(getResources(), R.mipmap.big_style_picture))
                        .bigLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.big_style_picture));// 大图展示时,右边的图标
                new Notify26(MainActivity.this).sendBigPictureStyleNotify(null, style);
            }
        });
        findViewById(R.id.btn_big_text_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Notify26(MainActivity.this).sendCustomNotifyBuilder(new INotifyParams() {

                    @Override
                    protected NotificationCompat.Builder addAction(NotificationCompat.Builder builder) {
                        NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle()
                                .setBigContentTitle("大文本展示...")
                                .setSummaryText("notification展示大文本")
                                .bigText("的说法来得及as风口浪尖十大风口浪尖代课" +
                                        "老师副经理看的撒打飞机离开大水井坊考虑到举案" +
                                        "说法打扫房间扣篮大赛甲方考虑到静安寺打算理发进口" +
                                        "量大幅答复进口了对事件爱上对方就离开大幅打扫房间扩大设计费");
                        return builder.setStyle(style);
                    }
                });
            }
        });
        findViewById(R.id.btn_big_text_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Notify26(MainActivity.this).sendCustomNotifyBuilder(new INotifyParams() {

                    @Override
                    protected NotificationCompat.Builder addAction(NotificationCompat.Builder builder) {
                        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                                .setBigContentTitle("大文本展示...")
                                .setSummaryText("notification展示大文本")
                                // 最多6行
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈1")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈2")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈3")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈4")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈5")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈6")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈7")
                                .addLine("哈哈哈哈哈哈哈哈哈哈哈8");
                        return builder.setStyle(style);
                    }
                });
            }
        });
    }
}
