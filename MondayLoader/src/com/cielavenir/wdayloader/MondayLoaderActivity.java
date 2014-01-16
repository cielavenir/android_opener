package com.cielavenir.wdayloader;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;

import java.util.Calendar;

import com.cielavenir.wdayloader.R;

public class MondayLoaderActivity extends Activity{
	private final String KEY="308201a13082010aa00302010202044f130681300d06092a864886f70d01010505003014311230100603550403130963656c65737469616c3020170d3132303131353137303135335a180f33303131303531383137303135335a3014311230100603550403130963656c65737469616c30819f300d06092a864886f70d010101050003818d00308189028181009ef4049f45049228993d432f59668e5d0378b238366005c9aa67b8892e9f8d516579b233e35c29ff8439841307eb84dc5c5049d66314ba15c8e7c9128d2469c857b5a4137218c9b64d8a888094b8585a849585b187a9a4f9084ac834e6a3210e67fe57a66cac02dc09604c55b82cb1da06dfbed8dd19c4621d6a62b4e8acafbd0203010001300d06092a864886f70d01010505000381810001e5c3c3c43e21c6e5ad81673e29868d0043a0704082c36e65f92d1f229600884bf15dfa1d900626c53b7d98081fb3d8aea4bd4f65f8d2856fe3c0f3c0af8c67a1f3fd2578baab2013ed1bf31dca72fc77f753e57e186c62a1b5005352fda595c55d7b63b17fee939e2a20b44218efb6299796e7e76452fd2ac07ec48bbd41f5";

	private final String[] wdays={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	private final int REPEAT_INTERVAL = 200;
	private final int MESSAGE = 0xdeadbeef;
	private Calendar cal; //should be put here to shorten process time
	private int wdayToWait,t; //lol

	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg){
			//if(msg.what==MESSAGE){
				cal=Calendar.getInstance();
				if(t==-1)((ProgressBar)findViewById(R.id.progressBar1)).setMax(86400);
				t=cal.get(Calendar.HOUR_OF_DAY)*3600+cal.get(Calendar.MINUTE)*60+cal.get(Calendar.SECOND);
				if(t<2){
					((TextView)findViewById(R.id.textView1)).setText("Loaded "+wdays[wdayToWait]+".apk 100%!");
					((TextView)findViewById(R.id.textView2)).setText("Remaining 00:00:00");
					((ProgressBar)findViewById(R.id.progressBar1)).setProgress(86400);
				}else{
					((TextView)findViewById(R.id.textView1)).setText("Loading "+wdays[wdayToWait]+".apk "+String.format("%d%%...",t*100/86400));
					((TextView)findViewById(R.id.textView2)).setText("Remaining "+String.format("%02d:%02d:%02d",(86400-t)/3600,(86400-t)/60%60,(86400-t)%60));
					((ProgressBar)findViewById(R.id.progressBar1)).setProgress(t);
					handler.sendMessageDelayed(obtainMessage(), REPEAT_INTERVAL);
				}
			//}
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		PackageManager pm = getPackageManager();
		try{
			PackageInfo packageInfo = pm.getPackageInfo(getPackageName(),PackageManager.GET_SIGNATURES);
			for(int i=0; i<packageInfo.signatures.length; i++){
				Signature signature = packageInfo.signatures[i];
				//Log.d("SIGNATURE","Signature: "+signature.toCharsString());
				// //((TextView)findViewById(R.id.textViewKey)).setText("");//signature.toCharsString());
if(false){
				if(KEY.equals(signature.toCharsString())){
					//Log.d("SIGNATURE", "matched");
				}else{
					//Log.d("SIGNATURE", "unmatched");
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
					alertDialogBuilder.setTitle("Signature not match");
					alertDialogBuilder.setMessage("Package was resigned by unauthorized person");
					alertDialogBuilder.setNeutralButton("ok",
							new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which){
									finish();
								}
							});
					AlertDialog alertDialog = alertDialogBuilder.create();
					alertDialog.show();
					return;
				}
}
			}
		}catch(Exception e){
			e.printStackTrace();
		}

		setContentView(R.layout.main);
		Message message = new Message();
		message.what = MESSAGE;

		int wday=Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1; //0=Sunday, 6=Saturday
		wdayToWait=(wday+1)%7;
		t=-1;
		handler.sendMessageDelayed(message, REPEAT_INTERVAL);
    }
}
