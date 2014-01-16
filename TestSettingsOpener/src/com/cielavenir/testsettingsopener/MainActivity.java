package com.cielavenir.testsettingsopener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Intent intent = new Intent();
		intent.setClassName("com.android.settings", "com.android.settings.TestingSettings");
		//intent.setClassName("com.j4n87.tweaks","com.j4n87.tweaks.Main");
        startActivity(intent);

        finish();
    }
}