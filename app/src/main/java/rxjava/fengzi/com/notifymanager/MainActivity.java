package rxjava.fengzi.com.notifymanager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import rxjava.fengzi.com.notifymanager.notify.ui.INotifyParams;
import rxjava.fengzi.com.notifymanager.notify.ui.Notify26;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("task_id:", getTaskId() + "");
        // 测试
        findViewById(R.id.btn_simple).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
    }
}
