package com.cielavenir.browseropener;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

		Intent intent = new Intent();
		intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        startActivity(intent);

        finish();
    }
}