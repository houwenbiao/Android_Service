package com.hwb.service.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.hwb.service.service.TimerService;

/**
 * Created by Administrator on 2016/07/19.
 */
public class AlarmReceiver extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent)
	{
		Intent intent1 = new Intent(context, TimerService.class);
		context.startService(intent1);
	}
}
