package seoul.emergency.bbibbo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import seoul.emergency.bbibbo.map.AedMapActivity;
import seoul.emergency.bbibbo.map.MapsActivity;
import seoul.emergency.bbibbo.map.PharmacyMapActivity;
import seoul.emergency.bbibbo.notice.NoticeActivity;
import seoul.emergency.bbibbo.questionnaire.DiseaseItem;
import seoul.emergency.bbibbo.questionnaire.QuestionnaireActivity;
import seoul.emergency.bbibbo.questionnaire.ScreenService;
import seoul.emergency.bbibbo.userguide.UserguideActivity;
import seoul.emergency.bbibbo.youtube.YoutubeActivity;

import static seoul.emergency.bbibbo.R.string.title_activity_main;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static int window_width;
    public static int window_height;
    private static MainActivity appInstance;

    public ArrayList<DiseaseItem> diseaseItemList = new ArrayList<DiseaseItem>();

    public static ArrayList<Activity> actList = new ArrayList<Activity>();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        appInstance=this;
        actList.add(this);
        Intent intent = new Intent(MainActivity.this,UserguideActivity.class);
        intent.putExtra("actList",actList);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        final ImageView button1 = (ImageView) findViewById(R.id.btn_hospital);
        final ImageView button2 = (ImageView) findViewById(R.id.btn_pharmacy);
        final ImageView button3 = (ImageView) findViewById(R.id.btn_aed);
        final ImageView button4 = (ImageView) findViewById(R.id.btn_home);
        final ImageView button5 = (ImageView) findViewById(R.id.btn_call1);
        final ImageView button6 = (ImageView) findViewById(R.id.btn_call2);
        final ImageView button7 = (ImageView) findViewById(R.id.btn_m);


        ImageView icon_hospital = (ImageView) findViewById(R.id.icon_hospital);
        TextView text_hospital = (TextView) findViewById(R.id.text_hospital);
        ImageView icon_pharmacy= (ImageView) findViewById(R.id.icon_pharmacy);
        TextView text_pharmacy = (TextView) findViewById(R.id.text_pharmacy);
        ImageView icon_aed = (ImageView) findViewById(R.id.icon_aed);
        TextView text_aed = (TextView) findViewById(R.id.text_aed);
        ImageView icon_home = (ImageView) findViewById(R.id.icon_home);
        TextView text_home = (TextView) findViewById(R.id.text_home);
        ImageView icon_call1 = (ImageView) findViewById(R.id.icon_call1);
        TextView text_call1 = (TextView) findViewById(R.id.text_call1);
        ImageView icon_call2 = (ImageView) findViewById(R.id.icon_call2);
        TextView text_call2 = (TextView) findViewById(R.id.text_call2);
        ImageView icon_m = (ImageView) findViewById(R.id.icon_m);
        TextView text_m = (TextView) findViewById(R.id.text_m);

        final Typeface helR = Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Light.ttf");
        Typeface helB= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Bold.ttf");
        Typeface y340 = Typeface.createFromAsset(this.getAssets(),"fonts/yoon340.ttf");

        text_call1.setTypeface(helL);
        text_call2.setTypeface(helL);
        text_home.setTypeface(helL);
        text_m.setTypeface(helL);
        text_pharmacy.setTypeface(helR);
        text_hospital.setTypeface(helR);
        text_aed.setTypeface(helR);





        window_width = metrics.widthPixels;
        window_height = metrics.heightPixels;

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) icon_hospital.getLayoutParams();

        params.width = (int)(metrics.widthPixels * 45.0 / 360) ;
        params.height = (int)(metrics.heightPixels * 43.0 / 640);
        params.topMargin = (int)(metrics.heightPixels * 51.0 / 640);

        params = (RelativeLayout.LayoutParams) text_hospital.getLayoutParams();
        params.topMargin = (int)(metrics.heightPixels * 110.0 / 640);

        RelativeLayout.LayoutParams params2 = (RelativeLayout.LayoutParams) icon_pharmacy.getLayoutParams();

        params2.width = (int)(metrics.widthPixels * 31.0 / 360) ;
        params2.height = (int)(metrics.heightPixels * 41.0 / 640);
        params2.topMargin = (int)(metrics.heightPixels * 51.0 / 640);

        params2 = (RelativeLayout.LayoutParams) text_pharmacy.getLayoutParams();
        params2.topMargin = (int)(metrics.heightPixels * 110.0 / 640);

        RelativeLayout.LayoutParams params3 = (RelativeLayout.LayoutParams) icon_aed.getLayoutParams();

        params3.width = (int)(metrics.widthPixels * 45.0 / 360) ;
        params3.height = (int)(metrics.heightPixels * 34.0 / 640);
        params3.topMargin = (int)(metrics.heightPixels * 58.0 / 640);

        params3 = (RelativeLayout.LayoutParams) text_aed.getLayoutParams();
        params3.topMargin = (int)(metrics.heightPixels * 110.0 / 640);

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


        button1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button1.setBackgroundColor(Color.parseColor("#88000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                    startActivity(intent);
                    button1.setBackgroundColor(Color.parseColor("#66000000"));
                }
                return true;
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button2.setBackgroundColor(Color.parseColor("#88000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(getApplicationContext(), PharmacyMapActivity.class);
                    startActivity(intent);
                    button2.setBackgroundColor(Color.parseColor("#66000000"));
                }
                return true;
            }
        });
        button3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    button3.setBackgroundColor(Color.parseColor("#88000000"));
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    Intent intent = new Intent(getApplicationContext(), AedMapActivity.class);
                    startActivity(intent);
                    button3.setBackgroundColor(Color.parseColor("#66000000"));
                }
                return true;
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

                final Button btn_drawer1 = (Button)findViewById(R.id.btn_drawer1);
                final Button btn_drawer2 = (Button)findViewById(R.id.btn_drawer2);
                final Button btn_drawer3 = (Button)findViewById(R.id.btn_drawer3);
                final Button btn_drawer4 = (Button)findViewById(R.id.btn_drawer4);

                btn_drawer1.setTypeface(helR);
                btn_drawer2.setTypeface(helR);
                btn_drawer3.setTypeface(helR);
                btn_drawer4.setTypeface(helR);

                //questionaire
                btn_drawer1.setOnTouchListener(new View.OnTouchListener(){
                                                      @Override
                                                      public boolean onTouch(View v, MotionEvent event) {
                                                          if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                                              btn_drawer1.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                                                          } else if (event.getAction() == MotionEvent.ACTION_UP) {

                                                              Intent intent = new Intent(getApplicationContext(), QuestionnaireActivity.class);
                                                              startActivity(intent);
                                                              btn_drawer1.setBackground(getResources().getDrawable(R.drawable.border));
                                                          }
                                                          return true;
                                                      }
                                                  });


                //youtube
                btn_drawer2.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer2.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
                            startActivity(intent);
                            btn_drawer2.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });
                //notice
                btn_drawer3.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer3.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
                            startActivity(intent);
                            btn_drawer3.setBackground(getResources().getDrawable(R.drawable.border));
                        }
                        return true;
                    }
                });
                //user_guide
                btn_drawer4.setOnTouchListener(new View.OnTouchListener(){
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (event.getAction() == MotionEvent.ACTION_DOWN) {
                            btn_drawer4.setBackground(getResources().getDrawable(R.drawable.border_click_event));
                        } else if (event.getAction() == MotionEvent.ACTION_UP) {
                            Intent intent = new Intent(getApplicationContext(), UserguideActivity.class);
                            startActivity(intent);
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
        getMenuInflater().inflate(R.menu.main, menu);

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

        }

        return super.onOptionsItemSelected(item);
    }
*/
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Intent intent = new Intent(getApplicationContext(), QuestionnaireActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_gallery) {
            Intent intent = new Intent(getApplicationContext(), YoutubeActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static MainActivity getInstance(){
        return appInstance;
    }
}
