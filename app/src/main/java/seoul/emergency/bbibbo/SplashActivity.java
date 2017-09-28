package seoul.emergency.bbibbo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import seoul.emergency.bbibbo.map.PharmacyMapActivity;
import seoul.emergency.bbibbo.userguide.UserguideActivity;

public class SplashActivity extends AppCompatActivity {
    private final String baseURL = "";
    Handler handler;
    LocationManager locationManager;
    static Boolean userGuideFirst;
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private static String[] PERMISSIONS_ALL = {
            //Manifest.permission_group.STORAGE,Manifest.permission_group.LOCATION,Manifest.permission_group.PHONE
            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CALL_PHONE,Manifest.permission.READ_PHONE_STATE,Manifest.permission.PROCESS_OUTGOING_CALLS,Manifest.permission.SEND_SMS,Manifest.permission.CALL_PHONE
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);


        ImageView logo1 = (ImageView) findViewById(R.id.SplashLogo);
        ImageView logo2 = (ImageView) findViewById(R.id.SplashLogo2);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) logo1.getLayoutParams();

        params.width = (int)(metrics.widthPixels * 155.0 / 360) ;
        params.height = (int)(metrics.heightPixels * 185.0 / 640);
        params.topMargin = (int)(metrics.heightPixels * 140.0 / 640);

        params = (RelativeLayout.LayoutParams) logo2.getLayoutParams();
        params.topMargin = (int)(metrics.heightPixels * (140+165+63) / 640.0);
        params.width = (int)(metrics.widthPixels * 140.0 / 360) ;
        params.height = (int)(metrics.heightPixels * 22.0 / 640);


        ApplicationController applicationController = ApplicationController.getInstance();
        Log.d("####", "111");
        applicationController.buildNetworkService(baseURL);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);


        Log.d("####", "333");
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {

                    SharedPreferences mPref = getSharedPreferences("isFirst", Activity.MODE_PRIVATE);

                    Boolean bFirst = mPref.getBoolean("isFirst", false);
                    if(bFirst == false)
                    {
                        Log.d("version", "first");
                        SharedPreferences.Editor editor = mPref.edit();
                        editor.putBoolean("isFirst", true);
                        editor.commit();
                        Intent intent = new Intent(getApplicationContext(),UserguideActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    if(bFirst == true)
                    {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }




            }
        };
        // 권한을 획득하기전에 현재 Acivity에서 지정된 권한을 사용할 수 있는지 여부 체크

        if (ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
            // 권한 획득에 대한 설명 보여주기

            if (ActivityCompat.shouldShowRequestPermissionRationale(SplashActivity.this,Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 사용자에게 권한 획득에 대한 설명을 보여준 후 권한 요청을 수행

                ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS_ALL, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            } else {
                // 권한 획득의 필요성을 설명할 필요가 없을 때는 아래 코드를

                //수행해서 권한 획득 여부를 요청한다.
                ActivityCompat.requestPermissions(SplashActivity.this, PERMISSIONS_ALL, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            }
        }


        //GPS on/off 확인
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            alertCheckGPS();
        }else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)&&ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED ){

                handler.sendEmptyMessageDelayed(0, 3000);//GPS가 켜져있으면 넘어감

        }

    }

    @Override
    protected void onRestart(){
        super.onRestart();
        if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            alertCheckGPS();
        }else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            handler.sendEmptyMessageDelayed(0, 3000);//GPS가 켜져있으면 넘어감
        }
    }

    //GPS가 안켜져있을 시에 설정 대화상자
    private void alertCheckGPS(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Your GPS is disabled! Would you like to enable it?")
                .setCancelable(false)
                .setTitle("GPS")
                .setPositiveButton("Enable GPS",new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        moveConfigGPS();

                    }
                })
                .setNegativeButton("Do nothing", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Toast.makeText(SplashActivity.this, "Turn on the GPS or it will terminated.", Toast.LENGTH_LONG).show();

                        moveTaskToBack(true);
                        finish();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    //GPS설정화면으로 이동
    private void moveConfigGPS(){
        Intent gpsOptionsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(gpsOptionsIntent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {

                //권한 획득이 거부되면 결과 배열은 비어있게 됨
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //권한 획득이 허용되면 수행해야 할 작업이 표시됨
                    //일반적으로 작업을 처리할 메서드를 호출
                    if(!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        alertCheckGPS();
                    }else if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                        handler.sendEmptyMessageDelayed(0, 2000);//GPS가 켜져있으면 넘어감
                    }
                }
                else {
                    //권한 획득이 거부되면 수행해야 할 적업이 표시됨
                    //일반적으로 작업을 처리할 메서드를 호출
                    Toast.makeText(SplashActivity.this, "Give the permission or the app will be terminated.", Toast.LENGTH_LONG).show();
                    moveTaskToBack(true);
                    finish();
                }

                return;
            }
        }
    }




}