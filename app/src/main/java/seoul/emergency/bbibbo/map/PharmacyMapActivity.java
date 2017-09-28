package seoul.emergency.bbibbo.map;


import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.TypefaceSpan;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;


import android.Manifest;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seoul.emergency.bbibbo.ApplicationController;
import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.hospital.HospitalInfoFragment;
import seoul.emergency.bbibbo.model.Hospital;
import seoul.emergency.bbibbo.model.Pharmacy;
import seoul.emergency.bbibbo.notice.NoticeActivity;
import seoul.emergency.bbibbo.pharmacy.PharmacyFragment;
import seoul.emergency.bbibbo.pharmacy.PharmacyInfoFragment;
import seoul.emergency.bbibbo.questionnaire.QuestionnaireActivity;
import seoul.emergency.bbibbo.questionnaire.ScreenService;
import seoul.emergency.bbibbo.userguide.UserguideActivity;
import seoul.emergency.bbibbo.youtube.YoutubeActivity;


public class PharmacyMapActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, OnMapReadyCallback {

    GoogleMap mGoogleMap;

    Marker mMarkerStart;

    Marker mMarkerMan;

    boolean mFirstLoc = true;

    LocationManager mLocMgr;
    Location mlocation;
    BroadcastReceiver receiver2;
    static final LatLng SEOUL = new LatLng(37.56, 126.97);

    LatLng position;
    PharmacyFragment pharmacyFragment = new PharmacyFragment();
    float mylat;
    float mylong;
    //-동주-
    ArrayList<Pharmacy> pharmacy_ArrayList;
    NetworkService networkService;
    MarkerOptions options;
    Marker prevMarker;
    ImageButton newPosition;
    PharmacyMapActivity pharmacyMapActivity;
    SupportMapFragment mapFragment;

    ArrayList<Marker> markerArrayList = new ArrayList<Marker>();

    Spinner language_spinner;
    LinearLayout spinnerLayout;
    Bundle bundle;
    String res;
    Fragment fragment2;
    int check = 0;
    int zoom_start = 0;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pharmacy_map);


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



        spinnerLayout = (LinearLayout)findViewById(R.id.layout_pharmacy_spinner);

        Typeface helL= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Light.ttf");
        final Typeface helR = Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica.ttf");

        TextView appbar = (TextView)findViewById(R.id.appbar_pharmacy_text);
        appbar.setTypeface(helL);


        /*Spannable s = new SpannableString("Pharmacy");
        s.setSpan(helL,0,s.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(s);*/

        text_call1.setTypeface(helL);
        text_call2.setTypeface(helL);
        text_home.setTypeface(helL);
        text_m.setTypeface(helL);


        //하단바
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
        bundle = new Bundle();

        //-동주-
        try {
            CheckTypeTasks checkTypeTasks = new CheckTypeTasks();
            checkTypeTasks.execute();
        }catch (Exception e){
            Log.d("태스크",e.getMessage());
        }


        mLocMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        newPosition = (ImageButton) findViewById(R.id.new_position_pharmacy);
        newPosition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PharmacyMapActivity.this, PharmacyMapActivity.class);
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
                    if (initMap(location)) {
                        Log.d("@@", "" + mylat);
                        //PharmacyFragment로 위도 경도 값 보내기
                        bundle.putFloat("mylatitude", mylat);
                        bundle.putFloat("mylongitude", mylong);
                        //리스트뷰 프래그먼트
                        pharmacyFragment = new PharmacyFragment();
                        pharmacyFragment.setArguments(bundle);
                        FragmentManager fragmentManager = getFragmentManager();
                        if(!isFinishing()) {
                            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                            fragmentTransaction.add(R.id.pharmacyFragment_place, pharmacyFragment, "first");

                            fragmentTransaction.commitAllowingStateLoss();
                        }

                    } else {
                        new AlertDialog.Builder(PharmacyMapActivity.this)
                                .setMessage("Please Retry")
                                .setNegativeButton("Cancel", null).show();
                    }

                }
            };

            MyLocation myLocation = new MyLocation();
            myLocation.getLocation(getApplicationContext(), locationResult);
        } catch (Exception e) {
            new AlertDialog.Builder(PharmacyMapActivity.this)
                    .setMessage("Please Retry")
                    .setNegativeButton("Cancel", null).show();
        }
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.pharmacy_map);
        mapFragment.getMapAsync(PharmacyMapActivity.this);

        //language_spinner
        language_spinner = (Spinner) findViewById(R.id.language_spinner);
        final String[] spinner_str = getResources().getStringArray(R.array.spinnerArray);
       /* ArrayAdapter<String> spinner_adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_spinner_dropdown_item, spinner_str);

*/
        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource(getApplicationContext(),R.array.spinnerArray, R.layout.spinner_item_language);
        // adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_adapter.setDropDownViewResource(R.layout.spinner_item_language);

        language_spinner.setAdapter(spinner_adapter);

        language_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView)adapterView.getChildAt(0)).setTextSize(10);
                res = (String) language_spinner.getAdapter().getItem(i);

                //bundle.putString("language",res);
                if (check > 0) {
                    if (res.equals("Any near by")) {
                        setPharmacyMarkerOnMap(String.valueOf(mylat), String.valueOf(mylong));
                        pharmacyFragment.setLanguage("Any near by");
                        pharmacyFragment.setNearBy();
                        /*FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.pharmacyFragment_place, pharmacyFragment, "first");

                        fragmentTransaction.commit();*/
                    } else {
                        setPharmacyMarkerOnMap(String.valueOf(mylat), String.valueOf(mylong), res);
                        pharmacyFragment.setLanguage(res);
                        pharmacyFragment.setDataByLanguage(String.valueOf(mylat), String.valueOf(mylong), res);
                        /*FragmentManager fragmentManager = getFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.pharmacyFragment_place, pharmacyFragment, "first");
                        fragmentTransaction.commit();*/
                    }
                } else {
                    check += 1;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

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
                //navigation btn
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

    @Override
    protected void onDestroy() {
        try {
            mapFragment.onDestroy();
            super.onDestroy();
           /* unregisterReceiver(receiver2);*/
        } catch (NullPointerException n) {

        }/*catch(IllegalArgumentException e){

        }*/

    }

    @Override
    public void onLowMemory() {
        mapFragment.onLowMemory();
        super.onLowMemory();
    }

    LocationListener mLocListener = new LocationListener() {

        public void onLocationChanged(Location location) {
            mlocation = location;
            LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

//            if (mFirstLoc) {
//
//                mFirstLoc = false;
//                if(mMarkerStart==null)
//                    Toast.makeText(PharmacyMapActivity.this,"start is null",Toast.LENGTH_SHORT).show();
//                else
//                    mMarkerStart.setPosition(position);
//
//            }

            if (mMarkerMan == null)
                Toast.makeText(PharmacyMapActivity.this, "Press the new position button", Toast.LENGTH_SHORT).show();
            else {
                mMarkerMan.setPosition(position);
                //mGoogleMap.moveCamera(CameraUpdateFactory.newLatLng(position));
            }


        }


        public void onProviderDisabled(String provider) {
            new AlertDialog.Builder(PharmacyMapActivity.this)
                    .setMessage("GPS is turned off.\n On 'Location service’please turn on ‘Google Location service' ")
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
        } catch (Exception e) {
            Toast.makeText(PharmacyMapActivity.this, "Please connect to a network", Toast.LENGTH_SHORT).show();
        }
        registerReceiver(
                receiver2 = new BroadcastReceiver() {

                    @Override
                    public void onReceive(Context arg0, Intent intent) {
                        NetworkInfo info = intent.getParcelableExtra(ConnectivityManager.EXTRA_NETWORK_INFO);
                        if ((info.getType() == ConnectivityManager.TYPE_WIFI || info.getType() == ConnectivityManager.TYPE_MOBILE)
                                && info.getState() == NetworkInfo.State.CONNECTED) {
                            //Wi-Fi is connected.
                            //do something useful.
                            //...
                        } else {
                            Toast.makeText(arg0, "Refresh page if nothing shows", Toast.LENGTH_LONG).show();


                        }
                    }

                }, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));


    }


    public void onPause() {

        super.onPause();
        try {
            unregisterReceiver(receiver2);
            pharmacyFragment.onPause();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] gratintResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mLocMgr.removeUpdates(mLocListener);
        } catch (IllegalArgumentException e) {

        }

    }


    public boolean initMap(Location location) {

       /* GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);

        mGoogleMap = ((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();*/

        try {
            LatLng pos = new LatLng(location.getLatitude(), location.getLongitude());
            Log.d("" + pos.toString(), "initMap");
            mylat = (float) location.getLatitude();
            mylong = (float) location.getLongitude();
            Log.d("" + location.getLongitude(), "initMap2");
            //Log.d(""+mylat,"mylat");
            //부천시 원미구 중2동 위도,경도 : 37.4949542,126.76819620000003
            // 맵 중심 위치 이동

            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(pos, 15));


            //약국 추가 -동주-
            setPharmacyMarkerOnMap(
                    String.valueOf(pos.latitude),
                    String.valueOf(pos.longitude));

            //marker title 클릭 이벤트
            mGoogleMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                @Override
                public void onInfoWindowClick(Marker marker) {
                    Log.d("marker",""+marker.getTitle());
                    pharmacyFragment.setListView(marker.getTitle());

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
                            Log.d("1111", marker.getTitle());
                            fragment2 = null;
                            fragmentManager.popBackStackImmediate();



                        }
                    }
                    prevMarker = marker;

                    return false;


                }

            });


            // 보행자 마커 추가

            MarkerOptions moMan = new MarkerOptions();

            moMan.position(pos);

            moMan.icon(BitmapDescriptorFactory.fromResource(R.drawable.ml));

            mMarkerMan = mGoogleMap.addMarker(moMan);
        } catch (NullPointerException e) {
            new AlertDialog.Builder(PharmacyMapActivity.this)
                    .setMessage("Please try again")
                    .setNegativeButton("Cancel", null).show();
        }catch (Exception e){
            Toast.makeText(pharmacyMapActivity, "please retry", Toast.LENGTH_SHORT).show();
        }
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

    public void changeFragment(Pharmacy pharmacy) {
        Bundle arguments = new Bundle();
        arguments.putSerializable("pharmacy", pharmacy);

        fragment2 = new PharmacyInfoFragment();
        fragment2.setArguments(arguments);

        spinnerLayout.setVisibility(View.GONE);
        language_spinner.setVisibility(View.GONE);

        FragmentManager fragmentManager = getFragmentManager();
        if(!isFinishing()) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            //fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            fragmentTransaction.addToBackStack("pharmacyList");

            fragmentTransaction.replace(R.id.pharmacyFragment_place, fragment2);
            fragmentTransaction.commitAllowingStateLoss();
        }

    }

    // -동주-
    private void setPharmacyMarkerOnMap(String lat, String lon) {
        //네트워크 설정
        networkService = ApplicationController.getInstance().getNetworkService();

        //ArrayList 초기화
        pharmacy_ArrayList = new ArrayList<Pharmacy>();

        try {
            //현재위치기반 약국 데이터 함수 호출
            Call<List<Pharmacy>> call_Pharmacy_List = networkService.getPharmacy(lat, lon);

            //모든 약국 데이터 함수 호출
            //Call<List<Pharmacy>> call_Pharmacy_List = networkService.getAllPharmacy();
            call_Pharmacy_List.enqueue(new Callback<List<Pharmacy>>() {
                @Override
                public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                    if (response.code() == 201) {
                        //약국 json 파일 불러오기
                        List<Pharmacy> pharmacy_List = response.body();

                        for (int i = 0; i < markerArrayList.size(); i++) {
                            markerArrayList.get(i).remove();
                        }
                        markerArrayList.clear();
                        prevMarker = null;

                        for (int i = 0; i < pharmacy_List.size(); i++) {
                            pharmacy_ArrayList.add(
                                    new Pharmacy(
                                            pharmacy_List.get(i).getId(),
                                            pharmacy_List.get(i).getDutyAddr(),
                                            pharmacy_List.get(i).getEnglishAddr(),
                                            pharmacy_List.get(i).getDutyName(),
                                            pharmacy_List.get(i).getEnglishName(),
                                            pharmacy_List.get(i).getDutyTel1(),
                                            pharmacy_List.get(i).getDutyTime1s(),
                                            pharmacy_List.get(i).getDutyTime1c(),
                                            pharmacy_List.get(i).getDutyTime2s(),
                                            pharmacy_List.get(i).getDutyTime2c(),
                                            pharmacy_List.get(i).getDutyTime3s(),
                                            pharmacy_List.get(i).getDutyTime3c(),
                                            pharmacy_List.get(i).getDutyTime4s(),
                                            pharmacy_List.get(i).getDutyTime4c(),
                                            pharmacy_List.get(i).getDutyTime5s(),
                                            pharmacy_List.get(i).getDutyTime5c(),
                                            pharmacy_List.get(i).getDutyTime6s(),
                                            pharmacy_List.get(i).getDutyTime6c(),
                                            pharmacy_List.get(i).getDutyTime7s(),
                                            pharmacy_List.get(i).getDutyTime7c(),
                                            pharmacy_List.get(i).getDutyTime8s(),
                                            pharmacy_List.get(i).getDutyTime8c(),
                                            pharmacy_List.get(i).getPostCdn1(),
                                            pharmacy_List.get(i).getPostCdn2(),
                                            pharmacy_List.get(i).getLatitude(),
                                            pharmacy_List.get(i).getLongitude(),
                                            pharmacy_List.get(i).getforeignLanguage1(),
                                            pharmacy_List.get(i).getforeignLanguage2(),
                                            pharmacy_List.get(i).getforeignLanguage3(),
                                            pharmacy_List.get(i).getforeignLanguage4(),
                                            pharmacy_List.get(i).getDistance()));


                            //pharmacy_ArrayList.get(pharmacy_ArrayList.size()-1).setMarkerId();

                            //마커 설정
                            options = new MarkerOptions();
                            options.position(
                                    new LatLng(
                                            Double.parseDouble(pharmacy_List.get(i).getLatitude()),
                                            Double.parseDouble(pharmacy_List.get(i).getLongitude())))
                                    .title(pharmacy_List.get(i).getEnglishName())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2))
                                    .snippet(pharmacy_List.get(i).getEnglishAddr());

                            //지도에 마커 추가
                            Marker tempMarker = mGoogleMap.addMarker(options);
                            markerArrayList.add(tempMarker);

                            pharmacy_ArrayList.get(i).setMarkerId(tempMarker.getId());

                        }
                        // Log.e("SOPT", pharmacy_List.get(0).getEnglishName());
                    } else {
                        Toast.makeText(getApplicationContext(), "Fail to load data", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Pharmacy>> call, Throwable t) {

                }
            });
        }catch(Exception e) {
            e.getMessage();
            Toast.makeText(this, "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }

    }

    private void setPharmacyMarkerOnMap(String lat, String lon, String language) {
        //네트워크 설정
        networkService = ApplicationController.getInstance().getNetworkService();

        //ArrayList 초기화
        pharmacy_ArrayList = new ArrayList<Pharmacy>();

        try {
            //현재위치기반 약국 데이터 함수 호출
            Call<List<Pharmacy>> call_Pharmacy_List = networkService.getLanguage(language, lat, lon);

            //모든 약국 데이터 함수 호출
            //Call<List<Pharmacy>> call_Pharmacy_List = networkService.getAllPharmacy();
            call_Pharmacy_List.enqueue(new Callback<List<Pharmacy>>() {
                @Override
                public void onResponse(Call<List<Pharmacy>> call, Response<List<Pharmacy>> response) {
                    if (response.code() == 201) {
                        //약국 json 파일 불러오기
                        List<Pharmacy> pharmacy_List = response.body();
                        for (int i = 0; i < markerArrayList.size(); i++) {
                            markerArrayList.get(i).remove();
                        }
                        markerArrayList.clear();
                        prevMarker = null;

                        for (int i = 0; i < pharmacy_List.size(); i++) {
                            pharmacy_ArrayList.add(
                                    new Pharmacy(
                                            pharmacy_List.get(i).getId(),
                                            pharmacy_List.get(i).getDutyAddr(),
                                            pharmacy_List.get(i).getEnglishAddr(),
                                            pharmacy_List.get(i).getDutyName(),
                                            pharmacy_List.get(i).getEnglishName(),
                                            pharmacy_List.get(i).getDutyTel1(),
                                            pharmacy_List.get(i).getDutyTime1s(),
                                            pharmacy_List.get(i).getDutyTime1c(),
                                            pharmacy_List.get(i).getDutyTime2s(),
                                            pharmacy_List.get(i).getDutyTime2c(),
                                            pharmacy_List.get(i).getDutyTime3s(),
                                            pharmacy_List.get(i).getDutyTime3c(),
                                            pharmacy_List.get(i).getDutyTime4s(),
                                            pharmacy_List.get(i).getDutyTime4c(),
                                            pharmacy_List.get(i).getDutyTime5s(),
                                            pharmacy_List.get(i).getDutyTime5c(),
                                            pharmacy_List.get(i).getDutyTime6s(),
                                            pharmacy_List.get(i).getDutyTime6c(),
                                            pharmacy_List.get(i).getDutyTime7s(),
                                            pharmacy_List.get(i).getDutyTime7c(),
                                            pharmacy_List.get(i).getDutyTime8s(),
                                            pharmacy_List.get(i).getDutyTime8c(),
                                            pharmacy_List.get(i).getPostCdn1(),
                                            pharmacy_List.get(i).getPostCdn2(),
                                            pharmacy_List.get(i).getLatitude(),
                                            pharmacy_List.get(i).getLongitude(),
                                            pharmacy_List.get(i).getforeignLanguage1(),
                                            pharmacy_List.get(i).getforeignLanguage2(),
                                            pharmacy_List.get(i).getforeignLanguage3(),
                                            pharmacy_List.get(i).getforeignLanguage4(),
                                            pharmacy_List.get(i).getDistance()));


                            //pharmacy_ArrayList.get(pharmacy_ArrayList.size()-1).setMarkerId();

                            //마커 설정
                            options = new MarkerOptions();
                            options.position(
                                    new LatLng(
                                            Double.parseDouble(pharmacy_List.get(i).getLatitude()),
                                            Double.parseDouble(pharmacy_List.get(i).getLongitude())))
                                    .title(pharmacy_List.get(i).getEnglishName())
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.pin2))
                                    .snippet(pharmacy_List.get(i).getEnglishAddr());

                            //지도에 마커 추가
                            Marker tempMarker = mGoogleMap.addMarker(options);
                            markerArrayList.add(tempMarker);

                            pharmacy_ArrayList.get(i).setMarkerId(tempMarker.getId());

                        }
                        //Log.e("SOPT", pharmacy_List.get(0).getEnglishName());
                    } else {
                        Toast.makeText(getApplicationContext(), "Fail to load data", Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<List<Pharmacy>> call, Throwable t) {

                }
            });
        }catch(Exception e) {
            e.getMessage();
            Toast.makeText(this, "Don't turn off during loading ", Toast.LENGTH_SHORT).show();
        }

    }

    public void setMarkerMap(int pos) {
        /*
            if(i==pos){
                LatLng marker = new LatLng(markerArrayList.get(pos).getPosition())
            }
        }*/
        LatLng marker = markerArrayList.get(pos).getPosition();


        if (!markerArrayList.get(pos).equals(prevMarker)) {
            if (prevMarker != null) {
                prevMarker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin2));
                prevMarker.hideInfoWindow();
            }

            markerArrayList.get(pos).setIcon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            prevMarker = markerArrayList.get(pos);
        }

        prevMarker = markerArrayList.get(pos);
        prevMarker.showInfoWindow();
        CameraUpdateFactory.newLatLngZoom(marker, 15);
        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(marker, 15));
    }

    private class CheckTypesTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog asyncDialog = new ProgressDialog(
                PharmacyMapActivity.this);

        @Override
        protected void onPreExecute() {
            asyncDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            asyncDialog.setMessage("Loading..");

            // show dialog
            asyncDialog.show();
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            try {
                for (int i = 0; i < 5; i++) {
                    //asyncDialog.setProgress(i * 30);
                    Thread.sleep(1500);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            asyncDialog.dismiss();
            super.onPostExecute(result);
        }
    }


    @Override
    public void onBackPressed() {

        spinnerLayout.setVisibility(View.VISIBLE);
        language_spinner.setVisibility(View.VISIBLE);

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
        getMenuInflater().inflate(R.menu.pharmacy_map, menu);
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

        ProgressDialog progressDialog = new ProgressDialog(PharmacyMapActivity.this);

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
                for (int i = 0; i < 5; i++) {
                    Thread.sleep(2000);
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