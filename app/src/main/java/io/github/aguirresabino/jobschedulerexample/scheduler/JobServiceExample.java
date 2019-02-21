package io.github.aguirresabino.jobschedulerexample.scheduler;

import android.annotation.TargetApi;
import android.app.job.JobParameters;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class JobServiceExample extends android.app.job.JobService {

    private final static String TAG = "JobScheduler";

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.i(TAG, "onStartJob( " + params.toString() + ")");
        Log.i(TAG, Thread.currentThread().getName());
        mJobHandler.sendMessage(Message.obtain(mJobHandler, 1, params));
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.i(TAG, "onStopJob( " + params.toString() + ")");
        Log.i(TAG, Thread.currentThread().getName());
        mJobHandler.removeMessages(1);
        return true;
    }

    private Handler mJobHandler = new Handler( new Handler.Callback() {

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        public boolean handleMessage( Message msg ) {
            Toast.makeText( getApplicationContext(),
                    "JobServiceExample task running", Toast.LENGTH_SHORT )
                    .show();
            jobFinished( (JobParameters) msg.obj, false );
            return true;
        }

    } );
}
