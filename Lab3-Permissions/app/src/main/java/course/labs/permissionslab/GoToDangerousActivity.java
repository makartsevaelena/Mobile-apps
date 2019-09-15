package course.labs.permissionslab;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class GoToDangerousActivity extends Activity {

    private static final String TAG = "Lab-Permissions";
    private static final String DANGEROUS_ACTIVITY_ACTION = "course.labs.permissions.DANGEROUS_ACTIVITY";
    private static final int REQUEST_PERMISSIONS = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_to_dangerous_activity);

        Button startDangerousActivityButton = (Button) findViewById(R.id.start_dangerous_activity_button);
        int permissionStatus = ContextCompat.checkSelfPermission(this, DANGEROUS_ACTIVITY_ACTION);
        if (permissionStatus == PackageManager.PERMISSION_GRANTED) {
            startDangerousActivityButton.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    startDangerousActivity();
                }
            });
        } else {
            ActivityCompat.requestPermissions(this, new String[]{DANGEROUS_ACTIVITY_ACTION},
                    REQUEST_PERMISSIONS);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startDangerousActivity();
                } else {
                    Log.i(TAG, "permission denied");
                }
                return;
        }
    }

    private void startDangerousActivity() {

        Log.i(TAG, "Entered startDangerousActivity()");
        Intent intent = new Intent();
        intent.setClassName("course.labs.dangerousapp", "course.labs.dangerousapp.DangerousActivity");
        startActivity(intent);
    }

}
