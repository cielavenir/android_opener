package com.cielavenir.playstoreopener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Intent intent = new Intent();
		intent.setClassName("com.android.vending", "com.android.vending.AssetBrowserActivity");
        startActivity(intent);

        finish();
    }
}