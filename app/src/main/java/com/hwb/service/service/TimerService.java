package com.hwb.service.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hwb.service.receiver.AlarmReceiver;

import java.util.Date;

/**
 * Created by Administrator on 2016/07/19.
 */
public class TimerService extends Service
{
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{

		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				Log.i("zx","executed at:"+new Date().toString());
				Log.i("zx","系统开机距今的时间:"+ SystemClock.elapsedRealtime());//获取系统开机距离现在的时间ms
			}
		}).start();

		//创建一个定时服务
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		int anHour = 60*60*1000;//一小时的ms数
		long tiggerAtTime = SystemClock.elapsedRealtime()+30*1000;//设定任务在10s后执行
		Intent intent1 = new Intent(this, AlarmReceiver.class);
		PendingIntent pendingIntent = PendingIntent.getBroadcast(this,0,intent1,0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP,tiggerAtTime,pendingIntent);

		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy()
	{
		Log.i("zx","TimerService:onDestroy");
		super.onDestroy();
	}


}
