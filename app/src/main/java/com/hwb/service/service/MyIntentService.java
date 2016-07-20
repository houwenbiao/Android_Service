package com.hwb.service.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Administrator on 2016/07/19.
 */
public class MyIntentService extends IntentService
{
	public MyIntentService()
	{
		super("MyIntentService");
	}

	//次方法在子线程中执行，可以处理一些耗时的操作
	@Override
	protected void onHandleIntent(Intent intent)
	{
		for (int i = 0; i < 100; i++)
		{
			Log.i("zx","当前Thread id :"+Thread.currentThread().getId()+"  i:"+i);
		}
	}

	@Override
	public void onDestroy()
	{
		Log.i("zx","onDestroy");
		super.onDestroy();
	}
}
