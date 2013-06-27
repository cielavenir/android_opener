package com.cielavenir.aumarketmyapps;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

public class MainActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);

        Uri uri = Uri.parse("auonemkt://myapps_list");
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

        finish();
    }
}