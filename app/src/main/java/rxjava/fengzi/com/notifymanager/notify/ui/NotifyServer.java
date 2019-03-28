package rxjava.fengzi.com.notifymanager.notify.ui;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import rxjava.fengzi.com.notifymanager.R;
import rxjava.fengzi.com.notifymanager.notify.NotifyConstances;

import static rxjava.fengzi.com.notifymanager.notify.NotifyConstances.*;

public class NotifyServer extends Service {
    private NotificationManager mNotificationManager;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.getAction() != null) {
            switch (intent.getAction()) {
                case NotifyConstances.ACTION_MEDIA_STYLE:
                    operateMedia(intent);
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_LOVE:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_PRE:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_PLAY_OR_PAUSE:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_NEXT:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_LYRICS:
                    break;
                case ACTION_CUSTOM_VIEW_OPTIONS_CANCEL:
                    break;
            }
        }

        return START_STICKY;
    }

    private void operateMedia(Intent extras) {
        String options = extras.getStringExtra(NotifyConstances.EXTRA_OPTIONS);
        if (options == null) return;
        switch (options) {
            case NotifyConstances.EXTRA_MEDIA_STYLE_PLAY:
                updateMedisNotificationStatus();
                mMedisIsPlaying = !mMedisIsPlaying;
                break;
            case NotifyConstances.EXTRA_MEDIA_STYLE_PAUSE:
                updateMedisNotificationStatus();
                mMedisIsPlaying = !mMedisIsPlaying;
                break;
            case NotifyConstances.EXTRA_MEDIA_STYLE_NEXT:
                break;
            case NotifyConstances.EXTRA_MEDIA_STYLE_DELETE:
                mNotificationManager.cancel(1001);
                break;
            default:
        }
    }

    private boolean mMedisIsPlaying = true;

    private void updateMedisNotificationStatus() {
        Intent playOrPauseIntent = new Intent(this, NotifyServer.class);
        playOrPauseIntent.setAction(ACTION_MEDIA_STYLE);
        playOrPauseIntent.putExtra(EXTRA_OPTIONS, mMedisIsPlaying ? NotifyConstances.EXTRA_MEDIA_STYLE_PAUSE : NotifyConstances.EXTRA_MEDIA_STYLE_PLAY);
        PendingIntent playOrPausePendingIntent = PendingIntent.getService(this, 0, playOrPauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action playOrPauseAction = new NotificationCompat.Action.Builder(
                mMedisIsPlaying ? R.mipmap.ic_pause : R.mipmap.ic_play, mMedisIsPlaying ? "PAUSE" : "PLAY", playOrPausePendingIntent).build();

        Intent nextIntent = new Intent(this, NotifyServer.class);
        nextIntent.setAction(ACTION_MEDIA_STYLE);
        nextIntent.putExtra(EXTRA_OPTIONS, EXTRA_MEDIA_STYLE_NEXT);
        PendingIntent nextPendingIntent = PendingIntent.getService(this, 1, nextIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action nextAction = new NotificationCompat.Action.Builder(
                R.mipmap.ic_next, "Next", nextPendingIntent).build();

        Intent deleteIntent = new Intent(this, NotifyServer.class);
        deleteIntent.setAction(ACTION_MEDIA_STYLE);
        deleteIntent.putExtra(EXTRA_OPTIONS, EXTRA_MEDIA_STYLE_DELETE);
        PendingIntent deletePendingIntent = PendingIntent.getService(this, 2, deleteIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action deleteAction = new NotificationCompat.Action.Builder(
                R.mipmap.ic_delete, "Delete", deletePendingIntent).build();

        android.support.v4.media.app.NotificationCompat.MediaStyle style = new android.support.v4.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(0, 1, 2);
        new Notify26(this).sendMediaStyleNotify(null, style, new NotificationCompat.Action[]{playOrPauseAction, nextAction, deleteAction});
    }
}
