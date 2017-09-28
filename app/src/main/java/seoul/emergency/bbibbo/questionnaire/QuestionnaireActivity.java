package seoul.emergency.bbibbo.questionnaire;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import seoul.emergency.bbibbo.MainActivity;
import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.notice.NoticeActivity;
import seoul.emergency.bbibbo.userguide.UserguideActivity;
import seoul.emergency.bbibbo.youtube.YoutubeActivity;

public class QuestionnaireActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);


        ImageView icon_home = (ImageView) findViewById(R.id.icon_home);
        TextView text_home = (TextView) findViewById(R.id.text_home);
        ImageView icon_call1 = (ImageView) findViewById(R.id.icon_call1);
        TextView text_call1 = (TextView) findViewById(R.id.text_call1);
        ImageView icon_call2 = (ImageView) findViewById(R.id.icon_call2);
        TextView text_call2 = (TextView) findViewById(R.id.text_call2);
        ImageView icon_m = (ImageView) findViewById(R.id.icon_m);
        TextView text_m = (TextView) findViewById(R.id.text_m);

        TextView questionnaire_basic = (TextView) findViewById(R.id.questionnaire_basic);
        TextView questionnaire_name = (TextView) findViewById(R.id.questionnaire_name);
        TextView questionnaire_nationality = (TextView) findViewById(R.id.questionnaire_nationality);
        TextView questionnaire_height = (TextView) findViewById(R.id.questionnaire_height);
        TextView questionnaire_weight = (TextView) findViewById(R.id.questionnaire_weight);
        TextView questionnaire_age = (TextView) findViewById(R.id.questionnaire_age);
        TextView questionnaire_gender = (TextView) findViewById(R.id.questionnaire_gender);
        TextView questionnaire_bloodType = (TextView) findViewById(R.id.questionnaire_bloodType);
        TextView questionnaire_text1 = (TextView) findViewById(R.id.text1);
        TextView questionnaire_text2 = (TextView) findViewById(R.id.text2);
        TextView questionnaire_text3 = (TextView) findViewById(R.id.text3);
        TextView questionnaire_text4 = (TextView) findViewById(R.id.text4);
        TextView questionnaire_text5 = (TextView) findViewById(R.id.text5);
        TextView questionnaire_tuber = (TextView) findViewById(R.id.questionnaire_tuber);
        TextView questionnaire_hepa = (TextView) findViewById(R.id.questionnaire_hepa);
        TextView questionnaire_liver = (TextView) findViewById(R.id.questionnaire_liver);
        TextView questionnaire_hyper = (TextView) findViewById(R.id.questionnaire_hyper);
        TextView questionnaire_stroke = (TextView) findViewById(R.id.questionnaire_stroke);
        TextView questionnaire_heart = (TextView) findViewById(R.id.questionnaire_heart);
        TextView questionnaire_dia = (TextView) findViewById(R.id.questionnaire_dia);
        TextView questionnaire_cancer = (TextView) findViewById(R.id.questionnaire_cancer);
        TextView questionnaire_depress = (TextView) findViewById(R.id.questionnaire_depress);
        TextView questionnaire_other = (TextView) findViewById(R.id.questionnaire_other);
        TextView questionnaire_frequent = (TextView) findViewById(R.id.questionnaire_frequent);
        TextView questionnaire_some = (TextView) findViewById(R.id.questionnaire_some);
        TextView questionnaire_hardly = (TextView) findViewById(R.id.questionnaire_hardly);
        TextView questionnaire_never = (TextView) findViewById(R.id.questionnaire_never);
        TextView questionnaire_eating = (TextView) findViewById(R.id.questionnaire_eating);
        TextView questionnaire_smoking = (TextView) findViewById(R.id.questionnaire_smoking);
        TextView questionnaire_drink = (TextView) findViewById(R.id.questionnaire_drink);
        TextView questionnaire_additional = (TextView) findViewById(R.id.questionnaire_additional);

        final Typeface helR = Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica.ttf");
        Typeface helL= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Light.ttf");
        Typeface helB= Typeface.createFromAsset(this.getAssets(),"fonts/Helvetica Bold.ttf");
        Typeface y340 = Typeface.createFromAsset(QuestionnaireActivity.this.getAssets(),"fonts/yoon340.ttf");

        TextView appbar = (TextView)findViewById(R.id.appbar_questionnaire_text);
        appbar.setTypeface(helL);

        questionnaire_basic.setTypeface(helB);
        questionnaire_name.setTypeface(helR);
        questionnaire_nationality.setTypeface(helR);
        questionnaire_height.setTypeface(helR);
        questionnaire_weight.setTypeface(helR);
        questionnaire_age.setTypeface(helR);
        questionnaire_gender.setTypeface(helR);
        questionnaire_bloodType.setTypeface(helR);
        questionnaire_text1.setTypeface(helB);
        questionnaire_text2.setTypeface(helB);
        questionnaire_text3.setTypeface(helB);
        questionnaire_text4.setTypeface(helB);
        questionnaire_text5.setTypeface(helB);
        questionnaire_tuber.setTypeface(helR);
        questionnaire_hepa.setTypeface(helR);
        questionnaire_liver.setTypeface(helR);
        questionnaire_hyper.setTypeface(helR);
        questionnaire_stroke.setTypeface(helR);
        questionnaire_heart.setTypeface(helR);
        questionnaire_dia.setTypeface(helR);
        questionnaire_cancer.setTypeface(helR);
        questionnaire_depress.setTypeface(helR);
        questionnaire_other.setTypeface(helB);
        questionnaire_frequent.setTypeface(helB);
        questionnaire_some.setTypeface(helB);
        questionnaire_hardly.setTypeface(helB);
        questionnaire_never.setTypeface(helB);
        questionnaire_eating.setTypeface(helB);
        questionnaire_smoking.setTypeface(helR);
        questionnaire_drink.setTypeface(helR);
        questionnaire_additional.setTypeface(helR);


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

        final ImageView button4 = (ImageView) findViewById(R.id.btn_home);
        final ImageView button5 = (ImageView) findViewById(R.id.btn_call1);
        final ImageView button6 = (ImageView) findViewById(R.id.btn_call2);
        final ImageView button7 = (ImageView) findViewById(R.id.btn_m);


        final InnerDBManager innerDBManager = new InnerDBManager(getApplicationContext(), "Questionnaire.db", null, 1);

        final EditText etName = (EditText) findViewById(R.id.et_name);
        final EditText etNationality = (EditText) findViewById(R.id.et_nationality);
        final EditText etAge = (EditText) findViewById(R.id.et_age);
        final EditText etHeight = (EditText) findViewById(R.id.et_height);
        final EditText etWeight = (EditText) findViewById(R.id.et_weight);
        final EditText etNote = (EditText) findViewById(R.id.et_note);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner_bloodType);

        final RadioGroup rgGender = (RadioGroup) findViewById(R.id.rg_gender);
        final RadioGroup rg_m1 = (RadioGroup) findViewById(R.id.rGroup1);
        final RadioGroup rg_m2 = (RadioGroup) findViewById(R.id.rGroup2);
        final RadioGroup rg_m3 = (RadioGroup) findViewById(R.id.rGroup3);
        final RadioGroup rg_m4 = (RadioGroup) findViewById(R.id.rGroup4);
        final RadioGroup rg_m5 = (RadioGroup) findViewById(R.id.rGroup5);
        final RadioGroup rg_m6 = (RadioGroup) findViewById(R.id.rGroup6);
        final RadioGroup rg_m7 = (RadioGroup) findViewById(R.id.rGroup7);
        final RadioGroup rg_m8 = (RadioGroup) findViewById(R.id.rGroup8);
        final RadioGroup rg_m9 = (RadioGroup) findViewById(R.id.rGroup9);
        final RadioGroup rg_m10 = (RadioGroup) findViewById(R.id.rGroup10);

        final CheckBox cb_f1 = (CheckBox) findViewById(R.id.cb_1_f);
        final CheckBox cb_f2 = (CheckBox) findViewById(R.id.cb_2_f);
        final CheckBox cb_f3 = (CheckBox) findViewById(R.id.cb_3_f);
        final CheckBox cb_f4 = (CheckBox) findViewById(R.id.cb_4_f);
        final CheckBox cb_f5 = (CheckBox) findViewById(R.id.cb_5_f);
        final CheckBox cb_f6 = (CheckBox) findViewById(R.id.cb_6_f);
        final CheckBox cb_f7 = (CheckBox) findViewById(R.id.cb_7_f);
        final CheckBox cb_f8 = (CheckBox) findViewById(R.id.cb_8_f);
        final CheckBox cb_f9 = (CheckBox) findViewById(R.id.cb_9_f);
        final CheckBox cb_f10 = (CheckBox) findViewById(R.id.cb_10_f);

        final RadioGroup rgEat = (RadioGroup) findViewById(R.id.rg_eat);
        final RadioGroup rgSmoking = (RadioGroup) findViewById(R.id.rg_smoking);
        final RadioGroup rgDrinking = (RadioGroup) findViewById(R.id.rg_Drinking);


        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getApplicationContext(), R.array.blood_types, R.layout.spinner_item_blood);
        // adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(R.layout.spinner_item_blood);
        spinner.setAdapter(adapter1);



        rg_m10.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            TextView tv = (TextView) findViewById(R.id.tv_otherDisease_m);
            EditText et = (EditText) findViewById(R.id.et_otherDisease_m);
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.rb_10_x && !cb_f10.isChecked()) {
                    et.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                    et.setText("");
                } else {
                    tv.setVisibility(View.GONE);
                    et.setVisibility(View.VISIBLE);
                }
            }
        });

        cb_f10.setOnClickListener(new Button.OnClickListener(){

            TextView tv = (TextView) findViewById(R.id.tv_otherDisease_m);
            EditText et = (EditText) findViewById(R.id.et_otherDisease_m);
            public void onClick(View v){
                if(cb_f10.isChecked())
                {
                    tv.setVisibility(View.GONE);
                    et.setVisibility(View.VISIBLE);
                }
                else if(!cb_f10.isChecked() && (rg_m10.getCheckedRadioButtonId() == R.id.rb_10_x || rg_m10.getCheckedRadioButtonId() == -1 )){
                    et.setVisibility(View.GONE);
                    tv.setVisibility(View.VISIBLE);
                    et.setText("");
                }
            }
        });


        //     ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.blood_types, R.layout.spinner);
        //    adapter.setDropDownViewResource(R.layout.spinnerdorpdown);
        //     spinner.setAdapter(adapter);

        String[] strarr = innerDBManager.PrintData();

        RadioButton rb1 = (RadioButton) findViewById(R.id.rb_10_x);
        EditText et1 = (EditText) findViewById(R.id.et_otherDisease_m);

        if (!rb1.isChecked()) {
            if (!strarr[29].equals("-1"))
                et1.setText(strarr[29]);
        }

        etName.setText(strarr[0]);
        etNationality.setText(strarr[1]);
        etAge.setText(strarr[2]);
        etHeight.setText(strarr[4]);
        etWeight.setText(strarr[5]);

        spinner.setSelection(Integer.parseInt(strarr[31]));

        if (Integer.parseInt(strarr[3]) != -1) {
            final RadioButton rbGender = (RadioButton) findViewById(Integer.parseInt(strarr[3]));
            rbGender.setChecked(true);
        }

        else
            rgGender.clearCheck();

        if (Integer.parseInt(strarr[6]) != -1) {
            final RadioButton rb_m1 = (RadioButton) findViewById(Integer.parseInt(strarr[6]));
            rb_m1.setChecked(true);
        }
        else
            rg_m1.clearCheck();

        if (Integer.parseInt(strarr[7]) != -1) {
            final RadioButton rb_m2 = (RadioButton) findViewById(Integer.parseInt(strarr[7]));
            rb_m2.setChecked(true);
        }
        else
            rg_m2.clearCheck();

        if (Integer.parseInt(strarr[8]) != -1) {
            final RadioButton rb_m3 = (RadioButton) findViewById(Integer.parseInt(strarr[8]));
            rb_m3.setChecked(true);
        }
        else
            rg_m3.clearCheck();

        if (Integer.parseInt(strarr[9]) != -1) {
            final RadioButton rb_m4 = (RadioButton) findViewById(Integer.parseInt(strarr[9]));
            rb_m4.setChecked(true);
        }
        else
            rg_m4.clearCheck();

        if (Integer.parseInt(strarr[10]) != -1) {
            final RadioButton rb_m5 = (RadioButton) findViewById(Integer.parseInt(strarr[10]));
            rb_m5.setChecked(true);
        }
        else
            rg_m5.clearCheck();

        if (Integer.parseInt(strarr[11]) != -1) {
            final RadioButton rb_m6 = (RadioButton) findViewById(Integer.parseInt(strarr[11]));
            rb_m6.setChecked(true);
        }
        else
            rg_m6.clearCheck();

        if (Integer.parseInt(strarr[12]) != -1) {
            final RadioButton rb_m7 = (RadioButton) findViewById(Integer.parseInt(strarr[12]));
            rb_m7.setChecked(true);
        }
        else
            rg_m7.clearCheck();

        if (Integer.parseInt(strarr[13]) != -1) {
            final RadioButton rb_m8 = (RadioButton) findViewById(Integer.parseInt(strarr[13]));
            rb_m8.setChecked(true);
        }
        else
            rg_m8.clearCheck();

        if (Integer.parseInt(strarr[14]) != -1) {
            final RadioButton rb_m9 = (RadioButton) findViewById(Integer.parseInt(strarr[14]));
            rb_m9.setChecked(true);
        }
        else
            rg_m9.clearCheck();

        if (Integer.parseInt(strarr[15]) != -1) {
            final RadioButton rb_m10 = (RadioButton) findViewById(Integer.parseInt(strarr[15]));
            rb_m10.setChecked(true);
        }
        else{
            rg_m10.clearCheck();

            TextView tv = (TextView) findViewById(R.id.tv_otherDisease_m);
            EditText et = (EditText) findViewById(R.id.et_otherDisease_m);

            et.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);
            et.setText("");
        }

        if(Integer.parseInt(strarr[16]) != -1)
            cb_f1.setChecked(true);
        else
            cb_f1.setChecked(false);

        if(Integer.parseInt(strarr[17]) != -1)
            cb_f2.setChecked(true);
        else
            cb_f2.setChecked(false);

        if(Integer.parseInt(strarr[18]) != -1)
            cb_f3.setChecked(true);
        else
            cb_f3.setChecked(false);

        if(Integer.parseInt(strarr[19]) != -1)
            cb_f4.setChecked(true);
        else
            cb_f4.setChecked(false);

        if(Integer.parseInt(strarr[20]) != -1)
            cb_f5.setChecked(true);
        else
            cb_f5.setChecked(false);

        if(Integer.parseInt(strarr[21]) != -1)
            cb_f6.setChecked(true);
        else
            cb_f6.setChecked(false);

        if(Integer.parseInt(strarr[22]) != -1)
            cb_f7.setChecked(true);
        else
            cb_f7.setChecked(false);

        if(Integer.parseInt(strarr[23]) != -1)
            cb_f8.setChecked(true);
        else
            cb_f8.setChecked(false);

        if(Integer.parseInt(strarr[24]) != -1)
            cb_f9.setChecked(true);
        else
            cb_f9.setChecked(false);

        if(Integer.parseInt(strarr[32]) != -1)
            cb_f10.setChecked(true);
        else
            cb_f10.setChecked(false);


        if (Integer.parseInt(strarr[25]) != -1) {
            final RadioButton rb_Eat = (RadioButton) findViewById(Integer.parseInt(strarr[25]));
            rb_Eat.setChecked(true);
        }
        else
            rgEat.clearCheck();

        if (Integer.parseInt(strarr[26]) != -1) {
            final RadioButton rb_Smoking = (RadioButton) findViewById(Integer.parseInt(strarr[26]));
            rb_Smoking.setChecked(true);
        }
        else
            rgSmoking.clearCheck();

        if (Integer.parseInt(strarr[27]) != -1) {
            final RadioButton rb_Drinking = (RadioButton) findViewById(Integer.parseInt(strarr[27]));
            rb_Drinking.setChecked(true);
        }
        else
            rgDrinking.clearCheck();

        etNote.setText(strarr[28]);


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
        getMenuInflater().inflate(R.menu.questionnaire, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_save) {
            final InnerDBManager innerDBManager = new InnerDBManager(getApplicationContext(), "Questionnaire.db", null, 1);

            final EditText etName = (EditText) findViewById(R.id.et_name);
            final EditText etNationality = (EditText) findViewById(R.id.et_nationality);
            final EditText etAge = (EditText) findViewById(R.id.et_age);
            final EditText etHeight = (EditText) findViewById(R.id.et_height);
            final EditText etWeight = (EditText) findViewById(R.id.et_weight);
            final EditText etNote = (EditText) findViewById(R.id.et_note);

            final Spinner spinner = (Spinner) findViewById(R.id.spinner_bloodType);

            final RadioGroup rgGender = (RadioGroup) findViewById(R.id.rg_gender);
            final RadioGroup rg_m1 = (RadioGroup) findViewById(R.id.rGroup1);
            final RadioGroup rg_m2 = (RadioGroup) findViewById(R.id.rGroup2);
            final RadioGroup rg_m3 = (RadioGroup) findViewById(R.id.rGroup3);
            final RadioGroup rg_m4 = (RadioGroup) findViewById(R.id.rGroup4);
            final RadioGroup rg_m5 = (RadioGroup) findViewById(R.id.rGroup5);
            final RadioGroup rg_m6 = (RadioGroup) findViewById(R.id.rGroup6);
            final RadioGroup rg_m7 = (RadioGroup) findViewById(R.id.rGroup7);
            final RadioGroup rg_m8 = (RadioGroup) findViewById(R.id.rGroup8);
            final RadioGroup rg_m9 = (RadioGroup) findViewById(R.id.rGroup9);
            final RadioGroup rg_m10 = (RadioGroup) findViewById(R.id.rGroup10);

            final CheckBox cb_f1 = (CheckBox) findViewById(R.id.cb_1_f);
            final CheckBox cb_f2 = (CheckBox) findViewById(R.id.cb_2_f);
            final CheckBox cb_f3 = (CheckBox) findViewById(R.id.cb_3_f);
            final CheckBox cb_f4 = (CheckBox) findViewById(R.id.cb_4_f);
            final CheckBox cb_f5 = (CheckBox) findViewById(R.id.cb_5_f);
            final CheckBox cb_f6 = (CheckBox) findViewById(R.id.cb_6_f);
            final CheckBox cb_f7 = (CheckBox) findViewById(R.id.cb_7_f);
            final CheckBox cb_f8 = (CheckBox) findViewById(R.id.cb_8_f);
            final CheckBox cb_f9 = (CheckBox) findViewById(R.id.cb_9_f);
            final CheckBox cb_f10 = (CheckBox) findViewById(R.id.cb_10_f);

            final RadioGroup rgEat = (RadioGroup) findViewById(R.id.rg_eat);
            final RadioGroup rgSmoking = (RadioGroup) findViewById(R.id.rg_smoking);
            final RadioGroup rgDrinking = (RadioGroup) findViewById(R.id.rg_Drinking);

            String name = etName.getText().toString();
            String nationality = etNationality.getText().toString();
            String age = etAge.getText().toString();
            String height = etHeight.getText().toString();
            String weight = etWeight.getText().toString();
            String note = etNote.getText().toString();

            String bloodType = Integer.toString(spinner.getSelectedItemPosition());

            String gender = Integer.toString(rgGender.getCheckedRadioButtonId());
            String m1 = Integer.toString(rg_m1.getCheckedRadioButtonId());
            String m2 = Integer.toString(rg_m2.getCheckedRadioButtonId());
            String m3 = Integer.toString(rg_m3.getCheckedRadioButtonId());
            String m4 = Integer.toString(rg_m4.getCheckedRadioButtonId());
            String m5 = Integer.toString(rg_m5.getCheckedRadioButtonId());
            String m6 = Integer.toString(rg_m6.getCheckedRadioButtonId());
            String m7 = Integer.toString(rg_m7.getCheckedRadioButtonId());
            String m8 = Integer.toString(rg_m8.getCheckedRadioButtonId());
            String m9 = Integer.toString(rg_m9.getCheckedRadioButtonId());
            String m10 = Integer.toString(rg_m10.getCheckedRadioButtonId());

            String f1;
            String f2;
            String f3;
            String f4;
            String f5;
            String f6;
            String f7;
            String f8;
            String f9;
            String f10;

            if(cb_f1.isChecked())
                f1 = "1";
            else
                f1 = "-1";
            if(cb_f2.isChecked())
                f2 = "1";
            else
                f2 = "-1";
            if(cb_f3.isChecked())
                f3 = "1";
            else
                f3 = "-1";
            if(cb_f4.isChecked())
                f4 = "1";
            else
                f4 = "-1";
            if(cb_f5.isChecked())
                f5 = "1";
            else
                f5 = "-1";
            if(cb_f6.isChecked())
                f6 = "1";
            else
                f6 = "-1";
            if(cb_f7.isChecked())
                f7 = "1";
            else
                f7 = "-1";
            if(cb_f8.isChecked())
                f8 = "1";
            else
                f8 = "-1";
            if(cb_f9.isChecked())
                f9 = "1";
            else
                f9 = "-1";

            if(cb_f10.isChecked())
                f10 = "1";
            else
                f10 = "-1";


            String otherDisease_m = "";
            String otherDisease_f = "";

            RadioButton rb1 = (RadioButton) findViewById(R.id.rb_10_x);

            if (!rb1.isChecked() || cb_f10.isChecked()) {
                EditText et = (EditText) findViewById(R.id.et_otherDisease_m);
                otherDisease_m = et.getText().toString();
            }

            String eating = Integer.toString(rgEat.getCheckedRadioButtonId());
            String smoking = Integer.toString(rgSmoking.getCheckedRadioButtonId());
            String drinking = Integer.toString(rgDrinking.getCheckedRadioButtonId());

            //     etNote.setText(" "+gender+" "+m1+" "+m2+" "+m3+" "+m4+" "+m5+" "+m6+" "+m7+" "+m8+" "+m9+" "+m10+" "+f1+" "+f2+" "+f3+" "+f4+" "+f5+" "+f6+" "+f7+" " );
            innerDBManager.insert("insert into QUESTIONNAIRE_LIST values(null, '" + name + "', '" + nationality + "' , '" + age + "', '" + gender + "', '" + height + "', '" + weight + "' , '" + m1 + "', '" + m2 + "', '" + m3 + "', '" + m4 + "', '" + m5 + "', '" + m6 + "', '" + m7 + "', '" + m8 + "', '" + m9 + "', '" + m10 + "', '" + f1 + "', '" + f2 + "', '" + f3 + "','" + f4 + "', '" + f5 + "', '" + f6 + "', '" + f7 + "', '" + f8 + "', '" + f9 + "', '" + eating + "', '" + smoking + "', '" + drinking + "', '" + note + "', '" + otherDisease_m + "', '" + otherDisease_f + "', '" + bloodType + "', '"+f10 +"');");

            Intent intent = new Intent(getApplicationContext(), DiseaseNotifier.class);
            intent.putExtra("check", 1);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

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
