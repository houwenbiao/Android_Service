package com.hwb.service.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hwb.service.R;
import com.hwb.service.service.MyIntentService;
import com.hwb.service.service.MyService;
import com.hwb.service.service.TimerService;

public class MainActivity extends AppCompatActivity
{

	private Button btn_startService, btn_closeService,btn_bindService
			,btn_unbindService,btn_startIntentService,btn_startTimerService,btn_stopTimerService;
	private MyService.DownLoadBinder downLoadBinder;//内部类实例化
	private ServiceConnection connection = new ServiceConnection()
	{
		//活动与服务绑定的时候调用
		@Override
		public void onServiceConnected(ComponentName componentName, IBinder iBinder)
		{
			downLoadBinder = (MyService.DownLoadBinder) iBinder;
			downLoadBinder.startDownLoad();
			downLoadBinder.getProgress();
		}
		//活动与服务接触绑定的时候调用
		@Override
		public void onServiceDisconnected(ComponentName componentName)
		{

		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btn_closeService = (Button) findViewById(R.id.btn_closeService);
		btn_startService = (Button) findViewById(R.id.btn_startService);
		btn_bindService = (Button) findViewById(R.id.btn_binderService);
		btn_unbindService = (Button) findViewById(R.id.btn_unbinderService);
		btn_startIntentService = (Button) findViewById(R.id.btn_startIntentService);
		btn_startTimerService = (Button) findViewById(R.id.btn_startTimerService);
		btn_stopTimerService = (Button) findViewById(R.id.btn_stopTimerService);

		btn_startService.setOnClickListener(new MyClickListener());
		btn_closeService.setOnClickListener(new MyClickListener());
		btn_bindService.setOnClickListener(new MyClickListener());
		btn_unbindService.setOnClickListener(new MyClickListener());
		btn_startIntentService.setOnClickListener(new MyClickListener());
		btn_startTimerService.setOnClickListener(new MyClickListener());
		btn_stopTimerService.setOnClickListener(new MyClickListener());
	}

	class MyClickListener implements View.OnClickListener
	{
		@Override
		public void onClick(View view)
		{
			switch (view.getId())
			{
				case R.id.btn_startService:
					Intent startServiceIntent = new Intent(MainActivity.this, MyService.class);
					startService(startServiceIntent);//启动服务
					Toast.makeText(MainActivity.this,"startService",Toast.LENGTH_SHORT).show();
					break;
				case R.id.btn_closeService:
					Intent stopServiceIntent = new Intent(MainActivity.this, MyService.class);
					stopService(stopServiceIntent);//启动服务
					Toast.makeText(MainActivity.this,"stopService",Toast.LENGTH_SHORT).show();
					break;
				case R.id.btn_binderService:
					Intent bindIntent = new Intent(MainActivity.this,MyService.class);
					bindService(bindIntent,connection,BIND_AUTO_CREATE);
					Toast.makeText(MainActivity.this,"binderService",Toast.LENGTH_SHORT).show();
					break;
				case R.id.btn_unbinderService:
					unbindService(connection);
					Toast.makeText(MainActivity.this,"unbinderService",Toast.LENGTH_SHORT).show();
					break;
				case R.id.btn_startIntentService:
					Log.i("zx","主Thread id:"+Thread.currentThread().getId());
					Intent intent = new Intent(MainActivity.this, MyIntentService.class);
					startService(intent);
					break;
				case R.id.btn_startTimerService:
					Intent intent1 = new Intent(MainActivity.this, TimerService.class);
					startService(intent1);
					break;
				case R.id.btn_stopTimerService:
					Intent intent2 = new Intent(MainActivity.this, TimerService.class);
					stopService(intent2);
					break;

				default:
					break;
			}
		}
	}
}
