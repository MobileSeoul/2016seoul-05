package seoul.emergency.bbibbo.questionnaire;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import seoul.emergency.bbibbo.R;
import seoul.emergency.bbibbo.map.MyLocation;

public class DiseaseNotifier extends AppCompatActivity {

    public ArrayList<DiseaseItem> diseaseItemList = new ArrayList<DiseaseItem>();
    LocationManager locationManager;
    LocationListener locationListener;
    String lat, lon;

    String myMessage="";
    String diseaseNotificationMessage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN|
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        setContentView(R.layout.activity_disease_notifier);


        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(metrics);

        ImageView icon_call1 = (ImageView) findViewById(R.id.icon_call1);
        TextView text_call1 = (TextView) findViewById(R.id.text_call1);
        ImageView icon_m = (ImageView) findViewById(R.id.icon_m);
        TextView text_m = (TextView) findViewById(R.id.text_m);

        LinearLayout.LayoutParams params5 = (LinearLayout.LayoutParams) icon_call1.getLayoutParams();

        params5.width = metrics.widthPixels * 100 / 360 ;
        params5.height = metrics.heightPixels * 100 / 640;

        params5 = (LinearLayout.LayoutParams) text_call1.getLayoutParams();
        params5.topMargin = metrics.heightPixels * 55 / 640;

        LinearLayout.LayoutParams params6 = (LinearLayout.LayoutParams) icon_m.getLayoutParams();

        params6.width = metrics.widthPixels * 100 / 360 ;
        params6.height = metrics.heightPixels * 100 / 640;

        params6 = (LinearLayout.LayoutParams) text_m.getLayoutParams();
        params6.topMargin = metrics.heightPixels * 55 / 640;


        ImageView btn_call1 = (ImageView) findViewById(R.id.icon_call1);
        ImageView btn_m = (ImageView) findViewById(R.id.icon_m);
        LinearLayout layout_forCall1 = (LinearLayout) findViewById(R.id.layout_forCall1);



        Intent intent1 = getIntent();
        int check = intent1.getExtras().getInt("check");

        if(check==1) {
            layout_forCall1.setVisibility(View.GONE);
            text_m.setVisibility(View.GONE);
            text_call1.setVisibility(View.GONE);
            check=0;
        }

       btn_call1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               Intent intent=new Intent(Intent.ACTION_CALL);
               intent.setData(Uri.parse("tel:010119"));
               try {
                   startActivity(intent);
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       });

        btn_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                MyLocationListener listener = new MyLocationListener();
//
//                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//
//                    return;
//                }
//
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 0, listener);

                try{
                MyLocation.LocationResult locationResult = new MyLocation.LocationResult() {
                    @Override
                    public void gotLocation(Location location) {
                        String msg = "lon: " + location.getLongitude() +
                                "lat: " + location.getLatitude();
                        Log.d("lat",""+location.getLatitude());
                        lat = String.valueOf(location.getLatitude());
                        lon = String.valueOf(location.getLongitude());
                        /*Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.putExtra("address", "01031299455");
                        intent.putExtra("sms_body", "I'm in trouble please send me help. My location is "+lat+", "+lon+"");
                        intent.setType("vnd.android-dir/mms-sms");
                        startActivity(intent);*/

                        SmsManager smsManager = SmsManager.getDefault();
                        String sendTo = "01044305984";
                        myMessage += "in trouble please send me help. My location is"+lat+", "+lon+"";

                        smsManager.sendTextMessage(sendTo, null, myMessage, null, null);
                        if(!diseaseNotificationMessage.equals("")){
                            smsManager.sendTextMessage(sendTo, null, diseaseNotificationMessage, null, null);
                        }

                        Toast.makeText(DiseaseNotifier.this, "SMS Sent.", Toast.LENGTH_SHORT).show();
                        finish();


                    }
                };

                MyLocation myLocation = new MyLocation();
                myLocation.getLocation(getApplicationContext(), locationResult);
            }catch (Exception e){
            }
            }
        });

        ListView listView=(ListView)findViewById(R.id.diseaseListView);


        getMyDisease();

        DiseaseAdapter adapter=new DiseaseAdapter(this, R.layout.diseasetext_custom, diseaseItemList);
        listView.setAdapter(adapter);
        setListViewHeightBasedOnChildren(listView);

 //       Intent intent = new Intent(this, ScreenService.class);
 //       startService(intent);
    }

    class MyLocationListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {


            lat = String.valueOf(location.getLatitude());
            Log.d("$$$",""+lat+" d " + location.getLongitude() );
            lon = String.valueOf(location.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }




    public void getMyDisease(){

        InnerDBManager innerDBManager = new InnerDBManager(getApplicationContext(), "Questionnaire.db", null, 1);
        String[] strarr = innerDBManager.PrintData();

        String status = "";
        String name = "";

        final TextView tvName = (TextView) findViewById(R.id.tv_name);
        final TextView tvNationality = (TextView) findViewById(R.id.tv_nationality);
        final TextView tvAge = (TextView) findViewById(R.id.tv_age);
        final TextView tvGender = (TextView) findViewById(R.id.tv_gender);
        final TextView tvHeight = (TextView) findViewById(R.id.tv_height);
        final TextView tvWeight = (TextView) findViewById(R.id.tv_weight);
        final TextView tvNote = (TextView) findViewById(R.id.tv_note);
        final TextView tvBloodType = (TextView) findViewById(R.id.tv_bloodType);
        final TextView tvEating = (TextView) findViewById(R.id.tv_eating);
        final TextView tvSmoking = (TextView) findViewById(R.id.tv_smoking);
        final TextView tvDrinking = (TextView) findViewById(R.id.tv_drinking);

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

        tvName.setText(strarr[0]);
        tvNationality.setText(strarr[1]);

        if(Integer.parseInt(strarr[3]) == R.id.rb_male) {
            tvGender.setText(" Male");
            myMessage += "Male ";
        }
        else if(Integer.parseInt(strarr[3]) == R.id.rb_female) {
            tvGender.setText(" Female");
            myMessage += "Female ";
        }

        tvAge.setText(strarr[2]);
        tvHeight.setText(strarr[4]);
        tvWeight.setText(strarr[5]);
        tvNote.setText(strarr[28]);

        if(strarr[31].equals("0")) {
            tvBloodType.setText("RH+ / A");
            myMessage += "RH+ / A ";
        }
        else if(strarr[31].equals("1")){
            tvBloodType.setText("RH+ / B");
            myMessage += "RH+ / B ";
        }
        else if(strarr[31].equals("2")){
            tvBloodType.setText("RH+ / O");
            myMessage += "RH+ / O ";
        }
        else if(strarr[31].equals("3")){
            tvBloodType.setText("RH+ / AB");
            myMessage += "RH+ / AB ";
        }
        else if(strarr[31].equals("4")){
            tvBloodType.setText("RH- / A");
            myMessage += "RH- / A ";
        }
        else if(strarr[31].equals("5")){
            tvBloodType.setText("RH- / B");
            myMessage += "RH- / B ";
        }
        else if(strarr[31].equals("6")){
            tvBloodType.setText("RH- / O");
            myMessage += "RH- / O ";
        }
        else if(strarr[31].equals("7")){
            tvBloodType.setText("RH- / AB");
            myMessage += "RH- / AB ";
        }

        if(Integer.parseInt(strarr[25]) == R.id.rb_meat)
            tvEating.setText("Meat");
        else if(Integer.parseInt(strarr[25]) == R.id.rb_vegetarian)
            tvEating.setText("Vegeterian");
        else if(Integer.parseInt(strarr[25]) == R.id.rb_mixed)
            tvEating.setText("Mixed");

        if(Integer.parseInt(strarr[26]) == R.id.rb_s_frequent)
            tvSmoking.setText("Frequent");
        else if(Integer.parseInt(strarr[26]) == R.id.rb_s_seldom)
            tvSmoking.setText("Somehimes");
        else if(Integer.parseInt(strarr[26]) == R.id.rb_s_hardly)
            tvSmoking.setText("Hardly");
        else if(Integer.parseInt(strarr[26]) == R.id.rb_s_stop)
            tvSmoking.setText("Stop");
        else if(Integer.parseInt(strarr[26]) == R.id.rb_s_never)
            tvSmoking.setText("Never");

        if(Integer.parseInt(strarr[27]) == R.id.rb_d_frequent)
            tvDrinking.setText("Frequent");
        else if(Integer.parseInt(strarr[27]) == R.id.rb_d_seldom)
            tvDrinking.setText("At times");
        else if(Integer.parseInt(strarr[27]) == R.id.rb_d_hardly)
            tvDrinking.setText("Hardly");
        else if(Integer.parseInt(strarr[27]) == R.id.rb_d_never)
            tvDrinking.setText("Never");

        status = "";
        name = "";
        if(Integer.parseInt(strarr[6]) == R.id.rb_1_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[6]) == R.id.rb_1_ut)
            status = "Under Treatment ";
        if(strarr[16].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Tuberculosis ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[7]) == R.id.rb_2_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[7]) == R.id.rb_2_ut)
            status = "Under Treatment ";
        if(strarr[17].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Hepatitis ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[8]) == R.id.rb_3_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[8]) == R.id.rb_3_ut)
            status = "Under Treatment ";
        if(strarr[18].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Liver Disease";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[9]) == R.id.rb_4_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[9]) == R.id.rb_4_ut)
            status = "Under Treatment ";
        if(strarr[19].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Hypertension ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[10]) == R.id.rb_5_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[10]) == R.id.rb_5_ut)
            status = "Under Treatment ";
        if(strarr[20].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Stroke ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[11]) == R.id.rb_6_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[11]) == R.id.rb_6_ut)
            status = "Under Treatment ";
        if(strarr[21].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Heart Disease";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[12]) == R.id.rb_7_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[12]) == R.id.rb_7_ut)
            status = "Under Treatment ";
        if(strarr[22].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Diabetes ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[13]) == R.id.rb_8_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[13]) == R.id.rb_8_ut)
            status = "Under Treatment ";
        if(strarr[23].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Cancer ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[14]) == R.id.rb_9_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[14]) == R.id.rb_9_ut)
            status = "Under Treatment ";
        if(strarr[24].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = "Depression ";
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }

        status = "";
        name = "";
        if(Integer.parseInt(strarr[15]) == R.id.rb_10_cr)
            status = "Completely Recovered ";
        else if(Integer.parseInt(strarr[15]) == R.id.rb_10_ut)
            status = "Under Treatment ";
        if(strarr[32].equals("1")) {
            if (status.length() != 0)
                status += "\nFamily History";
            else
                status += "Family History";
        }
        if(!status.equals("")) {
            name = strarr[29];
            diseaseNotificationMessage+=name + status + " ";
            DiseaseItem item = new DiseaseItem(name, status);
            diseaseItemList.add(item);
        }
    }
}
