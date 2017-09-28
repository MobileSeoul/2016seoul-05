package seoul.emergency.bbibbo.questionnaire;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

public class ScreenService extends Service {

	private LockReceiver mReceiver = null;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		mReceiver = new LockReceiver();
		IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
		registerReceiver(mReceiver, filter);
		//Log.d("###","3333");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId){
		super.onStartCommand(intent, flags, startId);
		
		if(intent != null){
			if(intent.getAction()==null){
				if(mReceiver==null){
					mReceiver = new LockReceiver();
					IntentFilter filter = new IntentFilter(Intent.ACTION_SCREEN_OFF);
					registerReceiver(mReceiver, filter);
				}
			}
		}
		return START_REDELIVER_INTENT;
	}
	
	@Override
	public void onDestroy(){		 	
		super.onDestroy();
		
		if(mReceiver != null){
			unregisterReceiver(mReceiver);
		}
	}
}