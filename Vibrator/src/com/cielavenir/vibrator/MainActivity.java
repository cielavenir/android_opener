package com.cielavenir.vibrator;

import android.os.Bundle;
import android.os.Process;
import android.os.Vibrator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.KeyEvent;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		final Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
		final long[] pattern = {500, 1000};
		vibrator.vibrate(pattern, 0);
		BroadcastReceiver vibrateReceiver = new BroadcastReceiver(){
			@Override
			public void onReceive(Context context, Intent intent){
				if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
					vibrator.vibrate(pattern, 0);
				}
			}
		};

		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		registerReceiver(vibrateReceiver , filter);
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
*/
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
		//Log.d("Vibrator",String.valueOf(keyCode));
		if (keyCode == KeyEvent.KEYCODE_BACK){
			//this.finish();
			Process.killProcess(Process.myPid()); //process must be stopped, not suspended
			return true;
		}
		return false;
	}
}
