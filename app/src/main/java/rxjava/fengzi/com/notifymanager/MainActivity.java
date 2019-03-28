package rxjava.fengzi.com.notifymanager;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.Person;
import android.support.v4.app.RemoteInput;
import android.support.v4.graphics.drawable.IconCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import rxjava.fengzi.com.notifymanager.notify.NotifyConstances;
import rxjava.fengzi.com.notifymanager.notify.ui.INotifyParams;
import rxjava.fengzi.com.notifymanager.notify.ui.Notify26;
import rxjava.fengzi.com.notifymanager.notify.ui.NotifyServer;

import static rxjava.fengzi.com.notifymanager.notify.NotifyConstances.*;

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
        findViewById(R.id.btn_inbox_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Notify26(MainActivity.this).sendCustomNotifyBuilder(new INotifyParams() {

                    @Override
                    protected NotificationCompat.Builder addAction(NotificationCompat.Builder builder) {
                        NotificationCompat.InboxStyle style = new NotificationCompat.InboxStyle()
                                .setBigContentTitle("大文本展示...")
                                .setSummaryText("notification展示大文本")
                                // 最多7行
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

        findViewById(R.id.btn_media_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建Action按钮
                Intent playOrPauseIntent = new Intent(MainActivity.this, NotifyServer.class);
                playOrPauseIntent.setAction(ACTION_MEDIA_STYLE);
                playOrPauseIntent.putExtra(EXTRA_OPTIONS, NotifyConstances.EXTRA_MEDIA_STYLE_PLAY);
                PendingIntent playOrPausePendingIntent = PendingIntent.getService(MainActivity.this, 0, playOrPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Action playOrPauseAction = new NotificationCompat.Action.Builder(
                        R.mipmap.ic_pause, "PAUSE", playOrPausePendingIntent).build();

                Intent nextIntent = new Intent(MainActivity.this, NotifyServer.class);
                nextIntent.setAction(ACTION_MEDIA_STYLE);
                nextIntent.putExtra(EXTRA_OPTIONS, EXTRA_MEDIA_STYLE_NEXT);
                PendingIntent nextPendingIntent = PendingIntent.getService(MainActivity.this, 1, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Action nextAction = new NotificationCompat.Action.Builder(
                        R.mipmap.ic_next, "Next", nextPendingIntent).build();

                Intent deleteIntent = new Intent(MainActivity.this, NotifyServer.class);
                deleteIntent.setAction(ACTION_MEDIA_STYLE);
                deleteIntent.putExtra(EXTRA_OPTIONS, EXTRA_MEDIA_STYLE_DELETE);
                PendingIntent deletePendingIntent = PendingIntent.getService(MainActivity.this, 2, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Action deleteAction = new NotificationCompat.Action.Builder(
                        R.mipmap.ic_delete, "Delete", deletePendingIntent).build();

                android.support.v4.media.app.NotificationCompat.MediaStyle style = new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0, 1, 2);
                new Notify26(MainActivity.this).sendMediaStyleNotify(null, style, new NotificationCompat.Action[]{playOrPauseAction, nextAction, deleteAction});
            }
        });

        findViewById(R.id.btn_messaging_style).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.MessagingStyle style = new NotificationCompat.MessagingStyle(
                        new Person.Builder().setName("李俊杰").setIcon(IconCompat.createWithResource(MainActivity.this, R.mipmap.ic_launcher)).build())
                        .addMessage(new NotificationCompat.MessagingStyle.Message("this is person", SystemClock.currentThreadTimeMillis(),
                                new Person.Builder().setName("李俊杰1").setIcon(IconCompat.createWithResource(MainActivity.this, R.mipmap.ic_launcher)).build()))
                        .setConversationTitle("message notification");
                new Notify26(MainActivity.this).sendMessagingStyleNotify(null, style);
            }
        });

        findViewById(R.id.btn_progress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Notify26(MainActivity.this).sendProgressViewNotify(null, 30, false);
            }
        });
        findViewById(R.id.btn_custom_heads_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建点击通知时发送的广播
                Intent intent = new Intent(MainActivity.this, Result2Activity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                //创建自定义顶部提醒视图
                Intent answerIntent = new Intent(MainActivity.this, ResultActivity.class);
                answerIntent.setAction(Intent.ACTION_VIEW);
                PendingIntent answerPendingIntent = PendingIntent.getActivity(MainActivity.this, 0, answerIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                Intent rejectIntent = new Intent(MainActivity.this, Result1Activity.class);
                answerIntent.setAction(Intent.ACTION_VIEW);
                PendingIntent rejectPendingIntent = PendingIntent.getActivity(MainActivity.this, 1, rejectIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                RemoteViews headsUpView = new RemoteViews(MainActivity.this.getPackageName(), R.layout.custom_heads_up_layout);
                headsUpView.setOnClickPendingIntent(R.id.iv_answer, answerPendingIntent);
                headsUpView.setOnClickPendingIntent(R.id.iv_reject, rejectPendingIntent);
                new Notify26(MainActivity.this).sendCustomHeadsUpViewNotify(pi, headsUpView);
            }
        });

        // 在NotifyServer中根据点击的Action,更改同一个Id的Notification内容与上述MediaStyle相同
        findViewById(R.id.btn_custom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建点击通知时发送的广播
                Intent intent = new Intent(MainActivity.this, Result2Activity.class);
                PendingIntent pi = PendingIntent.getActivity(MainActivity.this, 0, intent, 0);
                //创建各个按钮的点击响应广播
                Intent intentLove = new Intent(MainActivity.this, NotifyServer.class);
                intentLove.setAction(ACTION_CUSTOM_VIEW_OPTIONS_LOVE);
                PendingIntent piLove = PendingIntent.getService(MainActivity.this, 0, intentLove, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent intentPre = new Intent(MainActivity.this, NotifyServer.class);
                intentPre.setAction(ACTION_CUSTOM_VIEW_OPTIONS_PRE);
                PendingIntent piPre = PendingIntent.getService(MainActivity.this, 0, intentPre, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent intentPlayOrPause = new Intent(MainActivity.this, NotifyServer.class);
                intentPlayOrPause.setAction(ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE);
                PendingIntent piPlayOrPause = PendingIntent.getService(MainActivity.this, 0, intentPlayOrPause, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent intentNext = new Intent(MainActivity.this, NotifyServer.class);
                intentNext.setAction(ACTION_CUSTOM_VIEW_OPTIONS_NEXT);
                PendingIntent piNext = PendingIntent.getService(MainActivity.this, 0, intentNext, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent intentLyrics = new Intent(MainActivity.this, NotifyServer.class);
                intentLyrics.setAction(ACTION_CUSTOM_VIEW_OPTIONS_LYRICS);
                PendingIntent piLyrics = PendingIntent.getService(MainActivity.this, 0, intentLyrics, PendingIntent.FLAG_UPDATE_CURRENT);

                Intent intentCancel = new Intent(MainActivity.this, NotifyServer.class);
                intentCancel.setAction(ACTION_CUSTOM_VIEW_OPTIONS_CANCEL);
                PendingIntent piCancel = PendingIntent.getService(MainActivity.this, 0, intentCancel, PendingIntent.FLAG_UPDATE_CURRENT);
                //创建自定义小视图
                RemoteViews customView = new RemoteViews(MainActivity.this.getPackageName(), R.layout.custom_view_layout);
                customView.setImageViewBitmap(R.id.iv_content, BitmapFactory.decodeResource(getResources(),
                        R.mipmap.custom_view_picture_current));
                customView.setTextViewText(R.id.tv_title, "歌曲标题");
                customView.setTextViewText(R.id.tv_summery, "歌曲内容");
                customView.setImageViewBitmap(R.id.iv_play_or_pause, BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_pause));
                customView.setOnClickPendingIntent(R.id.iv_play_or_pause, piPlayOrPause);
                customView.setOnClickPendingIntent(R.id.iv_next, piNext);
                customView.setOnClickPendingIntent(R.id.iv_lyrics, piLyrics);
                customView.setOnClickPendingIntent(R.id.iv_cancel, piCancel);
                //创建自定义大视图
                RemoteViews customBigView = new RemoteViews(getPackageName(), R.layout.custom_big_view_layout);
                customBigView.setImageViewBitmap(R.id.iv_content_big, BitmapFactory.decodeResource(getResources(),
                        R.mipmap.custom_view_picture_current));
                customBigView.setTextViewText(R.id.tv_title_big, "歌曲标题");
                customBigView.setTextViewText(R.id.tv_summery_big, "歌曲内容");
                customBigView.setImageViewBitmap(R.id.iv_love_big, BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_loved));
                customBigView.setImageViewBitmap(R.id.iv_play_or_pause_big, BitmapFactory.decodeResource(getResources(),
                        R.mipmap.ic_pause));
                customBigView.setOnClickPendingIntent(R.id.iv_love_big, piLove);
                customBigView.setOnClickPendingIntent(R.id.iv_pre_big, piPre);
                customBigView.setOnClickPendingIntent(R.id.iv_play_or_pause_big, piPlayOrPause);
                customBigView.setOnClickPendingIntent(R.id.iv_next_big, piNext);
                customBigView.setOnClickPendingIntent(R.id.iv_lyrics_big, piLyrics);
                customBigView.setOnClickPendingIntent(R.id.iv_cancel_big, piCancel);

                new Notify26(MainActivity.this).sendCustomViewNotify(pi, customView, customBigView);
            }
        });
    }
}
