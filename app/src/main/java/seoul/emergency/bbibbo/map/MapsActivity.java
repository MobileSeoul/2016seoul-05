package seoul.emergency.bbibbo.map;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;


import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;

import android.content.pm.PackageManager;
import android.location.Location;

import android.location.LocationListener;

import android.location.LocationManager;

import android.os.Bundle;

import android.support.v4.app.ActivityCompat;
//import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdateFactory;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;

import com.google.android.gms.maps.model.LatLng;

import com.google.android.gms.maps.model.Marker;

import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seoul.emergency.bbibbo.ApplicationController;
import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.hospital.HospitalFragment;
import seoul.emergency.bbibbo.hospital.HospitalInfoFragment;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Hospital;
import seoul.emergency.bbibbo.notice.NoticeActivity;
import seoul.emergency.bbibbo.pharmacy.PharmacyFragment;
import seoul.emergency.bbibbo.questionnaire.QuestionnaireActivity;
import seoul.emergency.bbibbo.questionnaire.ScreenService;
import seoul.emergency.bbibbo.userguide.UserguideActivity;
import seoul.emergency.bbibbo.youtube.YoutubeActivity;


public class MapsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    public  static MapsActivity mapsActivity;
    GoogleMap mGoogleMap;

    Marker mMarkerStart;

    Marker mMarkerMan;
    BroadcastReceiver receiver;


    boolean mFirstLoc = true;

    LocationManager mLocMgr;

    Location location;
    static final LatLng SEOUL = new LatLng(37.56, 126.97);

    HospitalFragment hospitalFragment;
    static final int HOSPITAL = 0;
    //동주
    ArrayList<Hospital> hospital_ArrayList;
    NetworkService networkService;

    //마커 변수 설정
    //동주
    MarkerOptions options;
    Marker prevMarker;
    SupportMapFragment mapFragment;
    float mylat, mylong;
    Hospital hospital;
    ArrayList<Marker> markerArrayList = new ArrayList<Marker>();
    ImageButton newPosition;
    Fragment fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        try {
            CheckTypeTasks checkTypeTasks = new CheckTypeTasks();
            checkTypeTasks.execute();
        }catch(Exception e){
            Toast.makeText(this, "Don't click while loading..",Toast.LENGTH_SHORT).show();
        }

        mapsActivity = MapsActivity.this;

        setContentView(R.layout.activity_maps);



        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);


        final ImageView button4 = (ImageView) findViewById(R.id.btn_home);
        final ImageView button5 = (ImageView) findViewById(R.id.btn_call1);
        final ImageView button6 = (ImageView) findViewById(R.id.btn_call2);
        final ImageView button7 = (ImageView) findViewById(R.id.btn_m);
        ImageView icon_home = (ImageView) findViewById(R.id.icon_home);
        TextView text_home = (TextView) findViewById(R.id.text_home);
        ImageView icon_call1 = (ImageView) findViewById(R.id.icon_call1);
        TextView text_call1 = (TextView) findViewById(R.id.text_call1);
        ImageView icon_call2 = (ImageView) findViewById(R.id.icon_call2);
        TextView text_call2 = (TextView) findViewById(R.id.text_call2);
        ImageView icon_m = (ImageView) findViewById(R.id.icon_m);
        TextView text_m = (TextView) findViewById(R.id.text_m);

        Typeface helL= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Light.ttf");
        final Typeface helR = Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica.ttf");

        Typeface helB= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Bold.ttf");
        TextView appbar = (TextView)findViewById(R.id.appbar_hopital_text);
        appbar.setTypeface(helL);

        text_call1.setTypeface(helL);
        text_call2.setTypeface(helL);
        text_home.setTypeface(helL);
        text_m.setTypeface(helL);


        //params4 5 6 7은는 home call1 call2 m
        RelativeLayout.LayoutParams params4 = (RelativeLayout.LayoutParams) icon_home.getLayoutParams();

        params4.width = (int)(metrics.widthPixels * 30.0 / 360) ;
        params4.height = (int)(metrics.heightPixels * 30.0 / 640);
        params4.topMargin = (int)(metrics.heightPixels * 10.0 / 640);

        params4 = (RelativeLayout.LayoutParams) text_home.getLayoutParams();
        params4.topMargin = (int)(metrics.heightPixels * 47.0 / 640);

        RelativeLayout.LayoutParams params5 = (RelativeLayout.LayoutParams) icon_call1.getLayoutParams();

        params5.width = (int)(metrics.widthPixels * 30.0 / 360) ;
        params5.height = (int)(metrics.heightPixels * 30.0 / 640);
        params5.topMargin = (int)(metrics.heightPixels * 10.0 / 640);

        params5 = (RelativeLayout.LayoutParams) text_call1.getLayoutParams();
        params5.topMargin = (int)(metrics.heightPixels * 47.0 / 640);

        RelativeLayout.LayoutParams params6 = (RelativeLayout.LayoutParams) icon_call2.getLayoutParams();

        params6.width = (int)(metrics.widthPixels * 30.0 / 360) ;
        params6.height = (int)(metrics.heightPixels * 30.0 / 640);
        params6.topMargin = (int)(metrics.heightPixels * 10.0 / 640);

        params6 = (RelativeLayout.LayoutParams) text_call2.getLayoutParams();
        params6.topMargin = (int)(metrics.heightPixels * 47.0 / 640);

        RelativeLayout.LayoutParams params7 = (RelativeLayout.LayoutParams) icon_m.getLayoutParams();

        params7.width = (int)(metrics.widthPixels * 33.0 / 360) ;
        params7.height = (int)(metrics.heightPixels * 30.0 / 640);
        params7.topMargin = (int)(metrics.heightPixels * 10.0 / 640);

        params7 = (RelativeLayout.LayoutParams) text_m.getLayoutParams();
        params7.topMargin = (int)(metrics.heightPixels * 47.0 / 640);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mLocMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        newPosition = (ImageButton)findViewById(R.id.new_position_hospital);
        newPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //프로그래스 다이얼로그 띄우기 (에러 수정 해야함)
                //CheckTypesTask checkTypesTask = new CheckTypesTask();
                //checkTypesTask.execute();
                Intent intent = new Intent(MapsActivity.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //처음 내 시작 위치 가져오기
        try {
            MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
                @Override
                public void gotLocation(Location location) {
                    String msg = "lon: " + location.getLongitude() +
                            "lat: " + location.getLatitude();
                    //gps켜야 initMap으로 넘어감
                    //initMap -> fragment 생성 되게 설정
                    if (initMap(location)){
                        Bundle bundle = new Bundle();
                        Log.d("@@", "" + mylat);
                        //동주
                        //PharmacyFragment로 위도 경도 값 보내기
                        bundle.putFloat("mylatitude1", mylat);
                        bundle.putFloat("mylongitude1", mylong);
                        //리스트뷰 프래그먼트
                        hospitalFragment = new HospitalFragment();
                        hospitalFragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        if(!isFinishing()) {
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.hospitalFragment_place, hospitalFragment, "second");


                            fragmentTransaction.commitAllowingStateLoss();
                        }
                    }

                }
            };

            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(getApplicationContext(), locationResult);
        }catch (Exception e){
            new AlertDialog.Builder(MapsActivity.this)
                    .setMessage("Please try again")
                    .setNegativeButton("cancle",null).show();
        }


        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(MapsActivity.this);

        button4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button4.setBackgroundColor(Color.parseColor("#22000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                    button4.setBackgroundColor(Color.parseColor("#00000000"));
                }
                return true;
            }
        });
        button5.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button5.setBackgroundColor(Color.parseColor("#22000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:119"));
                    startActivity(intent);
                    button5.setBackgroundColor(Color.parseColor("#00000000"));
                }
                return true;
            }
        });
        button6.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button6.setBackgroundColor(Color.parseColor("#22000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1345"));
                    startActivity(intent);
                    button6.setBackgroundColor(Color.parseColor("#00000000"));
                }
                return true;
            }
        });
        button7.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button7.setBackgroundColor(Color.parseColor("#22000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {


                    button7.setBackgroundColor(Color.parseColor("#00000000"));

                    Intent sms = new Intent(Intent.ACTION_VIEW);
                    sms.putExtra("address", "119");
                    sms.putExtra("sms_body", "Please help, I am in danger");
                    sms.setType("vnd.android-dir/mms-sms");
                    startActivity(sms);
                }
                return true;
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);


        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {


            }

            @Override
            public void onDrawerOpened(View drawerView) {

                Switch switch1 = (Switch) findViewById(R.id.switch_lock);

                if(isServiceRunning("seoul.emergency.bbibbo.questionnaire.ScreenService") == true)
                    switch1.setChecked(true);
                else
                    switch1.setChecked(false);

                switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if(isChecked){
                            Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                            startService(intent);
                        }
                        else{
                            Intent intent = new Intent(getApplicationContext(), ScreenService.class);
                            stopService(intent);
                        }
                    }
                });

                final Button btn_drawer1 = (Button)findViewById(R.id.btn_drawer1);
                final Button btn_drawer2 = (Button)findViewById(R.id.btn_drawer2);
                final Button btn_drawer3 = (Button)findViewById(R.id.btn_drawer3);
                final Button btn_drawer4 = (Button)findViewById(R.id.btn_drawer4);

                btn_drawer1.setTypeface(helR);
                btn_drawer2.setTypeface(helR);
                btn_drawer3.setTypeface(helR);
                btn_drawer4.setTypeface(helR);

                btn_drawer1.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer1.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {

                            Intent intent = new Intent(getApplicationContext(), QuestionnaireActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer1.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });


                btn_drawer2.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer2.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer2.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });
                btn_drawer3.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer3.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer3.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });
                btn_drawer4.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer4.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), UserguideActivity.class);
                            startActivity(intent);
                            finish();
                            btn_drawer4.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    public Boolean isServiceRunning(String serviceName) {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {

            if (serviceName.equals(runningServiceInfo.service.getClassName())) {
                return true;
            }
        }
        return false;
    }



    LocationListener mLocListener = new LocationListener() {

        public void onLocationChanged(Location location) {

            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

//            if (mFirstLoc) {
//
//                mFirstLoc = false;
//                if(mMarkerStart==null)
//                    Toast.makeText(MapsActivity.this,"start is null",Toast.LENGTH_SHORT).show();
//                else
//                    mMarkerStart.setPosition(position);
//
//            }

            if(mMarkerMan==null)
                Toast.makeText(MapsActivity.this,"Press the new position button",Toast.LENGTH_SHORT).show();
            else {
                mMarkerMan.setPosition(position);
                //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }



        }


        public void onProviderDisabled(String provider) {
            new AlertDialog.Builder(MapsActivity.this)
                    .setMessage("GPS is turned off.\n" + " On 'Location service’please turn on ‘Google Location service'")
                    .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // 설정 창을 띄운다
                            Intent intent = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);

                            startActivity(intent);
                        }

                    })
                    .setNegativeButton("Cancel", null).show();
        }

        public void onProviderEnabled(String provider) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

    };


    public void onResume() {

        super.onResume();

        String locProv = LocationManager.GPS_PROVIDER;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocMgr.requestLocationUpdates(locProv, (long) 10, (float) 0.5, mLocListener);
        try {
            mLocMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, (float) 0.5, mLocListener);
        }catch (Exception e){
            Toast.makeText(MapsActivity.this,"Please connect to a network",Toast.LENGTH_SHORT).show();
        }
        registerReceiver(
                receiver=new BroadcastReceiver (){

                    @Override
                    public void onReceive(Context arg0, Intent intent) {
                        NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                        if( (info.getType() == ConnectivityManager.TYPE_WIFI||info.getType() ==ConnectivityManager.TYPE_MOBILE)
                                && info.getState() == NetworkInfo.State.CONNECTED){
                            //Wi-Fi is connected.
                            //do something useful.
                            //...
                        }else{
                            Toast.makeText(arg0, "Refresh page if nothing shows", Toast.LENGTH_LONG).show();


                        }
                    }

                }, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


    }
    @Override
    protected void onDestroy() {
        try {
            mapFragment.onDestroy();
            super.onDestroy();
            /*unregisterReceiver(receiver);*/
        }catch(NullPointerException n){

        }/*catch(IllegalArgumentException e){

        }*/
    }

    @Override
    public void onLowMemory() {
        mapFragment.onLowMemory();
        super.onLowMemory();
    }


    public void onPause() {

        super.onPause();
        try{
        unregisterReceiver(receiver);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocMgr.removeUpdates(mLocListener);}
        catch(IllegalArgumentException e){

        }

    }



    private boolean initMap(Location location) {

       /* GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        mGoogleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();*/

        LatLng pos = new LatLng(location.getLatitude(),location.getLongitude());
        //부천시 원미구 중2동 위도,경도 : 37.4949542,126.76819620000003
        // 맵 중심 위치 이동
        mylat = (float) location.getLatitude();
        mylong = (float) location.getLongitude();
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 12));


        setHospitalMarkerOnMap(
                String.valueOf(mylat),
                String.valueOf(mylong));


        // 시작 위치에 마커 추가

//        MarkerOptions moStart = new MarkerOptions();
//
//        moStart.position(pos);
//
//        moStart.title("출발");
//
//        try {
//            mMarkerStart = mGoogleMap.addMarker(moStart);
//            if(mMarkerStart==null)
//                Toast.makeText(MapsActivity.this,"MarkerStart is null",Toast.LENGTH_SHORT).show();
//            mMarkerStart.showInfoWindow();
//        }catch(IllegalStateException e){
//
//        }
//
//        mMarkerStart.showInfoWindow();


        //mMarkerStart = mGoogleMap.addMarker(new MarkerOptions().position(pos).title("출발"));


        //marker title 클릭 이벤트
        mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
               // Log.d("marker",""+marker.getTitle());
                hospitalFragment.setListView(marker.getTitle());

            }
        });
        //marker 클릭 이벤트
        mGoogleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

            @Override

            public boolean onMarkerClick(Marker marker) {

                if (!marker.equals(prevMarker)) {
                    if (prevMarker != null) {
                        prevMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin2));
                    }

                    marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
                    prevMarker = marker;
                    if (fragment2 != null) {

                        FragmentManager fragmentManager = getFragmentManager();
                        //Log.d("1111", marker.getTitle());
                        fragment2 = null;
                        fragmentManager.popBackStackImmediate();



                    }
                }
                prevMarker = marker;


                //hospitalFragment.setListView(marker.getTitle());
                return false;

            }

        });



        // 보행자 마커 추가

        MarkerOptions moMan = new MarkerOptions();

        moMan.position(pos);

        moMan.icon(BitmapDescriptorFactory.fromResource(R.drawable.ml));

        mMarkerMan = mGoogleMap.addMarker( moMan );
        return true;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;

        // Add a marker in Sydney and move the camera

        //mGoogleMap.addMarker(new MarkerOptions().position(SEOUL).title("Marker in Seoul"));
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(SEOUL));
        mGoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15), 2000, null);


    }

    public void changeFragment(Hospital hospital){
        Bundle arguments = new Bundle();
        arguments.putSerializable("hospital",hospital);


        fragment2 = new HospitalInfoFragment();
        fragment2.setArguments(arguments);
        FragmentManager fragmentManager = getFragmentManager();
        if(!isFinishing()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack("hospitalList");
            fragmentTransaction.replace(R.id.hospitalFragment_place, fragment2);

            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    private void setHospitalMarkerOnMap(String lat, String lon) { //동주
        networkService = ApplicationController.getInstance().getNetworkService();
        hospital_ArrayList = new ArrayList<Hospital>();

        try {
            //병원데이터 불러오기
            Call<List<Hospital>> call_hospital_list = networkService.getAllHospital(lat, lon);
            call_hospital_list.enqueue(new Callback<List<Hospital>>() {
                @Override
                public void onResponse(Call<List<Hospital>> call, Response<List<Hospital>> response) {
                    if (response.code() == 201) {
                        List<Hospital> hospital_List = response.body();

                        for (int i = 0; i < hospital_List.size(); i++) {
                            //List<Hospital>의 데이터를 ArrayList<Hospital>에 넣는다.
                            hospital_ArrayList.add(
                                    new Hospital(
                                            hospital_List.get(i).getId(),
                                            hospital_List.get(i).getDutyAddr(),
                                            hospital_List.get(i).getEnglishAddr(),
                                            hospital_List.get(i).getDutyEmcls(),
                                            hospital_List.get(i).getDutyEmclsName(),
                                            hospital_List.get(i).getDutyName(),
                                            hospital_List.get(i).getEnglishName(),
                                            hospital_List.get(i).getDutyTel1(),
                                            hospital_List.get(i).getDutyTel2(),
                                            hospital_List.get(i).getLatitude(),
                                            hospital_List.get(i).getLongitude(),
                                            hospital_List.get(i).getDistance()));

                            //마커설정
                            options = new MarkerOptions();
                            options.position(
                                    new LatLng(
                                            Double.parseDouble(hospital_List.get(i).getLatitude()),
                                            Double.parseDouble(hospital_List.get(i).getLongitude())))
                                    .title(hospital_List.get(i).getEnglishName())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2))
                                    .snippet(hospital_List.get(i).getEnglishAddr());

                            //지도에 마커 추가
                            Marker tempMarker = mGoogleMap.addMarker(options);
                            markerArrayList.add(tempMarker);
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Fail to load data", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Hospital>> call, Throwable t) {

                }
            });
        }catch(Exception e) {
            e.getMessage();
            Toast.makeText(this, "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }
    }

    public void setMarkerMap(int pos){
        /*
            if(i==pos){
                LatLng marker = new LatLng(markerArrayList.get(pos).getPosition())
            }
        }*/
        LatLng marker = markerArrayList.get(pos).getPosition();


        if(!markerArrayList.get(pos).equals(prevMarker)) {
            if(prevMarker != null) {
                prevMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin2));
                prevMarker.hideInfoWindow();
            }

            markerArrayList.get(pos).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            prevMarker = markerArrayList.get(pos);
        }

        prevMarker = markerArrayList.get(pos);
        prevMarker.showInfoWindow();
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.maps, menu);
        return true;
    }
/*
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //progress class -동주-
    private class CheckTypeTasks extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog = new ProgressDialog(MapsActivity.this);

        @Override
        protected void onPreExecute() {
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("Please wait....");

            progressDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                for (int i=0; i<5; i++) {
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            super.onPostExecute(aVoid);
        }
    }
}