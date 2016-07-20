package com.hwb.service.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.hwb.service.R;
import com.hwb.service.activity.MainActivity;

/**
 * Created by Administrator on 2016/07/19.
 */
public class MyService extends Service
{
	public class DownLoadBinder extends Binder
	{
		//服务中的方法
		public void startDownLoad()
		{
			Log.i("zx","Service中的startDownLoad()");
		}

		public int getProgress()
		{
			Log.i("zx","Service中的startDownLoad()");
			return 0;
		}
	}

	DownLoadBinder downLoadBinder = new DownLoadBinder();
	@Nullable
	@Override
	public IBinder onBind(Intent intent)
	{
		Log.i("zx","onBind");
		return downLoadBinder;
	}

	//服务创建的时候调用
	@Override
	public void onCreate()
	{
		super.onCreate();
		Log.i("zx","onCreate");
		//开启前台服务,使用通知
		NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		Notification.Builder builder = new Notification.Builder(MyService.this)
											   .setContentTitle("Notification Comes")
											   .setContentText("Notification Come")
											   .setSmallIcon(R.mipmap.ic_launcher);
		Intent intent = new Intent(MyService.this,MainActivity.class);
		PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this,0,intent,PendingIntent.FLAG_NO_CREATE);
		if(android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN)
		{
			Notification notification = builder.setContentIntent(pendingIntent).build();
//			manager.notify(1,notification);
			startForeground(1,notification);//后台运行的服务被强行kill掉，有可能是系统回收内存的一种机制，
											// 要想避免这种情况可以通过startForeground让服务前台运行
											// ,和notify的区别就是服务关闭则通知消失
		}

	}

	//服务启动的时候调用:如果服务没有被onDestroy那么再执行开启时候就从onStartCommand开始执行不执行onCreate
	@Override
	public int onStartCommand(Intent intent, int flags, int startId)
	{
		Log.i("zx","onStartCommand");
		return super.onStartCommand(intent, flags, startId);
	}

	//服务销毁的时候调用
	@Override
	public void onDestroy()
	{
		Log.i("zx","onDestroy");
		super.onDestroy();
	}

}
