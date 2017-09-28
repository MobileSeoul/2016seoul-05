package seoul.emergency.bbibbo.notice;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import seoul.emergency.bbibbo.ApplicationController;
import seoul.emergency.bbibbo.NetworkService;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.model.Notice;
import seoul.emergency.bbibbo.questionnaire.QuestionnaireActivity;
import seoul.emergency.bbibbo.questionnaire.ScreenService;
import seoul.emergency.bbibbo.userguide.UserguideActivity;
import seoul.emergency.bbibbo.youtube.YoutubeActivity;

public class NoticeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ArrayList<Notice> noticeArray;
    private NetworkService networkService;
    NoticeAdapter noticeAdapter;
    ListView notice_listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNotice();
        final Typeface helR = Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica.ttf");

        Typeface helL = Typeface.createFromAsset(getApplicationContext().getAssets(),"fonts/Helvetica Light.ttf");

        TextView appbar = (TextView)findViewById(R.id.appbar_notice_text);
        appbar.setTypeface(helL);

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

    public void setNotice() {
        networkService = ApplicationController.getInstance().getNetworkService();
        noticeArray = new ArrayList<Notice>();
        notice_listView = (ListView) findViewById(R.id.notice_listView);
        if (noticeArray != null) noticeArray.clear();

        try {
            final Call<List<Notice>> allNotice = networkService.getNotice();
            allNotice.enqueue(new Callback<List<Notice>>() {
                @Override
                public void onResponse(Call<List<Notice>> call, Response<List<Notice>> response) {
                    //Log.d("my", "" + pharmacy.getLongitude());
                    if (response.code() == 201) {
                        List<Notice> allnotice = response.body();
                        //Log.d("SOPT", "  allpharmacy_id : " + allpharmacy.get(0).getId());
                        //int id, String dutyAddr, String englishAddr, String dutyName, String englishName, String dutyTel1, String dutyTime1s, String dutyTime1c, String dutyTime2s, String dutyTime2c, String dutyTime3s, String dutyTime3c, String dutyTime4s, String dutyTime4c, String dutyTime5s, String dutyTime5c, String dutyTime6s, String dutyTime6c, String dutyTime7s, String dutyTime7c, String dutyTime8s, String dutyTime8c
                        // String postCdn1, String postCdn2, String latitude, String longitude, String foreignLanguage1, String foreignLanguage2, String foreignLanguage3, String foreignLanguage4
                        for (int i = 0; i < allnotice.size(); i++) {
                            noticeArray.add(new Notice(allnotice.get(i).getId(), allnotice.get(i).getTitle(), allnotice.get(i).getContent(),
                                    allnotice.get(i).getWritten_date()));

                        }

                        noticeAdapter = new NoticeAdapter(NoticeActivity.this, R.layout.custom_notice, noticeArray);
                        notice_listView.setAdapter(noticeAdapter);
                        notice_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                                Log.d("bbibbo", "클릭됨");
                                Intent intent = new Intent(NoticeActivity.this, NoticeDetailActivity.class);
                                intent.putExtra("noticeData", noticeArray.get(pos));
                                startActivity(intent);

                            }
                        });
                        noticeAdapter.notifyDataSetChanged();
                    } else {
                        int statusCode = response.code();
                        Log.d("SOPT", "응답코드: " + statusCode);
                    }
                }

                @Override
                public void onFailure(Call<List<Notice>> call, Throwable t) {
                    //Toast.makeText(context, "Failed to load thumbnails", Toast.LENGTH_LONG).show();
                    Log.i("SOPT", "에러내용: " + t.getMessage());

                }
            });
        } catch (Exception e) {
            Log.d("notice", e.getMessage());
        }
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
        getMenuInflater().inflate(R.menu.notice, menu);
        return true;
    }

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return false;
        }

        //return super.onOptionsItemSelected(item);
        return false;
    }*/

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
