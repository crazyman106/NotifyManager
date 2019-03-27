package rxjava.fengzi.com.notifymanager.notify.bean;

import android.app.PendingIntent;

public class BaseNotifyType {
    private int mSmallIcon;
    private CharSequence mTitle;
    private CharSequence mContent;
    private boolean mShowWhen = true;
    private PendingIntent mContentIntent;
    private boolean mAutoCancel = false;
}
