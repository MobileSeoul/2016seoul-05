package seoul.emergency.bbibbo.questionnaire;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by Min-Soo on 2016-08-25.
 */
public class LockReceiver extends BroadcastReceiver {

    public void onReceive(Context context, Intent intent){
        String action = intent.getAction();
        Log.d("###","1111111");
        if(action.equals(Intent.ACTION_SCREEN_OFF)){
            Log.d("###","222");

   //         Button btn_test = (Button)((Activity)context).findViewById(R.id.btn_test);
   //         btn_test.setVisibility(View.VISIBLE);

            Intent intentStart = new Intent(context,DiseaseNotifier.class);
            intentStart.putExtra("check", 0);

            intentStart.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentStart);
        }
    }
}
