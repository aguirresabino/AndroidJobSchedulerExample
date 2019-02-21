package io.github.aguirresabino.jobschedulerexample;

import android.annotation.TargetApi;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.ComponentName;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import io.github.aguirresabino.jobschedulerexample.scheduler.JobServiceExample;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "JobScheduler";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Criando O JobInfo
        ComponentName componentName = new ComponentName(getPackageName(), JobServiceExample.class.getName());
        JobInfo jobInfo = new JobInfo.Builder(1, componentName)
                .setBackoffCriteria(4000, JobInfo.BACKOFF_POLICY_LINEAR)
                //.setMinimumLatency(2000)
                //.setOverrideDeadline(2000)
                .setPersisted(true)
                .setPeriodic(2000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(false)
                .setRequiresDeviceIdle(false)
                .build();

        Log.i(TAG, jobInfo.toString());

        //Obtendo instância do serviço do sistema JOB_SCHEDULER_SERVICE
        JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);

        int resultCode = jobScheduler.schedule(jobInfo);

        if(resultCode == JobScheduler.RESULT_SUCCESS) Log.i(TAG, "JOB SCHEDULED");
        else Log.i(TAG, "JOB SCHEDULING FAILED");
    }
}
