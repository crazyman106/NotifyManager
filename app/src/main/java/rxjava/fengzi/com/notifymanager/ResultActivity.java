package rxjava.fengzi.com.notifymanager;

import android.app.RemoteInput;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import rxjava.fengzi.com.notifymanager.notify.NotifyConstances;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_big_view_layout);
        Log.d("task_id:", getTaskId() + "");
        // 测试RemoteInput Notification
        String action = getIntent().getAction();
        if (NotifyConstances.ACTION_REMOTE_INPUT.equals(action)) {
            Bundle bundle = RemoteInput.getResultsFromIntent(getIntent());
            Toast.makeText(this, bundle.getString(NotifyConstances.REMOTE_INPUT_RESULT_KEY), Toast.LENGTH_LONG).show();
        }
    }
}
